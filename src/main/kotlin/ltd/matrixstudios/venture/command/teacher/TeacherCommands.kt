package ltd.matrixstudios.venture.command.teacher

import ltd.matrixstudios.venture.VentureService
import ltd.matrixstudios.venture.controllers.TeacherController
import ltd.matrixstudios.venture.models.Teacher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import revxrsal.commands.annotation.Command
import revxrsal.commands.cli.core.CommandLineActor
import java.util.*

@Service
class TeacherCommands {

    @Autowired lateinit var teacherController: TeacherController

    init {
        VentureService.commandHandler.register(this)
    }

    @Command("teacher register")
    fun registerAccount(
        actor: CommandLineActor,
        email: String,
        username: String,
        password: String
    )
    {
        if (teacherController.repository.findByEmail(email).blockOptional().isPresent)
        {
            actor.reply("Someone with this email is already registered!")
            return
        }

        val teacher = Teacher(
            UUID.randomUUID(),
            username,
            "None",
            email,
            password
        )

        teacherController.repository.save(teacher).subscribe()
        actor.reply("Teacher has been created!")
    }

}