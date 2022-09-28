package ltd.matrixstudios.venture.controllers

import ltd.matrixstudios.venture.models.Student
import ltd.matrixstudios.venture.models.Teacher
import ltd.matrixstudios.venture.models.classes.Class
import ltd.matrixstudios.venture.repository.mongo.`class`.ClassRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClassController
{
    @Autowired lateinit var classRepository: ClassRepository

    fun createClass(teacher: Teacher, clazz: Class)
    {
        classRepository.save(clazz).subscribe()
    }

    fun joinClass(student: Student, code: String)
    {
        val optional = classRepository.findByRandomId(code).blockOptional()

        if (optional.isPresent)
        {
            val clazz = optional.get()

            clazz.members.add(student)

            classRepository.save(clazz).subscribe()
        }
    }
}