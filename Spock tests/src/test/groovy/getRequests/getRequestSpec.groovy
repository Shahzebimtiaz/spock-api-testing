package getRequests

import groovy.json.internal.LazyMap
import groovyx.net.http.HttpResponseDecorator
import spock.lang.Unroll
import util.AbstractApiSpec
import getRequests.getUtil

/**
 * Created by Shahzeb on 5/11/2021.
 */
class getRequestSpec extends AbstractApiSpec{

    @Unroll("#TestScenario")
    def 'Get_Posts'(){
        given:
        final HttpResponseDecorator response = getUtil.getPost(client)

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
        TestScenario                      |   userID  |   Id  |   title                                                                           |   body                                                                                                                                                                | statusCode
        'Happy Path for Get all Posts'    |   1       |   1   |   "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"    | "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"| 200
    }
    @Unroll("#TestScenario")
    def 'GET_POST_BY_ID'(){
        given:
        final HttpResponseDecorator response = getUtil.getByID(client)

        expect:
        assert  response.status == statusCode
        if (statusCode == 200){
            println(response.data)
        }else {
            final List<LazyMap> errors = (ArrayList) response.data['errors']
            assert errors.size() == 1
            final LazyMap error = errors[0]
            assert ((String) error['endUserMessage']).contains(errorMessage)
            println error['errorMessage']
        }

        where:
        TestScenario    |   userID  |   Id    | statusCode
        'Happy Path'    |   1       |   1     | 200
    }
}
