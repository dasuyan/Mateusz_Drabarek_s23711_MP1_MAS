import java.time.LocalDate

fun main() {
    Cat.showCatExtent() // can be called without any existing Cat instances, is empty
    println()

    val cat1 = Cat("Pruti", "Hunter", LocalDate.of(2020, 6, 10), "Female", "Tricolore", mutableListOf("passport", "book of health", "vaccination certificate"))
    val cat2 = Cat("Leoś", null, LocalDate.of(2021, 10, 28), "Male", "Orange Tabby", mutableListOf("list of allergies", "book of health", "vaccination certificate"))

    println("\nWe don't accept patients, who are ${ Cat.maxAge } years old or older\n")

    Cat.showCatExtent() // is automatically filled with all the created cat instances

    println()
    val definitelyNotACat = Animal()
    definitelyNotACat.makeSound()

    cat1.makeSound()
    cat2.makeSound(3)
    println("\n#####################################################################################################")
    // Cat.writeExtent()
    Cat.readExtent()
}