package domainapp.modules.simple.dom.so;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.Editing;
import org.apache.causeway.applib.annotation.Nature;
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.annotation.PropertyLayout;
import org.apache.causeway.commons.internal.base._Tuples.Tuple2;
import org.apache.causeway.valuetypes.markdown.applib.value.Markdown;

import domainapp.modules.hive.api.GetContentJson;
import domainapp.modules.hive.api.LinkPair;
import domainapp.modules.simple.SimpleModule;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement(name = "referenceDiscussion")
@XmlType(propOrder = { "account", "permLink", "preview" })
@DomainObject(nature = Nature.VIEW_MODEL)
@Named(SimpleModule.NAMESPACE + ".ReferenceDiscussion")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ReferenceDiscussion {
	@Inject
	@XmlTransient
	private HiveService hiveService;

	@Property
	@XmlElement(required = true)
	@Getter
	@Setter
	String account;

	@Property
	@XmlElement(required = true)
	@Getter
	@Setter
	String permLink;

	// TODO: postJson cannot be anything but transient. need to keep account and
	// permlink param fields.
	// but, don't want to have to call hiveService.fetchPost() twice.
	@XmlTransient
	private GetContentJson postJson;

	public ReferenceDiscussion(GetContentJson postJson) {
		this.postJson = postJson;
		this.account = postJson.getResult().getAuthor();
		this.permLink = postJson.getResult().getPermlink();
	}

	public GetContentJson getPostJson() {
		if (postJson == null) {
			postJson = hiveService.fetchPost(getAccount(), getPermLink());
		}
		return postJson;
	}

	@Property
	@XmlTransient
	public String getPostBody() {
		return   getPostJson().getResult().getBody();
	}
	
	@Property(editing = Editing.ENABLED)
	@PropertyLayout
//	@XmlTransient
	@XmlElement
	@Setter
	private Markdown preview = null;
	
	public Markdown getPreview() {
		if(preview == null) {
			preview = Markdown.valueOf(getPostBody());
		}
		return preview;
	}

	@Property
	@XmlTransient
	public List<String> getKeywords() {
		String endTag = "**Search Links:**";
		//TODO:  have keywords be a member that can be added to.  Populate that when null.
		// stop looking when you get to the Search Links
		String postBody = getPostBody();
		String keywordsText = postBody.substring(0,postBody.indexOf(endTag));
		
		// Define a regex pattern to match hashtags
		String hashtagPattern = "#\\w+";
		Pattern pattern = Pattern.compile(hashtagPattern);
		Matcher matcher = pattern.matcher(keywordsText);

		// Use a set to avoid duplicate hashtags
		Set<String> hashtags = new LinkedHashSet<>();

		// Find all hashtags in the post body
		while (matcher.find()) {
			hashtags.add(matcher.group());
		}

		// Return the hashtags as a list
		return new ArrayList<>(hashtags);
	}

	@Property
	@XmlTransient
	public List<Tuple2<String, String>> getComments() {
		// call get_comments api?
		return null;
	}

	@Property
	@XmlTransient
	public Markdown getNotesFormatted() {
		return Markdown.valueOf(getNotes());
	}
	
	
	@Property
	@XmlTransient
	public String getNotes() {
		return extractCommentary(getPostBody());
	}
	
    public static String extractCommentary(String postBody) {
        String startMarker = "**Commentary on this Post:**";

        int startIndex = postBody.indexOf(startMarker);
        if (startIndex == -1) {
            return "Commentary section not found.";
        }
        
        // Adjust startIndex to the end of the start marker
        startIndex += startMarker.length();
        
        // Will read until the end.
        return postBody.substring(startIndex).trim();
    }
	

	@Property
	@XmlTransient
	public List<LinkPair> getLinks() {
		// Regex to match the original and archived links in the post body
		String linkRegex = "\\|\\s*\\[([^\\]]+)\\]\\(([^\\)]+)\\)\\s*\\|\\s*\\[([^\\]]+)\\]\\(([^\\)]+)\\)";
		Pattern pattern = Pattern.compile(linkRegex);
		Matcher matcher = pattern.matcher(getPostBody());

		List<LinkPair> links = new ArrayList<LinkPair>();

		// Extract links and pair them
		while (matcher.find()) {
			String originalLink = matcher.group(2); // Extract the original link
			String archivedLink = matcher.group(4); // Extract the archived link
			links.add(new LinkPair(originalLink, archivedLink));
		}

		return links;
	}
}
