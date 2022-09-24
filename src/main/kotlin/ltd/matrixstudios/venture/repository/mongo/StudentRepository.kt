package ltd.matrixstudios.venture.repository.mongo

import ltd.matrixstudios.venture.models.Student
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface StudentRepository : ReactiveMongoRepository<Student, UUID>
{
    fun findByName(username: String) : Student
}
