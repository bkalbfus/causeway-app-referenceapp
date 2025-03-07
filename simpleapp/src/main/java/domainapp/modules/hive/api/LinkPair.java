package domainapp.modules.hive.api;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.Property;

import domainapp.modules.simple.SimpleModule;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "linkPair")
@XmlType(propOrder = { "originalLink", "archivedLink" })
@DomainObject
@Named(SimpleModule.NAMESPACE + ".LinkPair")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class LinkPair {

	private String originalLink;

	private String archivedLink;

	public LinkPair(String originalLink, String archivedLink) {
		this.originalLink = originalLink;
		this.archivedLink = archivedLink;
	}

	@Property
	@XmlTransient
	public URL getOriginalLink() {
		try {
			return URI.create(originalLink).toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Property
	@XmlTransient
	public URL getArchivedLink() {
		try {
			return URI.create(archivedLink).toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
