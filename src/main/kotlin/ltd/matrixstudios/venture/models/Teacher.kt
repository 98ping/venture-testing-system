package ltd.matrixstudios.venture.models

import java.util.UUID

data class Teacher(
    var uuid: UUID,
    var name: String,
    var association: String,
    var email: String,
    var password: String
)