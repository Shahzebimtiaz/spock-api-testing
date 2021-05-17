package putRequest

import groovy.json.internal.LazyMap
import groovyx.net.http.HttpResponseDecorator
import spock.lang.Unroll
import util.AbstractApiSpec
import putRequest.putUtil

import javax.xml.ws.Response

/**
 * Created by Shahzeb on 5/17/2021.
 */

class putRequestSpec extends AbstractApiSpec{
    static final String id = "1"
    static final String title = "foo"
    static final String bodyData  =  "bar"
    static  final String  userId = "1"

    @Unroll("#TestScenario")
    def "Put Request" (){
        given:
        final HttpResponseDecorator response = putUtil.putRequest(client, id, title, body, userId)

        expect:
        println response.data
        println response.status
        assert response.status == statusCode
        if(statusCode == 200){
            def result = response.data
            assert result['title'] == title
            assert result['body'] == bodyData
            assert result['userID'] == userId
        }else
        {
            final List<LazyMap> errors = (ArrayList) response.data['errors']
            assert errors.size() == 1
            final LazyMap error = errors[0]
            assert ((String) error['endUserMessage']).contains(errorMessage)
            println error['errorMessage']
        }

        where:
        TestScenario    |   id  | title |   body  |     userId  | statusCode
        'Happy Path'    |   id  | title | bodyData|     userId  | 200
    }
}
