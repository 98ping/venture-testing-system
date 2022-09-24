package ltd.matrixstudios.venture.testing.attempts

import ltd.matrixstudios.venture.testing.Test
import ltd.matrixstudios.venture.testing.questions.Question
import ltd.matrixstudios.venture.testing.questions.subtypes.Selectable

data class TestAttempt(
    var createdAt: Long,
    var allocatedTimeframe: Long,
    var canSeeAnswersWhenDone: Boolean,
    var gradeWhenNotCompleteInTime: Int,
    var userFinalGrade: Int = 0,
    var answers: MutableList<Question>,
    var owningTest: Test
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

    fun getFinalGrade() : Int
    {
        val correctQuestionList = mutableListOf<Question>()

        var finalGrade = gradeWhenNotCompleteInTime

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
                        correctQuestionList.add(question)
                    }
                }
            }
        }

        for (correctQuestion in correctQuestionList)
        {
            val totalQuestions = answers.size

            val unweightedGrade = totalQuestions / correctQuestionList.size

            finalGrade = unweightedGrade
        }

        return finalGrade
    }
}