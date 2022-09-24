package ltd.matrixstudios.venture.testing.questions.subtypes

import ltd.matrixstudios.venture.testing.questions.Question

class Written(
    var questionPrompt: String,
    var studentsAnswer: String,
    questionPoints: Int,
    number: Int
) : Question(questionPoints, number)