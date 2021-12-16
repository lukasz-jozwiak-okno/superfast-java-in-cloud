package e2e

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.util.regex.Pattern

import static io.restassured.RestAssured.given
import static org.hamcrest.CoreMatchers.containsString

@Unroll
class ServicesE2E extends Specification {

  @Shared
  def serviceUrls = getListOfServices()

  def "should return report of all telemetries for #shortUrl"() {
    expect:
      given().get("${url}/batch")
          .then()
          .statusCode(200)
          .body(containsString("Report"))
    where:
      url << serviceUrls
      shortUrl = shortenUrl(url)
  }

  def "should return report of single telemetry for #shortUrl"() {
    expect:
      given().get("${url}/single")
          .then()
          .statusCode(200)
          .body(containsString("Report"))
    where:
      url << serviceUrls
      shortUrl = shortenUrl(url)
  }

  def "should return ping for #shortUrl"() {
    expect:
      given().get("${url}/ping")
          .then()
          .statusCode(200)
          .body(containsString("ping"))
    where:
      url << serviceUrls
      shortUrl = shortenUrl(url)
  }

  static Set<String> getListOfServices() {
    def urls = new File('services.txt').readLines().findAll { !it.isBlank() }.toSet()

    assert !urls.isEmpty()

    urls
  }

  static String shortenUrl(String url) {
    def pattern = Pattern.compile("https://(.*-native|.*-jvm)")
    def matcher = pattern.matcher(url)

    matcher.find()
    matcher.group(1)
  }
}
