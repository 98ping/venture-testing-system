package ltd.matrixstudios.venture.repository.mongo

import ltd.matrixstudios.venture.models.Student
import ltd.matrixstudios.venture.models.Teacher
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.UUID

@Repository
interface TeacherRepository : ReactiveMongoRepository<Teacher, UUID>
{
    fun findByName(username: String) : Teacher
    fun findByEmail(email: String) : Mono<Teacher>
}
