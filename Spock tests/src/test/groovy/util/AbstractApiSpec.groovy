package util

import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by Shahzeb on 5/17/2021.
 */
abstract class AbstractApiSpec extends Specification{
    @Shared
    RESTClient client = RequestHelper.getClient()
}
