package getRequests

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient

/**
 * Created by Shahzeb on 5/11/2021.
 */
class getUtil{
    public static HttpResponseDecorator getPost(final RESTClient client){
        return (HttpResponseDecorator) client.get(
                path: "posts"
        )
    }

    public static HttpResponseDecorator getByID(final RESTClient client){
        return (HttpResponseDecorator) client.get(
                path:"posts/1"
        )
    }
}
