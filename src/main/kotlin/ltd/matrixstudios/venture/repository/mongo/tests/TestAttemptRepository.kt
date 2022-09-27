package ltd.matrixstudios.venture.repository.mongo.tests

import ltd.matrixstudios.venture.testing.attempts.TestAttempt
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TestAttemptRepository : ReactiveMongoRepository<TestAttempt, UUID>