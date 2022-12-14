package ltd.matrixstudios.venture.models

import ltd.matrixstudios.venture.testing.attempts.TestAttempt
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document
data class Student(
    @Id val identifier: UUID,
    val name: String,
    val email: String,
    val password: String
)