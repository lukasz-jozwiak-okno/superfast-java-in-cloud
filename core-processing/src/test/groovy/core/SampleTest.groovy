package core

import spock.lang.Specification

class SampleTest extends Specification {

    def 'should say hello'() {
        given:
            def sample = new Sample()
        expect:
            sample.doSomething() == 'Hello world'
    }
}
