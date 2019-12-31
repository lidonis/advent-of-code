import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ActorScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor

@ObsoleteCoroutinesApi
fun main() {
    NetworkInterfaceControllerActor.main()
}

sealed class NetworkMessage
data class Packet(val source: Int, val destination: Int, val x: Long, val y: Long) : NetworkMessage()
object Done : NetworkMessage()
abstract class Activity : NetworkMessage() {
    abstract val address: Int
}

data class Idle(override val address: Int) : Activity()
data class Active(override val address: Int) : Activity()

object NetworkInterfaceControllerActor {

    @ObsoleteCoroutinesApi
    fun main() = runBlocking {
        actor<NetworkMessage>(capacity = Channel.UNLIMITED) {
            Router(this).dispatch()
        }
    }

    @ObsoleteCoroutinesApi
    private class Router(private val actor: ActorScope<NetworkMessage>) {
        private val scope = CoroutineScope(actor.coroutineContext)
        private val program = InputReader("day23.txt").asLineOfLongs()
        private val computerChannels = mutableListOf<SendChannel<Packet>>()
        private var monitor: SendChannel<NetworkMessage>
        private var nat: SendChannel<NetworkMessage>

        init {
            monitor = scope.actor(capacity = Channel.UNLIMITED) {
                MonitorActor(this, actor.channel).watch()
            }
            nat = scope.actor(capacity = Channel.UNLIMITED) {
                NotAlwaysTransmittingActor(this, actor.channel).nat()
            }
            computerChannels.addAll((0 until 50).map {
                scope.actor<Packet>(capacity = Channel.UNLIMITED) {
                    ComputerActor(this, actor.channel, program, it).compute()
                }
            })
        }

        suspend fun dispatch() {
            routeMessage()
        }

        private suspend fun routeMessage() {
            for (networkMessage in actor.channel) {
                monitor.send(networkMessage)
                when (networkMessage) {
                    is Packet -> routePacket(networkMessage)
                    is Activity -> if (networkMessage.address == 255) nat.send(networkMessage)
                    is Done -> actor.cancel()
                }
            }
        }

        private suspend fun routePacket(packet: Packet) {
            when (packet.destination) {
                in computerChannels.indices -> computerChannels[packet.destination].send(packet)
                255 -> nat.send(packet)
                else -> {
                    error("Dead letter $packet")
                }
            }
        }

    }

    @ObsoleteCoroutinesApi
    private class ComputerActor(
        private val actor: ActorScope<Packet>,
        private val network: Channel<NetworkMessage>,
        program: List<Long>,
        private val address: Int
    ) {
        private val computer = IntCodeComputerFactory.buildIOComputer(program)

        init {
            computer.input(address.toLong())
        }

        suspend fun compute() {
            while (true) {
                withContext(Dispatchers.IO) {
                    activate()
                }
            }
        }

        private suspend fun activate() {
            var count = 0
            while (count < 20) {
                actor.channel.poll()?.run {
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
            computer.tryNextOutput(10)?.let { destination ->
                computer.tryNextOutput(10)?.let { x ->
                    computer.tryNextOutput(10)?.let { y ->
                        Packet(address, destination.toInt(), x, y)
                    }
                }
            }
    }

    @ObsoleteCoroutinesApi
    private class MonitorActor(
        private val actor: ActorScope<NetworkMessage>,
        private val network: Channel<NetworkMessage>
    ) {
        private val ysReceivedByComputer0FromNat = mutableSetOf<Long>()
        private val computerStates = MutableList(50) { true }

        suspend fun watch() {
            for (networkMessage in actor.channel) {
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
                        network.send(Active(255))
                    }
                    is Idle -> {
                        computerStates[networkMessage.address] = false
                        if (computerStates.all { !it }) {
                            network.send(Idle(255))
                        }
                    }
                }
            }
        }

        private fun checkFirstY(networkMessage: Packet) {
            if (networkMessage.destination == 255 && ysReceivedByComputer0FromNat.isEmpty()) {
                println("Y value of the first packet sent to address 255: ${networkMessage.y}")
            }
        }

        private suspend fun checkYTwiceInRow(networkMessage: Packet) {
            if (networkMessage.source == 255 && networkMessage.destination == 0
                && !ysReceivedByComputer0FromNat.add(networkMessage.y)
            ) {
                println("First Y value delivered by the NAT to the computer at address 0 twice in a row: ${networkMessage.y}")
                network.send(Done)
            }
        }
    }

    @ObsoleteCoroutinesApi
    private class NotAlwaysTransmittingActor(
        private val actor: ActorScope<NetworkMessage>,
        private val network: Channel<NetworkMessage>
    ) {
        private var lastPacket: Packet? = null

        suspend fun nat() {
            for (networkMessage in actor.channel) {
                when {
                    networkMessage is Packet -> lastPacket = networkMessage
                    networkMessage is Idle && networkMessage.address == 255 -> lastPacket?.run {
                        network.send(Packet(255, 0, x, y))
                        lastPacket = null
                    }
                }
            }
        }
    }
}