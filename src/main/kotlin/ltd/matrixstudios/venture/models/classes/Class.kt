package ltd.matrixstudios.venture.models.classes

import ltd.matrixstudios.venture.controllers.TeacherController
import ltd.matrixstudios.venture.models.Student
import ltd.matrixstudios.venture.models.Teacher
import ltd.matrixstudios.venture.testing.Test
import org.springframework.data.annotation.Id
import java.util.UUID

data class Class(
    @Id var randomId: String,
    var name: String,
    var createdAt: Long,
    var owner: UUID,
    var members: MutableList<Student>,
    var tests: MutableList<Test>
)