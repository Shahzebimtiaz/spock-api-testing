package util

import org.apache.http.HttpResponse

/**
 * Created by Shahzeb on 5/11/2021.
 */

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient

public class RequestHelper {

    public static final String DEFAULT_REST_ENDPOINT = 'https://jsonplaceholder.typicode.com/'
    public static final String REST_ENDPOINT;

    public static final users = [
            'admin'     :[
                    username : 'test@example.com',
                    password : 'test123',
                    tokens: null
            ]
    ]

    static {
        final Properties properties = new Properties()
        final InputStream propertiesStream = this.getClass().getResourceAsStream('/config.properties')
        if(propertiesStream != null){
            properties.load(propertiesStream)
        }
        if (properties.stringPropertyNames().contains('endpoint-url')){
            REST_ENDPOINT = properties.getProperty('endpoint-url')
        }else {
            REST_ENDPOINT = DEFAULT_REST_ENDPOINT
        }
    }

    public static RESTClient getClient(){
        final RESTClient client = new RESTClient(REST_ENDPOINT,'application/json')
        client.handler.failure = {
            final resp, final data ->
            return new HttpResponseDecorator((HttpResponse) resp, data);
        }
        return client
    }

    public static void validateUser(final String user){
        if (user == null){
            throw new IllegalArgumentException('The given user is null')
        }
        if (users[user] == null){
            throw new IllegalArgumentException(user + 'not available in util.RequestHelper.users');
        }
    }
}
