package svcs

import java.io.File

/** Object for the Checkout command */
object CheckoutCommandController : CommandController {

    private val commitDir = VcsFiles.COMMITS.file
    private val indexFile = VcsFiles.INDEX.file
    private val headFile = VcsFiles.HEAD.file

    override fun handleArguments(args: List<String>) {
        if (args.isEmpty()) {
            println("Commit id was not passed.")
            return
        }
        val commit = commitDir.resolve(args[0])
        if (!commit.exists()) {
            println("Commit does not exist.")
            return
        }
        for (fileName in indexFile.readLines()) {
            val file = File(fileName)
            val committedFile = commit.resolve(fileName)
            if (file.exists() && committedFile.exists()) {
                file.writeText(committedFile.readText())
            } else if (!file.exists()) {
                file.createNewFile()
                file.writeText(committedFile.readText())
            } else {
                file.delete()
            }
        }
        headFile.writeText(args[0])
        println("Switched to commit ${args[0]}.")
    }

}
