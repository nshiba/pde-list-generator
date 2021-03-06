import java.io.File
import java.nio.file.Files

val file: File = File("./index.html").absoluteFile
val visitor = MyVisitor(File("./data/no2/").toPath()) // your target file path

fun main(args: Array<String>) {
    Files.walkFileTree(visitor.path, visitor)
    file.writeText(createHtml(visitor.body + "</h2>"))
}

// http://processingjs.org/
fun createHtml(body: String): String =
        """<!DOCTYPE html>
        <html>
        <head>
            <meta charset="utf-8">
            <title>Processing Submitted List</title>
            <script src="./processing.js"></script>
            <script src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js"></script>
        </head>
        <body>$body</body></html>"""
