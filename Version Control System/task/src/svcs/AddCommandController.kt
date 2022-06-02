package svcs

import java.io.File

/** Object for the Add command */
object AddCommandController : CommandController {

    private val indexFile = VcsFiles.INDEX.file

    override fun handleArguments(args: List<String>) {
        if (args.isEmpty()) {
            if (indexFile.readText().isEmpty()) {
                println("Add a file to the index.")
            } else {
                println("Tracked files:")
                for (fileName in indexFile.readLines()) {
                    println(fileName)
                }
            }
        } else {
            if (File(args[0]).exists()) {
                indexFile.appendText(args[0] + "\n")
                println("The file '${args[0]}' is tracked.")
            } else {
                println("Can't find '${args[0]}'.")
            }
        }
    }

}
