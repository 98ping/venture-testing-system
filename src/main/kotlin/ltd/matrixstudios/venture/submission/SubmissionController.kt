package ltd.matrixstudios.venture.submission

import ltd.matrixstudios.venture.models.Student
import ltd.matrixstudios.venture.testing.attempts.TestAttempt
import ltd.matrixstudios.venture.testing.questions.subtypes.Selectable

object SubmissionController
{
    fun submitATest(testAttempt: TestAttempt)
    {
        val questions = testAttempt.answers

        for (answer in questions)
        {
            if (answer is Selectable)
            {
                if (answer.correctOption == answer.studentsOption)
                {
                    //they earn full credit for the question
                    answer.earnedPoints = answer.points
                }
            }
        }

        testAttempt.completedAt = System.currentTimeMillis()
        testAttempt.userFinalGrade = testAttempt.getFinalGrade()
    }
}