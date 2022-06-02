package svcs

import java.io.File
import java.security.MessageDigest

/** Object for the Commit command */
object CommitCommandController : CommandController {

    private val commitDir = VcsFiles.COMMITS.file
    private val logFile = VcsFiles.LOG.file
    private val indexFile = VcsFiles.INDEX.file
    private val configFile = VcsFiles.CONFIG.file
    private val headFile = VcsFiles.HEAD.file

    private val messageDigest = MessageDigest.getInstance("SHA-256")

    private val headHash
        get() = headFile.readText()
    private val currentHash
        get() = currentHash()

    override fun handleArguments(args: List<String>) {
        if (args.isEmpty()) {
            println("Message was not passed.")
            return
        }
        if (indexFile.readText().isEmpty()) {
            println("No files tracked.")
            return
        }
        if (configFile.readText().isEmpty()) {
            println("No author information.")
            return
        }
        if (nothingToCommit()) {
            println("Nothing to commit.")
            return
        } else {
            headFile.writeText(currentHash)
            val commit = commitDir.resolve(headHash)
            commit.mkdir()
            for (fileName in indexFile.readLines()) {
                val file = File(fileName)
                val newFile = commit.resolve(fileName)
                newFile.writeText(file.readText())
            }
            logFile.appendText("${args[0]}\n")
            logFile.appendText("Author: ${configFile.readText()}\n")
            logFile.appendText("commit $headHash\n")
            logFile.appendText("\n")
            println("Changes are committed.")
        }
    }

    private fun nothingToCommit(): Boolean {
        return currentHash == headHash
    }

    private fun currentHash(): String {
        for (fileName in indexFile.readLines()) {
            val file = File(fileName)
            if (file.exists()) {
                messageDigest.update(file.readBytes())
            }
        }
        val hash = messageDigest.digest().toHex()
        messageDigest.reset()
        return hash
    }
}

/** Transforms a ByteArray to a String consisting of the hex values of each bite */
fun ByteArray.toHex(): String {
    return this.joinToString(separator = "") { "%02x".format(it + 128) }
}
