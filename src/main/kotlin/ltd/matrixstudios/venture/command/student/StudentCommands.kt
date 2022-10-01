package ltd.matrixstudios.venture.command.student

import ltd.matrixstudios.venture.VentureService
import ltd.matrixstudios.venture.controllers.ClassController
import ltd.matrixstudios.venture.controllers.StudentController
import ltd.matrixstudios.venture.models.Student
import ltd.matrixstudios.venture.repository.mongo.StudentRepository
import ltd.matrixstudios.venture.repository.mongo.`class`.ClassRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import revxrsal.commands.annotation.Command
import revxrsal.commands.cli.core.CommandLineActor
import java.util.*

@Service
class StudentCommands {

    @Autowired lateinit var studentRepository: StudentRepository
    @Autowired lateinit var classRepository: ClassRepository

    @Autowired lateinit var classController: ClassController
    @Autowired lateinit var studentController: StudentController

    init {
        VentureService.commandHandler.register(this)
    }

    @Command("student searchname")
    fun searchName(
        actor: CommandLineActor,
        name: String
    )
    {
        val start = System.currentTimeMillis()

        val student = studentRepository.findByName(name)

        actor.reply("Student was found in " + System.currentTimeMillis().minus(start))

    }

    @Command("student simulate")
    fun simulate(
        actor: CommandLineActor,
        amount: Int
    )
    {
        val start = System.currentTimeMillis()

        for (i in 0 until amount)
        {
            val student = Student(UUID.randomUUID(), "Student$i", "funnyemail", "funny password")
            studentRepository.save(student).subscribe()
        }

        actor.reply("Created 200,000 students in about " + System.currentTimeMillis().minus(start) + "ms")

    }

    @Command("student joinClass")
    fun joinClass(
        actor: CommandLineActor,
        studentEmail: String,
        classCode: String
    )
    {
        if (!studentRepository.findByEmail(studentEmail).blockOptional().isPresent)
        {
            actor.reply("There is no student registered with this email!")
            return
        }

        val clazz = classRepository.findByRandomId(classCode).blockOptional()

        if (!clazz.isPresent)
        {
            actor.reply("Invalid class code!")
            return
        }

        val student = studentRepository.findByEmail(studentEmail).block()!!

        classController.joinClass(student, clazz.get().randomId)
        actor.reply("Made student join class")
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