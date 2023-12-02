package fr.lidonis.adventofcode.common.reflect

import java.io.File

inline fun <reified T : Any> getAllSubclasses(): List<Class<out T>> {
    return getSubtypes(T::class.java)
}

inline fun <reified T> getSubtypes(type: Class<T>): List<Class<out T>> {
    val packageName = type.`package`.name
    return getClasses(type, packageName)
        .filter { type.isAssignableFrom(it) && it != type }
}

fun <T> getClasses(type: Class<T>, packageName: String): List<Class<out T>> {
    val classLoader = Thread.currentThread().contextClassLoader
    val path = packageName.replace('.', '/')
    val resources = classLoader.getResources(path)
    val dirs = mutableListOf<File>()

    while (resources.hasMoreElements()) {
        val resource = resources.nextElement()
        dirs.add(File(resource.file))
    }

    val classes = mutableListOf<Class<out T>>()

    for (directory in dirs) {
        classes.addAll(findClasses(type, directory, packageName))
    }

    return classes
}

fun <T> findClasses(type: Class<T>, directory: File, packageName: String): List<Class<out T>> {
    val classes = mutableListOf<Class<out T>>()

    if (!directory.exists()) {
        return classes
    }

    val files = directory.listFiles() ?: return classes

    for (file in files) {
        if (file.isDirectory) {
            classes.addAll(findClasses(type, file, "$packageName.${file.name}"))
        } else if (file.name.endsWith(".class")) {
            val className = "${packageName}.${file.name.substring(0, file.name.length - 6)}"
            try {
                classes.add(Class.forName(className).asSubclass(type))
            } catch (e: ClassNotFoundException) {
                // Handle the exception, if necessary
            } catch (e: ClassCastException) {
                // Handle the exception, if necessary
            }
        }
    }

    return classes
}