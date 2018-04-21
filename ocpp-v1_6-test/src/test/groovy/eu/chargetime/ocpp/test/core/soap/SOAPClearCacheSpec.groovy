package eu.chargetime.ocpp.test.core.soap

import spock.util.concurrent.PollingConditions

class SOAPClearCacheSpec extends SOAPBaseSpec {
    def setup() {
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")
    }

    def "Central System sends a ClearCache request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        given:
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        centralSystem.sendClearCacheRequest()

        then:
        conditions.eventually {
            assert chargePoint.hasHandledClearCacheRequest()
            assert centralSystem.hasReceivedClearCacheConfirmation()
        }
    }
}
