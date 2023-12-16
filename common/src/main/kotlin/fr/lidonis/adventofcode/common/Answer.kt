package fr.lidonis.adventofcode.common

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Answer(val answer: String = "")
