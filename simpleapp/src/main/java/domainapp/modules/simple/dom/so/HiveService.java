package domainapp.modules.simple.dom.so;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.DomainService;
import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.annotation.SemanticsOf;
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


	@Action(semantics = SemanticsOf.SAFE_AND_REQUEST_CACHEABLE)
	public HivePostJson fetchPost(String account, String permlink) {
        String payload = """
                {
                    "jsonrpc": "2.0",
                    "method": "condenser_api.get_content",
                    "params": ["%s", "%s"],
                    "id": 1
                }
                """.formatted((String)account, (String)permlink);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        GetContentJson result;
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			ObjectMapper objectMapper = new ObjectMapper();
			result =  objectMapper.readValue(response.body(), GetContentJson.class);
		} catch (Exception e) {
			e.printStackTrace();
			messageService.warnUser("Cannot format response from Hive API.  Error: " + e.getLocalizedMessage());
			result = null;
		}
        return result.getResult();
    }

	@Action(semantics = SemanticsOf.SAFE_AND_REQUEST_CACHEABLE)
	public HivePostJson[] fetchComments(String account, String permlink) {
        String payload = """
                {
                    "jsonrpc": "2.0",
                    "method": "condenser_api.get_content_replies",
                    "params": ["%s", "%s"],
                    "id": 1
                }
                """.formatted((String)account, (String)permlink);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
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
