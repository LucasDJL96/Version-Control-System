package svcs

/**
 * Enum representing the distinct commands
 *
 * @property command string representing the command line command
 * @property helpMessage the message used for the help command
 * @property controller the controller for this command
 */
enum class Command(val command: String, val helpMessage: String, val controller: CommandController) {

    /** Config command */
    CONFIG("config", "Get and set a username.", ConfigCommandController),

    /** Add command */
    ADD("add", "Add a file to the index.", AddCommandController),

    /** Log command */
    LOG("log", "Show commit logs.", LogCommandController),

    /** Commit command */
    COMMIT("commit", "Save changes.", CommitCommandController),

    /** Config command */
    CHECKOUT("checkout", "Restore a file.", CheckoutCommandController),

    /** Help command */
    HELP("--help", "These are SVCS commands:", HelpCommandController);

    companion object {
        /** A map mapping from the command line command to the corresponding object */
        val fromString = buildMap {
            for (command in Command.values()) {
                put(command.command, command)
            }
        }
    }

}
