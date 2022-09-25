package ltd.matrixstudios.venture

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext

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
