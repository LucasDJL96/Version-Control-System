package svcs

/** Object for the Config command */
object ConfigCommandController : CommandController {

    private val configFile = VcsFiles.CONFIG.file

    override fun handleArguments(args: List<String>) {
        if (args.isEmpty()) {
            if (configFile.readLines().isEmpty()) {
                println("Please, tell me who you are.")
            } else {
                println("The username is ${configFile.readLines()[0]}.")
            }
        } else {
            configFile.writeText(args[0])
            println("The username is ${args[0]}.")
        }
    }

}
