package svcs

import java.io.File

/** Enum class representing the files used by the VCS */
enum class VcsFiles(val file: File, private val isDir: Boolean) {

    /** vcs main directory */
    VCS(File("vcs"), true),

    /** commits directory */
    COMMITS(VCS.file.resolve("commits"), true),

    /** config file */
    CONFIG(VCS.file.resolve("config.txt"), false),

    /** index file */
    INDEX(VCS.file.resolve("index.txt"), false),

    /** log file */
    LOG(VCS.file.resolve("log.txt"), false),

    /** head file with the current commit hash */
    HEAD(VCS.file.resolve("head.txt"), false);

    companion object {

        /** Initializes the files, creating them if necessary */
        fun init() {
            for (file in VcsFiles.values()) {
                if (!file.file.exists()) {
                    if (file.isDir) file.file.mkdir()
                    else file.file.createNewFile()
                }
            }
        }
    }

}
