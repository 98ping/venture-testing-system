package ltd.matrixstudios.venture.routes.student

import ltd.matrixstudios.venture.repository.mongo.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin(origins = ["*"], allowCredentials = "false", allowedHeaders = ["*"])
@RequestMapping("/api/v1/student")
class StudentRoutes
{
    @Autowired lateinit var studentRepository: StudentRepository

    @RequestMapping("/emailTaken")
    fun emailTaken(@RequestParam("email") email: String) : Boolean
    {
        return studentRepository.findAll().filter { it.email == email }.blockFirst() != null
    }

    @GetMapping("/fetch/{identifier}")
    fun fetchStringIdentifier(@PathVariable identifier: String) = studentRepository.findById(UUID.fromString(identifier)).block()
}