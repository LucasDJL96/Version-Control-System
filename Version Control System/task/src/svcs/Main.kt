package svcs

fun main(args: Array<String>) {
    VcsFiles.init()

    if (args.isEmpty()) {
        Command.HELP.controller.handleArguments(args.drop(1))
    } else if (args[0] in Command.fromString.keys) {
        Command.fromString[args[0]]!!.controller.handleArguments(args.drop(1))
    } else {
        println("'${args[0]}' is not a SVCS command.")
    }
}
