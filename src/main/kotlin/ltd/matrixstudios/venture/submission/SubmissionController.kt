package ltd.matrixstudios.venture.submission

import ltd.matrixstudios.venture.models.Student
import ltd.matrixstudios.venture.testing.Test
import ltd.matrixstudios.venture.testing.attempts.TestAttempt
import ltd.matrixstudios.venture.testing.questions.subtypes.Selectable

object SubmissionController
{

    fun startATest(student: Student, test: Test) : TestAttempt
    {
        val attempt = TestAttempt(
            System.currentTimeMillis(),
            student.identifier,
            test.timeFrame,
            false,
            0,
            0, test.questions, test,
            0L
        )

        student.allTests.add(attempt)

        return attempt
    }

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
                } else
                {
                    answer.earnedPoints = 0
                }
            }
        }

        testAttempt.completedAt = System.currentTimeMillis()
        testAttempt.userFinalGrade = testAttempt.getFinalGrade()
    }
}