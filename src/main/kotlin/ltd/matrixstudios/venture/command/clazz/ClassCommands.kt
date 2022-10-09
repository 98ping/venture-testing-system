package ltd.matrixstudios.venture.command.clazz

import ltd.matrixstudios.venture.VentureService
import ltd.matrixstudios.venture.controllers.ClassController
import ltd.matrixstudios.venture.controllers.TeacherController
import ltd.matrixstudios.venture.models.classes.Class
import ltd.matrixstudios.venture.testing.Test
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

    @Command("class createtest")
    fun createTest(
        actor: CommandLineActor,
        classId: String,
        name: String
    )
    {
        val clazz = classController.classRepository.findByRandomId(classId).blockOptional()

        if (!clazz.isPresent)
        {
            actor.reply("Class with this id not found")
            return
        }

        val test = Test(UUID.randomUUID().toString().substring(0, 6), name, Long.MAX_VALUE, mutableListOf())

        val finalClazz = clazz.get()
        finalClazz.tests.add(test)

        classController.classRepository.save(finalClazz).subscribe()

        actor.reply("Created a new test with the name $name")
    }

    @Command("class create")
    fun createClass(
        actor: CommandLineActor,
        teacherEmail: String,
        name: String
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
            actor.reply("email: $teacherEmail")
            actor.reply("class name: $name")
            return
        }

        val teacher = teacherOptional.get()

        val clazz = Class(randomId, name, System.currentTimeMillis(), teacher.uuid, mutableListOf(), mutableListOf())

        classController.classRepository.save(clazz).subscribe()
    }
}