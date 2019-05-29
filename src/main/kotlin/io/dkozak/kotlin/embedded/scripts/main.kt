package io.dkozak.kotlin.embedded.scripts

import de.swirtz.ktsrunner.objectloader.KtsObjectLoader
import io.dkozak.kotlin.embedded.scripts.model.Coffee
import org.assertj.core.api.Assertions.assertThat
import javax.script.ScriptEngineManager

class Dummy

fun String.asResource() = Dummy::class.java.getResource(this)

fun main() {
    val fileName = "/coffeeList.kts"

    val script = fileName.asResource().readText()

    val coffeeList = KtsObjectLoader().load<List<Coffee>>(script)
    println(coffeeList)
    assertThat(coffeeList).hasSize(3)
}

private fun simpleExample() {
    // just execute a few expressions
    with(ScriptEngineManager().getEngineByExtension("kts")) {
        requireNotNull(this)

        eval("val x = 2")
        val res = eval("x + 2")
        println(res)
        assertThat(res).isEqualTo(4)
    }
}