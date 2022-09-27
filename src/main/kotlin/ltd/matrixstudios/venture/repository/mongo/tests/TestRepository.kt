package ltd.matrixstudios.venture.repository.mongo.tests


import ltd.matrixstudios.venture.testing.Test
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TestRepository : ReactiveMongoRepository<Test, UUID>