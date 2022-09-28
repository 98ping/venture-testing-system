package ltd.matrixstudios.venture.command.student

import ltd.matrixstudios.venture.VentureService
import ltd.matrixstudios.venture.controllers.StudentController
import ltd.matrixstudios.venture.models.Student
import ltd.matrixstudios.venture.repository.mongo.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import revxrsal.commands.annotation.Command
import revxrsal.commands.cli.core.CommandLineActor
import java.util.*

@Service
class StudentCommands {

    @Autowired lateinit var studentRepository: StudentRepository
    @Autowired lateinit var studentController: StudentController

    init {
        VentureService.commandHandler.register(this)
    }

    @Command("student register")
    fun registerAccount(
        actor: CommandLineActor,
        email: String,
        username: String,
        password: String
    )
    {
        if (studentRepository.findByEmail(email).blockOptional().isPresent)
        {
            actor.reply("Someone with this email is already registered!")
            return
        }

        val student = Student(
            UUID.randomUUID(),
            username,
            email,
            password
        )

        studentController.cacheAndSave(student)
        actor.reply("Student has been created!")
    }

}