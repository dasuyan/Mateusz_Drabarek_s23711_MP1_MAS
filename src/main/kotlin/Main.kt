import java.time.LocalDate

fun main() {
    Cat.showCatExtent() // can be called without any existing Cat instances, is empty
    println()

    val cat1 = Cat("Pruti", "Hunter", LocalDate.of(2020, 3, 10), "Female", "Tricolore", mutableListOf("passport", "book of health", "vaccination certificate"))
    val cat2 = Cat("Leoś", null, LocalDate.of(2021, 10, 28), "Male", "Orange Tabby", mutableListOf("list of allergies", "book of health", "vaccination certificate"))

    println(cat1.age)
    println("\n#####################################################################################################")
    println("Multi-value property: ")
//    cat1.documents.forEach { println(it) }
    cat1.documents.forEach(::println)
    println("...and a part of a complex property, Pruti's month of birth : ${cat1.dateOfBirth.month}")
        .also { if (cat1.dateOfBirth.month == LocalDate.now().month) {
            println("Happy birthday, ${cat1.name}!")
            }
        }
    println("#####################################################################################################")

    println("\nWe don't accept patients, who are ${ Cat.maxAge } years old or older\n")

    Cat.showCatExtent() // is automatically filled with all the created cat instances
    println("Leoś doesn't have a surname, because it's optional. So surname = ${cat2.surname}")

    println("\n#####################################################################################################")
    val definitelyNotACat = Animal()
    definitelyNotACat.makeSound()

    cat1.makeSound()
    cat2.makeSound(3)
    println("\n#####################################################################################################")

    println("The birthday calendar: ${Cat.getMapOfBirthdays2()}")

     //Cat.writeExtent()
    Cat.readExtent()
}