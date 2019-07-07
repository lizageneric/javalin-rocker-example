package app

import com.fizzed.rocker.Rocker
import io.javalin.Javalin
import io.javalin.rendering.FileRenderer
import io.javalin.rendering.JavalinRenderer
import io.javalin.rendering.template.TemplateUtil.model

fun main() {
    JavalinRenderer.register(FileRenderer { filepath, model -> Rocker.template(filepath).bind(model).render().toString()}, ".rocker.html")
    val app = Javalin.create().start(7000)
    app.get("/") {ctx ->
        ctx.render("templates/root.rocker.html")
    }
    app.get("/hello") { ctx ->
        ctx.render("templates/demo.rocker.html", model("message", "Hello Rocker from Kotlin!"))
    }
    app.get("/hello2") {ctx ->
        ctx.html(templates.demo.template("Hi from Kotlin! I am using a compiler-checked version!").render().toString())
    }
}