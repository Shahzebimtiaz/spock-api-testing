package deleteRequest

import groovy.json.internal.LazyMap
import groovyx.net.http.HttpResponseDecorator
import spock.lang.Unroll
import util.AbstractApiSpec
import deleteRequest.deleteUtil

/**
 * Created by Shahzeb on 5/17/2021.
 */
class deleteRequestSpec extends AbstractApiSpec{
    @Unroll("#TestScenario")
    def 'Delete Post'(){
        given:
        final HttpResponseDecorator response = deleteUtil.deletePost(client)

        expect:
        assert response.status == statusCode

        if (statusCode == 200){
            println response.data
        }else {
            final List<LazyMap> errors = (ArrayList) response.data['errors']
            assert errors.size() == 1
            final LazyMap error = errors[0]
            assert ((String) error['endUserMessage']).contains(errorMessage)
            println error['errorMessage']
        }

        where:
        TestScenario                      | statusCode
        'Happy Path'                      | 200
    }
}
