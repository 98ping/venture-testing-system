package ltd.matrixstudios.venture.repository.mongo.`class`

import ltd.matrixstudios.venture.models.Teacher
import ltd.matrixstudios.venture.models.classes.Class
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClazzRepository : ReactiveMongoRepository<Class, UUID> {
}