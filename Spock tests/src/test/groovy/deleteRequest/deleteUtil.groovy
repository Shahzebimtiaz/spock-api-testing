package deleteRequest

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient

/**
 * Created by Shahzeb on 5/17/2021.
 */
class deleteUtil {
    public static HttpResponseDecorator deletePost(final RESTClient client){
        return (HttpResponseDecorator) client.delete(
                path: "posts/1"
        )
    }
}
