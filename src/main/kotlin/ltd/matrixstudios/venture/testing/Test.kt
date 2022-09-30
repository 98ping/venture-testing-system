package ltd.matrixstudios.venture.testing

import ltd.matrixstudios.venture.testing.questions.Question
import org.springframework.data.annotation.Id
import java.util.UUID

data class Test(
    @Id var randomId: UUID,
    var name: String,
    var timeFrame: Long,
    var questions: MutableList<Question>
)