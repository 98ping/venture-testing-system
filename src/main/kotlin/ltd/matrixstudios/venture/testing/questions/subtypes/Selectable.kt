package ltd.matrixstudios.venture.testing.questions.subtypes

import ltd.matrixstudios.venture.testing.questions.Question

class Selectable(
    var prompt: String,
    var earnedPoints: Int,
    var correctOption: String,
    var studentsOption: String?,
    questionPoints: Int,
    number: Int
) : Question(questionPoints, number)