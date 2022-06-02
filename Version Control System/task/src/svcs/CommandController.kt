package svcs

/** Interface representing controllers for the commands */
interface CommandController {

    /**
     * Function to perform the command function
     *
     * @param args the arguments the command is called with
     */
    fun handleArguments(args: List<String>)

}
