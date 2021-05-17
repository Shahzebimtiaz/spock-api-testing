# Introduction
This is a sample project to automate Rest api's using Spock framework. This project uses https://jsonplaceholder.typicode.com/ as the application for testing purpose. It is a free to use fake Online Rest API for testing.

for further on Spock, take a look at "https://spockframework.org/"

### Framework Organization
```
├── README.md
├── src
│   ├── test              
│       ├── resources              
|           └── example.config.properties            - contains Base url of api's 
├── src
│   ├── test              
│       ├── groovy              
│           ├── getRequests     
│               ├── getUtil.groovy                   - creating get requests here   
│               ├── getRequestSpec.groovy            - verifying the response in it
├── src
│   ├── test              
│       ├── groovy              
│           ├── util     
│               ├── AbstractApiSpec.groovy           - RESTClient   
│               ├── RequestHelper.groovy             - setting base url
```
