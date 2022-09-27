package ltd.matrixstudios.venture.submission

import ltd.matrixstudios.venture.models.Student
import ltd.matrixstudios.venture.testing.Test
import ltd.matrixstudios.venture.testing.attempts.TestAttempt
import ltd.matrixstudios.venture.testing.questions.subtypes.Selectable
import org.springframework.stereotype.Service

@Service
object SubmissionController
{

    fun startATest(student: Student, test: Test): TestAttempt {
        return TestAttempt(
            System.currentTimeMillis(),
            student.identifier,
            test.timeFrame,
            false,
            0,
            0, test.questions, test,
            0L
        )
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