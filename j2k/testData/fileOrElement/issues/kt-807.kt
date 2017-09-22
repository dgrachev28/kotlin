// ERROR: None of the following functions can be called with the arguments supplied:  public constructor FileInputStream(file: File!) defined in java.io.FileInputStream public constructor FileInputStream(fdObj: FileDescriptor!) defined in java.io.FileInputStream public constructor FileInputStream(name: String!) defined in java.io.FileInputStream
// ERROR: Type mismatch: inferred type is DataInputStream but InputStream! was expected
// ERROR: Unresolved reference: close
import java.io.*

internal object FileRead {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val fstream = FileInputStream()
            val `in` = DataInputStream(fstream)
            val br = BufferedReader(InputStreamReader(`in`))
            val strLine: String
            strLine = br.readLine()
            while (strLine != null) {
                println(strLine)
            }
            `in`.close()
        } catch (e: Exception) {
            System.err.println("Error: " + e.message)
        }

    }
}
