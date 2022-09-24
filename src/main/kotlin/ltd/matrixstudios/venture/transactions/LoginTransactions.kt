package ltd.matrixstudios.venture.transactions

import ltd.matrixstudios.venture.controllers.StudentController
import ltd.matrixstudios.venture.controllers.TeacherController
import ltd.matrixstudios.venture.models.Teacher
import java.util.*

object LoginTransactions {

    fun emailIsIllegal(email: String) : Boolean
    {
        if (!email.contains("@")) return true

        //add more intricate things

        return false
    }

    fun signUpIntructor(
        email: String,
        instructorName: String,
        password: String
    )
    {
        TeacherController.loadReactiveProfile(
            UUID.randomUUID(),
            instructorName,
            email,
            password
        )
    }

    fun signUpStudent(
        email: String,
        name: String,
        password: String
    )
    {
        StudentController.loadReactiveProfile(
            UUID.randomUUID(),
            name,
            email,
            password
        )
    }
}