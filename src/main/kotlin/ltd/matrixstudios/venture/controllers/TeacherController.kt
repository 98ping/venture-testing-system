package ltd.matrixstudios.venture.controllers

import ltd.matrixstudios.venture.models.Student
import ltd.matrixstudios.venture.models.Teacher
import ltd.matrixstudios.venture.repository.mongo.StudentRepository
import ltd.matrixstudios.venture.repository.mongo.TeacherRepository
import java.util.UUID

object TeacherController
{
    private val repository: TeacherRepository? = null

    private val cache = mutableMapOf<UUID, Teacher>()

    fun get(uuid: UUID) : Teacher?
    {
        if (cache.containsKey(uuid)) return cache[uuid]!!

        return repository!!.findById(uuid).block()
    }


    fun exists(uuid: UUID) : Boolean?
    {
        return repository!!.existsById(uuid).block()
    }

    fun loadReactiveProfile(uuid: UUID, username: String, email: String, password: String) : Teacher
    {
        if (cache.containsKey(uuid)) return cache[uuid]!!

        val profile = repository!!.findById(uuid).blockOptional()

        if (profile.isPresent)
        {
            return profile.get()
        }

        return Teacher(UUID.randomUUID(), username, "Change Me!", email, password)
    }
}