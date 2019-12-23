import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce

data class NetworkPacket(val source: Int, val destination: Int, val x: Long, val y: Long)

fun main() {
    NetworkInterfaceCoroutine.main()
}

object NetworkInterfaceCoroutine {

    fun main() {
        val program = InputReader("day23.txt").asLineOfLongs()
        val outputChannel = Channel<NetworkPacket>(capacity = Channel.UNLIMITED)
        val computers = (0 until 50).map {
            NetworkInterfaceComputer(program, it, outputChannel)
        }
        val nat = NotAlwaysTransmitting(computers, outputChannel)
        val router = Router(computers, nat, outputChannel)
        runBlocking {
            launch(newSingleThreadContext("RouterThread")) {
                log("run router")
                router.run()
            }
            launch(newSingleThreadContext("NatThread")) {
                log("run nat")
                nat.run()
            }
            for (computer in computers) {
                launch(newSingleThreadContext("Computer${computer.address}Thread")) {
                    computer.run()
                }
            }
        }
    }

    fun log(text: String) {
        println("${Thread.currentThread().name} $text")
    }

    class Router(
        private val computers: List<NetworkInterfaceComputer>,
        private val nat: NotAlwaysTransmitting,
        private val outputChannel: Channel<NetworkPacket>
    ) {
        private val monitor = NetWorkMonitor()

        suspend fun run() {
            while (true) {
                outputChannel.receive().let {
                    monitor.monitor(it)
                    when (it.destination) {
                        in computers.indices -> {
                            computers[it.destination].inputChannel.send(it)
                        }
                        255 -> nat.send(it)
                    }
                }
            }
        }

        class NetWorkMonitor {
            private val ysReceivedByComputer0FromNat = mutableSetOf<Long>()

            fun monitor(packet: NetworkPacket) {
                log("Monitor $packet")
                if (packet.destination == 255) {
                    if (ysReceivedByComputer0FromNat.isEmpty()) {
                        log("First Y sent to address 255: ${packet.y}")
                    }
                }
                if (packet.source == 255 && packet.destination == 0) {
                    if (!ysReceivedByComputer0FromNat.add(packet.y)) {
                        log("First Y value delivered by the NAT to the computer at address 0 twice in a row: ${packet.y}")
                    }
                }
            }
        }
    }


    class NetworkInterfaceComputer(
        program: List<Long>,
        val address: Int,
        private val outputChannel: Channel<NetworkPacket>
    ) {
        private val computer = IntCodeComputer(program)
        val inputChannel = Channel<NetworkPacket>(capacity = Channel.UNLIMITED)
        var idle = false

        init {
            computer.input(address.toLong())
        }

        suspend fun run() {
            while (true) {
                inputChannel.poll()?.let {
                    computer.input(it.x)
                    computer.input(it.y)
                }
                val packet = computer.tryNextOutput(10)?.let {
                    NetworkPacket(
                        address, it.toInt(),
                        computer.nextOutput()!!,
                        computer.nextOutput()!!
                    )
                }
                if (packet != null) {
                    idle = false
                    outputChannel.send(packet)
                } else {
                    idle = true
                }
            }
        }
    }

    class NotAlwaysTransmitting(
        private val computers: List<NetworkInterfaceComputer>,
        private val outputChannel: Channel<NetworkPacket>
    ) {
        private var packet: NetworkPacket? = null

        fun send(nextPacket: NetworkPacket) {
            packet = nextPacket
        }

        suspend fun run() {
            while (true) {
                if (packet != null && computers.all { it.idle }) {
                    packet?.let {
                        val outputPacket = NetworkPacket(255, 0, it.x, it.y)
                        outputChannel.send(outputPacket)
                        packet = null
                    }
                }
                delay(50)
            }
        }
    }
}