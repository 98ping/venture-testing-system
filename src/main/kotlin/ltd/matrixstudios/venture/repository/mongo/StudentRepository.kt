package ltd.matrixstudios.venture.repository.mongo

import ltd.matrixstudios.venture.models.Student
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@Repository
interface StudentRepository : ReactiveMongoRepository<Student, UUID>
{
    fun findByName(name: String) : Flux<Student>
    fun findByEmail(email: String) : Mono<Student>
}
