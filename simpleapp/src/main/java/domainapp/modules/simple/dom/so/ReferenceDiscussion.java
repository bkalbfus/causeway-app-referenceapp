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
import org.apache.causeway.applib.graph.tree.TreeNode;
import org.apache.causeway.applib.services.wrapper.WrapperFactory;
import org.apache.causeway.valuetypes.markdown.applib.value.Markdown;

import domainapp.modules.hive.api.HivePostJson;
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
@XmlType(propOrder = { "account", "permLink", "preview", "replyAccount", "replyPermLink", "replyPreview" })
@DomainObject(nature = Nature.VIEW_MODEL)
@Named(SimpleModule.NAMESPACE + ".ReferenceDiscussion")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
//public class ReferenceDiscussion implements IHivePost {
public class ReferenceDiscussion implements IHivePost {
	@Inject
	@XmlTransient
	private HiveService hiveService;

	@Inject
	@XmlTransient
	private WrapperFactory wrapper;
	
	@Property
	@PropertyLayout(fieldSetId = "identity")
	@XmlElement(required = true)
	@Getter
	@Setter
	String account;

	@Property
	@PropertyLayout(fieldSetId = "identity")
	@XmlElement(required = true)
	@Getter
	@Setter
	String permLink;

	@Property
	@PropertyLayout(fieldSetId = "navigation")
	@XmlElement(required = true)
	@Getter
	@Setter
	String replyAccount;

	@Property
	@PropertyLayout(fieldSetId = "navigation")
	@XmlElement(required = true)
	@Getter
	@Setter
	String replyPermLink;

	public String title() {
		if(!isReply()) {
			return getPostJson().getTitle();
		} else {
//			return getReplyAccount() + " - " + getReplyJson().getCreated();
			return getReplyAccount();
		}
	}
	
	// TODO: postJson cannot be anything but transient. need to keep account and
	// permlink param fields.
	// but, don't want to have to call hiveService.fetchPost() twice.
	@XmlTransient
	private HivePostJson postJson;
	
	@XmlTransient
	private HivePostJson replyJson;
	
//	private IHivePost fileNodeVm;

	@Property
	@PropertyLayout(fieldSetId = "navigation")
	@XmlTransient
    public TreeNode<IHivePost> getComments(){
        return hiveNodeTreeService.sessionTree(this);
    }

    @Inject
    @XmlTransient
    HiveNodeTreeService hiveNodeTreeService;
    

	public ReferenceDiscussion(HivePostJson postJson) {
		this.postJson = postJson;
		this.account = postJson.getAuthor();
		this.permLink = postJson.getPermlink();
	}

	public ReferenceDiscussion(IHivePost rootPost, HivePostJson postJson) {
		this.postJson = rootPost.getPostJson();
		this.account = rootPost.getAccount();
		this.permLink = rootPost.getPermLink();

		this.replyJson = postJson;
		this.replyAccount = postJson.getAuthor();
		this.replyPermLink = postJson.getPermlink();
	}

	@XmlTransient
	@Property(editing = Editing.DISABLED)
	@PropertyLayout(fieldSetId = "metadata")
	public boolean isReply() {
		if(replyAccount == null && replyPermLink == null) {
			return false;
		} else {
			return true;
		}
	}

	public HivePostJson getPostJson() {
		if (postJson == null) {
			postJson = getHiveService().fetchPost(getAccount(), getPermLink());
		}
		return postJson;
	}

	private HiveService getHiveService() {
		return wrapper.wrap(hiveService);
	}

	public HivePostJson getReplyJson() {
		if (!isReply()) {
			replyJson = null;
		} else if (replyJson == null) {
			replyJson = getHiveService().fetchPost(getReplyAccount(), getReplyPermLink());
		}
		return replyJson;
	}

	@Property
	@PropertyLayout(fieldSetId = "other")
	@XmlTransient
	public String getPostBody() {
		return   getPostJson().getBody();
	}
	
//	public boolean hidePostBody() {
//		return isReply();
//	}

	
	@Property(editing = Editing.ENABLED)
	@PropertyLayout(fieldSetId = "content")
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
	
	public boolean hidePreview() {
		return isReply();
	}

	@Property(editing = Editing.ENABLED)
	@PropertyLayout(fieldSetId = "content")
//	@XmlTransient
	@XmlElement
	@Setter
	private Markdown replyPreview = null;
	
	
	public Markdown getReplyPreview() {
		if(replyAccount == null || replyPermLink == null) {
			replyPreview = null;
		} else if(replyPreview == null) {
			replyPreview = Markdown.valueOf(getReplyJson().getBody());
		}
		return replyPreview;
	}
	
	public boolean hideReplyPreview() {
		return !isReply();
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
	public Markdown getNotesFormatted() {
		return Markdown.valueOf(getNotes());
	}
	
	public boolean hideNotesFormatted() {
		return isReply();
	}
	
	
	@Property
	@XmlTransient
	public String getNotes() {
		return extractNotes(getPostBody());
	}
	
	public boolean hideNotes() {
		return isReply();
	}
	
    public static String extractNotes(String postBody) {
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
