package ltd.matrixstudios.venture.models

import org.springframework.data.annotation.Id
import java.util.UUID

data class Teacher(
    @Id var uuid: UUID,
    var name: String,
    var association: String,
    var email: String,
    var password: String
)