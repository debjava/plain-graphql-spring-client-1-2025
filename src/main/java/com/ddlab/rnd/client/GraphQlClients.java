package com.ddlab.rnd.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.ddlab.rnd.entity.Country;

import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;

@Component
public class GraphQlClients {
	
	@Autowired
    private GraphQLWebClient graphQLWebClient;
	private String firstUrl = "https://countries.trevorblades.com/graphql";
	
	public void callUsingWebClient() {
		String body = """
				{
					"query": "query($code: ID!) { country(code: $code) { code capital } }",
					"variables": {
						"code": "IN"
					}
				}
				""";
		WebClient webClient = WebClient.create(firstUrl);
		String sss = webClient
				.post()
				.uri(firstUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(body)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		System.out.println(sss);
	}
	
	public void callUsingHttpGraphQlClient() {
		
//		
//		
//		client.get().attribute("code", "IN").exchange().block().
//		
////		String responseBody = client.get().retrieve().toEntity(String.class)
////		    .block().getBody();
		
		
		
		HttpGraphQlClient httpGraphQlClient = HttpGraphQlClient
				.builder()
				.url(firstUrl)
				.build();
		
		String queryStr1 = """
				query($code: ID!) {
					country(code: $code) {
						code
						capital
					}
				}

				""";
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("code", "IN");
		
		Country country = httpGraphQlClient.document(queryStr1).variables(variables).retrieve("country").toEntity(Country.class).block();
		System.out.println("Country: "+country);
		
	}

	public void callUsingGraphQLWebClient() {
		String queryStr1 = """
				query($code: ID!) {
					country(code: $code) {
						code
						capital
					}
				}

				""";
		Map<String, Object> variables = new HashMap<>();
		variables.put("code", "IN");
		GraphQLRequest request = GraphQLRequest.builder().query(queryStr1).variables(variables).build();

		//print requestBody
		System.out.println(request.toString());

		//passing requestBody to server
		GraphQLResponse response = graphQLWebClient.post(request).block();
		System.out.println("Raw Response: "+response.getRawResponse());

	}
}
