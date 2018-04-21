package eu.chargetime.ocpp.test.core.soap

import spock.util.concurrent.PollingConditions

class SOAPMeterValuesSpec extends SOAPBaseSpec {

    def "Charge point sends MeterValues request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        chargePoint.sendMeterValuesRequest()

        then:
        conditions.eventually {
            assert centralSystem.hasHandledMeterValuesRequest()
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedMeterValuesConfirmation()
        }

    }

}
