package putRequest

import groovy.json.JsonBuilder
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient

/**
 * Created by Shahzeb on 5/17/2021.
 */
class putUtil {
    public static HttpResponseDecorator putRequest(final RESTClient client, final String id, final String title, final String bodyData,
                                                final String userId){
        def body = [
                id: id,
                title: title,
                body: bodyData,
                userID: userId
        ]
        println new JsonBuilder(body).toPrettyString()
        return (HttpResponseDecorator) client.put(
                path: "posts/1",
                body: body
        )
    }
}
