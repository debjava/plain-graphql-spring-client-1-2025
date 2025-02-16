package com.ddlab.rnd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;

@Component
public class AutoRun {
	
	@Autowired
    private GraphQLWebClient graphQLWebClient;
	
	public void usingRestTemplatecall1() {
		String url = "https://countries.trevorblades.com/graphql";
		
		String queryStr2 = """
        		query($eq11: String!) {
        				countries(filter: { currency: { eq: $eq11 } }) {
        					currencies
        					name
        			}
        		}

        		""";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		
      //create requestBody with query
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("query", queryStr2);
        
      //create variables
        Map<String, Object> variables = new HashMap<>();
        variables.put("eq11", "USD");
       
      //add variables in the requestBody
        requestBody.put("variables",variables);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        System.out.println(response.getBody());
	}
	
	public void mutationCall1() {
		String queryStr2 = """
        		mutation($fName: String!, $lName: String!, $sta1: MaritalStatus, $sta2: Boolean) {
        				createAppUser(
				    		inUser: { firstName: $fName, lastName: $lName, mStatus: $sta1, status: $sta2 }
						    ) {
						        id
						        firstName
						        lastName
						        mStatus
						        status
						        salary
						        phoneNos
						    }
        		}

        		""";
		
		 Map<String, Object> variables = new HashMap<>();
//       variables.put("code", "IN");
       variables.put("fName", "Hati");
       variables.put("lName", "Ghoda");
       variables.put("sta1", "MARRIED");
       variables.put("sta2", Boolean.TRUE);
       
     //setting up requestBody with query and variables
       GraphQLRequest request = GraphQLRequest.builder()
                       .query(queryStr2).variables(variables).build();

       //print requestBody
       System.out.println(request.toString());

       //passing requestBody to server
       GraphQLResponse response = graphQLWebClient.post(request).block();
       System.out.println(response.getRawResponse());
	}
	
	public void call2() {
		String queryStr2 = """
        		query($eq1: String!, $eq2: String!) {
        				countries(filter: { code: { eq: $eq1 }, continent: { eq: $eq2 } }) {
					        capital
					        code
					        awsRegion
					        languages {
					            name
					            native
					        }
						}
        		}

        		""";
		
		 Map<String, Object> variables = new HashMap<>();
//       variables.put("code", "IN");
       variables.put("eq1", "VA");
       variables.put("eq2", "EU");
       
     //setting up requestBody with query and variables
       GraphQLRequest request = GraphQLRequest.builder()
                       .query(queryStr2).variables(variables).build();

       //print requestBody
       System.out.println(request.toString());

       //passing requestBody to server
       GraphQLResponse response = graphQLWebClient.post(request).block();
       System.out.println(response.getRawResponse());
	}
	
	public void call1() {
		String queryStr2 = """
        		query($eq11: String!) {
        				countries(filter: { currency: { eq: $eq11 } }) {
        					currencies
        					name
        			}
        		}

        		""";
		
		 Map<String, Object> variables = new HashMap<>();
//       variables.put("code", "IN");
       variables.put("eq11", "USD");
       
     //setting up requestBody with query and variables
       GraphQLRequest request = GraphQLRequest.builder()
                       .query(queryStr2).variables(variables).build();

       //print requestBody
       System.out.println(request.toString());

       //passing requestBody to server
       GraphQLResponse response = graphQLWebClient.post(request).block();
       System.out.println(response.getRawResponse());
	}
	
	public void getGraphQLRespPayLoad(){
        String queryStr = "query($code: ID!) {\n" +
                "  country(code: $code) {\n" +
                "    name\n" +
                "    native\n" +
                "    capital\n" +
                "    emoji\n" +
                "    currency\n" +
                "    languages {\n" +
                "      code\n" +
                "      name\n" +
                "    }\n" +
                "  }\n" +
                "}";
        
        String queryStr1 = """
        		query($code: ID!) {
        			country(code: $code) {
        				code
        				capital
        			}
        		}

        		""";
        
        
        String queryStr2 = """
        		query($eq: String!) {
        				countries(filter: { currency: { eq: $eq } }) {
        					currencies
        					name
        			}
        		}

        		""";
        
        
        //setting up variable to pass in the query
        Map<String, Object> variables = new HashMap<>();
        variables.put("code", "IN");
//        variables.put("eq", "USD");

        //setting up requestBody with query and variables
        GraphQLRequest request = GraphQLRequest.builder()
                        .query(queryStr).variables(variables).build();

        //print requestBody
        System.out.println(request.toString());

        //passing requestBody to server
        GraphQLResponse response = graphQLWebClient.post(request).block();
        System.out.println(response.getRawResponse());

//        System.out.println(response.get("country", String.class));
    }
	
	@EventListener(ApplicationReadyEvent.class)
	public void execute() {
		System.out.println("Application started running ...");
//		getGraphQLRespPayLoad();
		
//		call1();
		
//		call2();
		
//		mutationCall1();
		
		usingRestTemplatecall1();
	}

}
