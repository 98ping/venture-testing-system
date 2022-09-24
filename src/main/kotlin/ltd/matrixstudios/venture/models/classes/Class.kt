package ltd.matrixstudios.venture.models.classes

import ltd.matrixstudios.venture.controllers.TeacherController
import ltd.matrixstudios.venture.models.Student
import ltd.matrixstudios.venture.models.Teacher
import java.util.UUID

data class Class(
    var randomId: String,
    var name: String,
    var createdAt: Long,
    var owner: UUID,
    var members: MutableList<Student>
)
{
    fun accesLiteralOwner() : Teacher?
    {
        return TeacherController.get(owner)
    }
}