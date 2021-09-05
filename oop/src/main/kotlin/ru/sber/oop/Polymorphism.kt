package ru.sber.oop

import kotlin.random.Random

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
       get() = Random.nextInt()

    fun attack(opponent: Fightable): Int
}

class Player(
    val name: String,
    val isBlessed: Boolean = false,
    override val powerType: String,
    override var healthPoints: Int
) : Fightable {
    override fun attack(opponent: Fightable): Int {
        val damageAmount = if (isBlessed) {
            damageRoll
        } else {
            damageRoll * 2
        }
        return damageAmount
    }
}

abstract class Monster(
    open val name: String,
    open val description: String,
) : Fightable {
    override fun attack(opponent: Fightable): Int {
        val damageAmount = damageRoll
        return damageAmount
    }

}

fun Monster.getSalutation() {
    println("Hello everybody !! Glad to see you all !")
}

class Goblin(
    override val name: String,
    override val description: String,
    override val powerType: String,
    override var healthPoints: Int
) : Monster(name, description) {
    constructor() : this(
        name = "Goblin Vasya",
        description = "I'm a Goblin",
        powerType = "",
        healthPoints = 100,
    ) {}

    override val damageRoll: Int
        get() = super.damageRoll / 2


}




