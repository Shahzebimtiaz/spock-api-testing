package postRequest

import groovy.json.JsonBuilder
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient

/**
 * Created by Shahzeb on 5/17/2021.
 */
class postUtil {
    public static HttpResponseDecorator postRequest(final RESTClient client, final String title, final String bodyData,
                                                   final String userId){
        def body = [
                title: title,
                body: bodyData,
                userID: userId
        ]
        println new JsonBuilder(body).toPrettyString()
        return (HttpResponseDecorator) client.post(
                path: "posts",
                body: body
        )
    }
}
