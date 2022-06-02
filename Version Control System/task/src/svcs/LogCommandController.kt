package svcs

/** Object for the Log command */
object LogCommandController : CommandController {

    private val logFile = VcsFiles.LOG.file

    override fun handleArguments(args: List<String>) {
        if (logFile.readText().isEmpty()) {
            println("No commits yet.")
            return
        }
        for (log in logFile.readLines().reversed()) {
            println(log)
        }
    }

}
