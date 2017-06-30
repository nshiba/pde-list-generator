import java.io.File
import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

class MyVisitor(val path: Path, var body: String = ""): SimpleFileVisitor<Path>() {

    private val root = File("./")

    override fun visitFile(file: Path?, attrs: BasicFileAttributes?): FileVisitResult {
        val relativePath = root.toURI().relativize(file?.toUri()).toString()
        if (relativePath.contains(".pde")) {
            body += """<pre class="prettyprint style="background: lightgray;">${createCode(file?.toFile())}</pre>"""
            body += """<canvas data-processing-sources="$relativePath"></canvas>"""
        }
        return super.visitFile(file, attrs)
    }

    override fun preVisitDirectory(dir: Path?, attrs: BasicFileAttributes?): FileVisitResult {
        body += "<h2>${dir?.fileName}</h2>"
        return super.preVisitDirectory(dir, attrs)
    }

    fun createCode(file: File?): String {
        var code = ""
        file?.forEachLine {
            code += it + "</br>"
        }
        return code
    }
}
