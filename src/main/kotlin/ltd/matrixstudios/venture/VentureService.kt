package ltd.matrixstudios.venture

import ltd.matrixstudios.venture.controllers.StudentController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import java.util.*

@SpringBootApplication
class VentureService
{


    companion object
    {
        lateinit var context: ConfigurableApplicationContext
        @JvmStatic
        fun main(args: Array<String>)
        {
            context = runApplication<VentureService>(*args) {
                println(
                    "Started testing suite successfully!."
                )
            }
        }
    }
}
