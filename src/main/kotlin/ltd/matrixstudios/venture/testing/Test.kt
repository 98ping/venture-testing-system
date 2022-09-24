package ltd.matrixstudios.venture.testing

import ltd.matrixstudios.venture.testing.questions.Question
import java.util.UUID

data class Test(
    var randomId: UUID,
    var name: String,
    var questions: MutableList<Question>
) {
}