import org.junit.jupiter.api.Test
import java.net.URI
import java.util.*
import java.util.regex.Pattern

class UpdateCheckTest {

    @Test
    fun versionCheckTest() {
        val myVersion = "0.0.8"
        val resourceId = "XvDcVrRl"
        val uri = URI("https://api.modrinth.com/v2/project/$resourceId/version")

        val response = uri.toURL().openStream().use { Scanner(it).useDelimiter("\\A").next() }

        val versionPattern = Pattern.compile("\"version_number\":\"(.*?)\"")
        val typePattern = Pattern.compile("\"version_type\":\"(.*?)\"")

        val vMatcher = versionPattern.matcher(response)
        val tMatcher = typePattern.matcher(response)

        if (vMatcher.find() && tMatcher.find()) {
            val latestVersion = vMatcher.group(1)
            val type = tMatcher.group(1)

            println("----------------------------------------")
            println("최신 버전 번호: $latestVersion")
            println("최신 버전 타입: $type")
            println("----------------------------------------")

            if (myVersion < latestVersion) {
                println("결과: 업데이트 필요")
            } else {
                println("결과: 최신 버전임")
            }
        }
    }
}