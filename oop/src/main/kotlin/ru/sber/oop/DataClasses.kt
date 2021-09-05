package ru.sber.oop

import java.lang.reflect.Array.set

data class User(val name: String, val age: Long) {
    lateinit var city: String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User)
            return false
        else {
            if (other.city == this.city)
                return true
        }
        return false
    }
}

fun main() {
    val user1 = User("Alex", 13)
    val user2 = user1.copy("Nikita")
    user1.city = "Omsk"
    val user3 = user1.copy()
    user3.city = "Tomsk"
}

