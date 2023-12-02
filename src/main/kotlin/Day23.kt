import fr.lidonis.adventofcode.common.InputReader
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ActorScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.runBlocking

@ObsoleteCoroutinesApi
fun main() {
    NetworkInterfaceControllerActor.main()
}

sealed class NetworkMessage
data class Packet(val source: Int, val destination: Int, val x: Long, val y: Long) : NetworkMessage()
data object Done : NetworkMessage()
abstract class Activity : NetworkMessage() {
    abstract val address: Int
}

data class Idle(override val address: Int) : Activity()
data class Active(override val address: Int) : Activity()

object NetworkInterfaceControllerActor {
    private const val MAX_COMPUTER = 50
    private const val MAX_TRIES = 10
    private const val MAX_COUNT = 20
    private const val NAT_ADDRESS = 255

    @ObsoleteCoroutinesApi
    fun main() = runBlocking {
        actor(capacity = Channel.UNLIMITED) {
            Router(this).dispatch()
        }
    }

    @ObsoleteCoroutinesApi
    private class Router(private val actor: ActorScope<NetworkMessage>) {
        private val scope = CoroutineScope(actor.coroutineContext)
        private val program = InputReader("/input/y2019/day23.txt").text()
        private var monitor: SendChannel<NetworkMessage> = scope.actor(capacity = Channel.UNLIMITED) {
            Monitor(this, actor.channel).watch()
        }

        private var nat: SendChannel<NetworkMessage> = scope.actor(capacity = Channel.UNLIMITED) {
            NotAlwaysTransmitting(this, actor.channel).nat()
        }

        private val computerChannels = (0 until MAX_COMPUTER).map {
            scope.actor(capacity = Channel.UNLIMITED) {
                NetworkComputer(this, actor.channel, program, it).compute()
            }
        }

        suspend fun dispatch() {
            routeMessage()
        }

        private suspend fun routeMessage() {
            for (networkMessage in actor) {
                monitor.send(networkMessage)
                when (networkMessage) {
                    is Packet -> routePacket(networkMessage)
                    is Activity -> if (networkMessage.address == NAT_ADDRESS) nat.send(networkMessage)
                    is Done -> actor.cancel()
                }
            }
        }

        private suspend fun routePacket(packet: Packet) {
            when (packet.destination) {
                in computerChannels.indices -> computerChannels[packet.destination].send(packet)
                NAT_ADDRESS -> nat.send(packet)
                else -> {
                    error("Dead letter $packet")
                }
            }
        }
    }

    private class NetworkComputer(
        private val inbox: ReceiveChannel<Packet>,
        private val network: SendChannel<NetworkMessage>,
        program: String,
        private val address: Int,
    ) {
        private val computer = IntCodeComputerFactory.buildIOComputer(program)

        init {
            computer.input(address.toLong())
        }

        suspend fun compute() {
            while (true) {
                activate()
            }
        }

        private suspend fun activate() {
            var count = 0
            while (count < MAX_COUNT) {
                inbox.tryReceive().getOrNull()?.run {
                    computer.input(x)
                    computer.input(y)
                    network.send(Active(address))
                    count = 0
                }
                nextPacket()?.run { network.send(this) }
                count++
            }
            network.send(Idle(address))
        }

        private fun nextPacket() =
            computer.tryNextOutput(MAX_TRIES)?.let { destination ->
                computer.tryNextOutput(MAX_TRIES)?.let { x ->
                    computer.tryNextOutput(MAX_TRIES)?.let { y ->
                        Packet(address, destination.toInt(), x, y)
                    }
                }
            }
    }

    private class Monitor(
        private val inbox: ReceiveChannel<NetworkMessage>,
        private val network: SendChannel<NetworkMessage>,
    ) {
        private val ysReceivedByComputer0FromNat = mutableSetOf<Long>()
        private val computerStates = MutableList(MAX_COMPUTER) { true }

        suspend fun watch() {
            for (networkMessage in inbox) {
                if (networkMessage is Packet) {
                    checkFirstY(networkMessage)
                    checkYTwiceInRow(networkMessage)
                } else if (networkMessage is Activity) {
                    checkActivity(networkMessage)
                }
            }
        }

        private suspend fun checkActivity(networkMessage: Activity) {
            if (networkMessage.address in computerStates.indices) {
                when (networkMessage) {
                    is Active -> {
                        computerStates[networkMessage.address] = true
                        network.send(Active(NAT_ADDRESS))
                    }
                    is Idle -> {
                        computerStates[networkMessage.address] = false
                        if (computerStates.all { !it }) {
                            network.send(Idle(NAT_ADDRESS))
                        }
                    }
                }
            }
        }

        private fun checkFirstY(networkMessage: Packet) {
            if (networkMessage.destination == NAT_ADDRESS && ysReceivedByComputer0FromNat.isEmpty()) {
                println("Y value of the first packet sent to address 255: ${networkMessage.y}")
            }
        }

        private suspend fun checkYTwiceInRow(networkMessage: Packet) {
            if (networkMessage.source == NAT_ADDRESS && networkMessage.destination == 0 &&
                !ysReceivedByComputer0FromNat.add(networkMessage.y)
            ) {
                println(
                    "First Y value delivered by the NAT to the computer at address 0 twice in a row: " +
                        "${networkMessage.y}"
                )
                network.send(Done)
            }
        }
    }

    private class NotAlwaysTransmitting(
        private val inbox: ReceiveChannel<NetworkMessage>,
        private val network: SendChannel<NetworkMessage>,
    ) {
        private var lastPacket: Packet? = null

        suspend fun nat() {
            for (networkMessage in inbox) {
                when {
                    networkMessage is Packet -> lastPacket = networkMessage
                    networkMessage is Idle && networkMessage.address == NAT_ADDRESS -> lastPacket?.run {
                        network.send(Packet(NAT_ADDRESS, 0, x, y))
                        lastPacket = null
                    }
                }
            }
        }
    }
}
