package ltd.matrixstudios.venture

import ltd.matrixstudios.venture.controllers.StudentController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import revxrsal.commands.cli.ConsoleCommandHandler
import java.util.*

@SpringBootApplication
class VentureService
{
    companion object
    {
        lateinit var instance: VentureService
        lateinit var context: ConfigurableApplicationContext
        val commandHandler = ConsoleCommandHandler.create()

        @JvmStatic
        fun main(args: Array<String>)
        {
            context = runApplication<VentureService>(*args) {
                println(
                    "Started testing suite successfully!."
                )
            }

            instance = VentureService()

            commandHandler.pollInput()
        }
    }
}
