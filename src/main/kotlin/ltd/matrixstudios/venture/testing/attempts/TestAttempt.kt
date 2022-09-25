package ltd.matrixstudios.venture.testing.attempts

import ltd.matrixstudios.venture.testing.Test
import ltd.matrixstudios.venture.testing.questions.Question
import ltd.matrixstudios.venture.testing.questions.subtypes.Selectable
import java.util.UUID

data class TestAttempt    (
    var createdAt: Long,
    var student: UUID,
    var allocatedTimeframe: Long,
    var canSeeAnswersWhenDone: Boolean,
    var gradeWhenNotCompleteInTime: Int,
    var userFinalGrade: Int = 0,
    var answers: MutableList<Question>,
    var owningTest: Test,
    var completedAt: Long
)
{
    fun getFinalPoints() : Int
    {
        var finalPoints = 0

        for (question in answers)
        {
            if (question is Selectable)
            {
                val correct = question.correctOption
                val whatTheyChose = question.studentsOption

                //didnt put an answer what dweeb
                if (whatTheyChose != null)
                {
                    if (correct.equals(whatTheyChose, ignoreCase = true))
                    {
                        finalPoints = (finalPoints + question.earnedPoints)
                    }
                }
            }
        }

        return finalPoints
    }

    fun getAllPointsForQuestions() : Int
    {
        var pointIndex = 0

        for (question in answers)
        {
            pointIndex = (pointIndex + question.points)
        }

        return pointIndex
    }

    fun getFinalGrade() : Int
    {
        var totalPointIndex = 0

        for (question in answers)
        {
            if (question is Selectable)
            {
                val correct = question.correctOption
                val whatTheyChose = question.studentsOption

                //didnt put an answer what a dweeb
                if (whatTheyChose != null)
                {
                    if (correct.equals(whatTheyChose, ignoreCase = true))
                    {
                       totalPointIndex = (totalPointIndex + question.points)
                    }
                }
            }
        }

        return divideForGrade(getAllPointsForQuestions(), totalPointIndex)
    }

    fun divideForGrade(top: Int, bottom: Int) : Int
    {
        val dividedValue = (top / bottom).toDouble()

        //incase the student is really bad and just gets nothing right LOL
        if (dividedValue.isNaN() || dividedValue.isInfinite())
        {
            return 0
        }

        return dividedValue.toInt()
    }
}