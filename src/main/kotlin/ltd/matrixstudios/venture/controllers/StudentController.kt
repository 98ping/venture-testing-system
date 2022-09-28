package ltd.matrixstudios.venture.controllers

import ltd.matrixstudios.venture.models.Student
import ltd.matrixstudios.venture.repository.mongo.StudentRepository
import ltd.matrixstudios.venture.repository.mongo.tests.TestAttemptRepository
import ltd.matrixstudios.venture.testing.attempts.TestAttempt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import java.util.UUID
import javax.annotation.PostConstruct

@Service
class StudentController
{

    @Autowired lateinit var studentRepository: StudentRepository
    @Autowired lateinit var testAttemptRepository: TestAttemptRepository

    private val cache = mutableMapOf<UUID, Student>()


    fun findStudentTestAttempts(student: Student) : MutableList<TestAttempt>?
    {
        return testAttemptRepository!!.findAll().filter {
            it.student.toString() == student.identifier.toString()
        }.collectList().block()
    }

    fun cacheAndSave(student: Student)
    {
        cache[student.identifier] = student
        studentRepository.save(student).subscribe()
    }


    fun exists(uuid: UUID) : Boolean?
    {
        return studentRepository.existsById(uuid).block()
    }

    fun loadReactiveProfile(uuid: UUID, username: String, email: String, password: String) : Student
    {
        if (cache.containsKey(uuid)) return cache[uuid]!!

        val profile = studentRepository.findById(uuid).block()

        if (profile != null)
        {
            return profile
        }

        return Student(UUID.randomUUID(), username, email, password)
    }
}