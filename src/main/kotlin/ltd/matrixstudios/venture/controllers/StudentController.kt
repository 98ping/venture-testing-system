package ltd.matrixstudios.venture.controllers

import ltd.matrixstudios.venture.models.Student
import ltd.matrixstudios.venture.repository.mongo.StudentRepository
import java.util.UUID

object StudentController
{
    private val repository: StudentRepository? = null

    private val cache = mutableMapOf<UUID, Student>()

    fun exists(uuid: UUID) : Boolean?
    {
        return repository!!.existsById(uuid).block()
    }

    fun loadReactiveProfile(uuid: UUID, username: String, email: String, password: String) : Student
    {
        if (cache.containsKey(uuid)) return cache[uuid]!!

        val profile = repository!!.findById(uuid).blockOptional()

        if (profile.isPresent)
        {
            return profile.get()
        }

        return Student(UUID.randomUUID(), username, email, password, mutableListOf())
    }
}