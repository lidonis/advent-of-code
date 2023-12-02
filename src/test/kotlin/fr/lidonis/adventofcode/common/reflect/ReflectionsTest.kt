package fr.lidonis.adventofcode.common.reflect

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReflectionsTest {

    @Test
    fun `should return all subclasses of abstract class`() {
        val allSubclasses = getAllSubclasses<AbstractClass>()
        assertThat(allSubclasses)
            .containsExactlyInAnyOrder(
                ConcreteClass1::class.java,
                ConcreteClass2::class.java,
            )
    }


    abstract class AbstractClass {
        abstract fun function()
    }

    class ConcreteClass1 : AbstractClass() {
        override fun function() {
            println("Hello World")
        }
    }

    class ConcreteClass2 : AbstractClass() {
        override fun function() {
            println("Hello World")
        }
    }
}