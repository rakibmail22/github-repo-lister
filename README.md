# github-project-lister
The work in this repository demonstrate configuration of Spring Open Feign client
to ingest data from github repository search api

## API Endpoint Sample Request
`curl -X GET \
'http://localhost:8080/api/v1/repositories?createdFrom=2022-01-01&limit=10&language=go'`

## How To Run
##### Run With gradle (Dev Profile) :: Pre Requisite: jdk 17
1. navigate to repository root
2. Run the following command from terminal `./gradlew bootRun`


##### Run With gradle (Prod Profile) :: Pre Requisite: Docker
1. navigate to repository root
2. Make the following scripts executable
   `chmod +x dockerBuild.sh`, `chmod +x runProd.sh`
3. Build docker image `./dockerBuild.sh`
4. Run docker image `./runProd.sh`
5. Now we can run the above mentioned curl request

##### Future Improvement for Better Scaling
1. As github has ip based api throttling (maximum 10 request per minute for unauthenticated client and 30 for authenticated)
we can cache the response from `ListGithubRepoServiceImpl#getRepositories` against the query params for 2 minutes
that will prevent our service to create request to github for same request params within 2 minutes
This can be implemented with Redis (Or any other distributed cache like Hazelcast) configured with `@Cahceable` Spring
or using any of the cache specific clients.
2. Add more test coverage especially for the api endpoint and validator class
3. Add Api documentation tool like Swagger with Open Api

#### Alternative Clients
1. We can use `RestTEmplate` directly to call. Feign client is just a wrapper above springs Rest Template
2. We can also use `WebClient` which is now a days promoted by Spring, it's basically introduced for the reactive webflux api but works with servlet based implementations too



