package svcs

/** Object for the Help command */
object HelpCommandController : CommandController{

    override fun handleArguments(args: List<String>) {
        println(Command.HELP.helpMessage)
        for (command in Command.values()) {
            if (command != Command.HELP) {
                println(command.name.lowercase().padEnd(11) + command.helpMessage)
            }
        }
    }

}
