package ltd.matrixstudios.venture.command.clazz

import ltd.matrixstudios.venture.VentureService
import ltd.matrixstudios.venture.controllers.ClassController
import ltd.matrixstudios.venture.controllers.TeacherController
import ltd.matrixstudios.venture.models.classes.Class
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import revxrsal.commands.annotation.Command
import revxrsal.commands.cli.core.CommandLineActor
import java.util.*
import kotlin.math.acos

@Service
class ClassCommands {
    @Autowired lateinit var classController: ClassController
    @Autowired lateinit var teacherController: TeacherController

    init {
        VentureService.commandHandler.register(this)
    }


    @Command("class create")
    fun createClass(
        actor: CommandLineActor,
        name: String,
        teacherEmail: String
    )
    {
        val randomId = UUID.randomUUID().toString().substring(0, 6)

        val classOptional = classController.classRepository.findByRandomId(randomId).blockOptional()

        if (classOptional.isPresent)
        {
            actor.reply("Wow this is rare but like the class already exists")
            return
        }

        val teacherOptional = teacherController.repository.findByEmail(teacherEmail).blockOptional()

        if (!teacherOptional.isPresent)
        {
            actor.reply("No teacher with this email exists!")
            return
        }

        val teacher = teacherOptional.get()

        val clazz = Class(randomId, name, System.currentTimeMillis(), teacher.uuid, mutableListOf())

        classController.classRepository.save(clazz).subscribe()
    }
}