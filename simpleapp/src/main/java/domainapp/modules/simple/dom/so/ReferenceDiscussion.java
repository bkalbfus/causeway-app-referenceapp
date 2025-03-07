package domainapp.modules.simple.dom.so;

import java.util.ArrayList;
import java.util.List;
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
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
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
		// TODO: parse post body for keywords
		return null;
	}

	@Property
	@XmlTransient
	public List<Tuple2<String, String>> getComments() {
		// call get_comments api?
		return null;
	}

	@Property
	@XmlTransient
	public String getNotes() {
		// parse body to get notes
		return null;
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
