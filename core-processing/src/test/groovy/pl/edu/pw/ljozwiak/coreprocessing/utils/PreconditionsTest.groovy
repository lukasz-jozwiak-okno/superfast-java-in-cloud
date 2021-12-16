package pl.edu.pw.ljozwiak.coreprocessing.utils

import spock.lang.Specification

class PreconditionsTest extends Specification {

  def 'should throw NullPointerException if null'() {
    when:
      Preconditions.checkNotNull(null, "null value passed")
    then:
      def npe = thrown(NullPointerException)
      npe.message == "null value passed"
  }

  def 'should not throw any exception if not null'() {
    when:
      Preconditions.checkNotNull("not null object", "null value passed")
    then:
      noExceptionThrown()
  }
}
