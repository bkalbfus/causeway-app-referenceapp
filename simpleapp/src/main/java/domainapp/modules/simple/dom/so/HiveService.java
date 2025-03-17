package domainapp.modules.simple.dom.so;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.causeway.applib.annotation.DomainService;
import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.services.message.MessageService;

import com.fasterxml.jackson.databind.ObjectMapper;

import domainapp.modules.hive.api.GetCommentsJson;
import domainapp.modules.hive.api.GetContentJson;
import domainapp.modules.hive.api.HivePostJson;
import domainapp.modules.simple.SimpleModule;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named(SimpleModule.NAMESPACE + ".HiveService")
@DomainService
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class HiveService {
	@Inject MessageService messageService;
	private String apiUrl = "https://api.hive.blog";

	public HivePostJson fetchPost(String account, String permlink) {
        // Replace these with the account and permlink of the post you want to fetch
//        String account = "example_account"; // Hive account name
//        String permlink = "example-permlink"; // Permlink of the post

        // JSON payload using Text Blocks (heredoc-like syntax)
        String payload = """
                {
                    "jsonrpc": "2.0",
                    "method": "condenser_api.get_content",
                    "params": ["%s", "%s"],
                    "id": 1
                }
                """.formatted(account, permlink);
        
        
        // Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Build the HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        // Send the request and handle the response
        GetContentJson result;
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
        ObjectMapper objectMapper = new ObjectMapper();
        result =  objectMapper.readValue(response.body(), GetContentJson.class);
			
//			result = HivePostResponse.fromJson(response.body());
		} catch (Exception e) {
			e.printStackTrace();
			messageService.warnUser("Cannot format response from Hive API.  Error: " + e.getLocalizedMessage());
			result = null;
		}
        return result.getResult();
    }

	public HivePostJson[] getComments(String account, String permlink) {
        String payload = """
                {
                    "jsonrpc": "2.0",
                    "method": "condenser_api.get_content_replies",
                    "params": ["%s", "%s"],
                    "id": 1
                }
                """.formatted(account, permlink);
        
        
        // Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Build the HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        // Send the request and handle the response
        GetCommentsJson result;
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
        ObjectMapper objectMapper = new ObjectMapper();
        result =  objectMapper.readValue(response.body(), GetCommentsJson.class);
		} catch (Exception e) {
			e.printStackTrace();
			messageService.warnUser("Cannot format response from Hive API.  Error: " + e.getLocalizedMessage());
			result = null;
		}
        return result.getResult();
	}
}
