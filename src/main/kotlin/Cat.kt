import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.io.File
import java.time.LocalDate

// For now all properties are read-only, because there's no need to change them in this demo
@Serializable
data class Cat( // a primary constructor
    val name: String, // a simple property
    val surname: String?, // an optional property
    @Contextual
    val dateOfBirth: LocalDate, // a complex property
    val sex: String,
    val breed: String,
    val documents: MutableList<String>, // a multi-value property
    ) : Animal() {

    init {
        addCat(this)
        val age: Int = (LocalDate.now().year - dateOfBirth.year).also{ println("$name is $it years old. Their hashCode = ${ hashCode() }") } // a computed/derived property
    }

    companion object {
        private var extent: MutableList<Cat> = mutableListOf() // the class extent, while also a class property
        private val module = SerializersModule {
            contextual(LocalDate::class, LocalDateSerializer)
        }
        private val json = Json { serializersModule = module }
        private val file = File("cats.json")

        const val maxAge = 50 // a class property and a compile-time constant

        fun showCatExtent() { // a class method
            if (extent.isEmpty()) {
                println("The extent is empty")
            } else {
                println("Extent of the class: ${ Cat::class.simpleName }")
                extent.forEach{println(it)}
            }
            /* when there are 3 or more options, it is preferable to use:
             /*
             when (extent.isEmpty()) {
                true -> println("The extent is empty")

                false -> { // or false -> {
                    [...]
                }
               }
              */
             */
        }

        private fun addCat(cat: Cat) {
            extent.add(cat)
        }

        private fun removeCat(cat: Cat) { // currently never used
            extent.remove(cat)
        }

        fun writeExtent() {
            val jsonString = json.encodeToString(extent)
            file.writeText(jsonString)
        }

        fun readExtent() {
            val loadedJsonString = file.readText()
            extent = json.decodeFromString(loadedJsonString)
            println()
            showCatExtent()
        }
    }

    override fun makeSound() { // a method override
        println("$name says: MEOW!")
    }

    fun makeSound(times: Int) { // a method overload
        print("$name says: ")
        repeat(times) {
            print("MEOW! ")
        }
    }
}
