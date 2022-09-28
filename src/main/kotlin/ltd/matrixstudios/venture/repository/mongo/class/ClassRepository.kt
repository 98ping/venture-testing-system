package ltd.matrixstudios.venture.repository.mongo.`class`

import ltd.matrixstudios.venture.models.classes.Class
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
interface ClassRepository : ReactiveMongoRepository<Class, String> {

    fun findByRandomId(randomId: String) : Mono<Class>
}