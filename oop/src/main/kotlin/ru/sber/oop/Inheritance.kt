package ru.sber.oop

open class Room(val name: String, val size: Int) {

    protected open val dangerLevel = 5

    var monster: Monster? = Goblin()

    fun description() = "Room: $name"

    open fun load() = {
        ->
        "Nothing much to see here..."
        monster?.getSalutation()
    }

    constructor(name: String) : this(name, size = 100) {
    }
}

class TownSquare : Room("TownSquare", 100) {
    override val dangerLevel = super.dangerLevel - 3
    final override fun load() = {
        print("There are a lot of interesting to see here")
    }
}



