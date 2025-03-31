package domainapp.modules.simple.dom.so;

import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.DomainObjectLayout;
import org.apache.causeway.applib.annotation.Editing;
import org.apache.causeway.applib.annotation.Nature;
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.annotation.PropertyLayout;
import org.apache.causeway.applib.annotation.Title;
import org.apache.causeway.applib.services.wrapper.WrapperFactory;
import org.apache.causeway.valuetypes.markdown.applib.value.Markdown;

import domainapp.modules.hive.api.HivePostJson;
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

@XmlRootElement(name = "hivePost")
@XmlType(propOrder = { "account", "permLink", "preview" })
//@XmlSeeAlso(ReferenceDiscussion.class)
@DomainObject(nature = Nature.VIEW_MODEL)
@Named(SimpleModule.NAMESPACE + ".HivePost")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class HivePost implements IHivePost {
	@Inject
	@XmlTransient
	private HiveService hiveService;

	@Inject
	@XmlTransient
	private WrapperFactory wrapper;
	
	@Property
	@XmlElement(required = true)
	@Getter
	@Setter
	@Title
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
	private HivePostJson postJson;

	public HivePost(HivePostJson postJson) {
		this.postJson = postJson;
		this.account = postJson.getAuthor();
		this.permLink = postJson.getPermlink();
	}

	@Override
	public HivePostJson getPostJson() {
		if (postJson == null) {
			postJson = wrapper.wrap(hiveService).fetchPost(getAccount(), getPermLink());
		}
		return postJson;
	}

	@Override
	@Property
	@XmlTransient
	
	public String getPostBody() {
		return getPostJson().getBody();
	}
	
	@Property(editing = Editing.ENABLED)
	@PropertyLayout
//	@XmlTransient
	@XmlElement
	@Setter
	private Markdown preview = null;
	
	@Override
	public Markdown getPreview() {
		if(preview == null) {
			preview = Markdown.valueOf(getPostBody());
		}
		return preview;
	}

	@Override
	public boolean isReply() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getReplyAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReplyPermLink() {
		// TODO Auto-generated method stub
		return null;
	}
}
