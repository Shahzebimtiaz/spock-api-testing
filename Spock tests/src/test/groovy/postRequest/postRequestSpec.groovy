package postRequest

import groovy.json.internal.LazyMap
import groovyx.net.http.HttpResponseDecorator
import spock.lang.Unroll
import util.AbstractApiSpec
import postRequest.postUtil

/**
 * Created by Shahzeb on 5/17/2021.
 */
class postRequestSpec extends AbstractApiSpec {
    static final String title = "foo"
    static final String bodyData  =  "bar"
    static  final String  userId = "1"

    @Unroll("#TestScenario")
    def "Post Request" (){
        given:
        final HttpResponseDecorator response = postUtil.postRequest(client, title, body, userId)

        expect:
        println response.data
        println response.status
        assert response.status == statusCode
        if(statusCode == 201){
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
        TestScenario         | title |   body  |     userId  | statusCode
        'Happy Path'         | title | bodyData|     userId  | 201
    }
}
