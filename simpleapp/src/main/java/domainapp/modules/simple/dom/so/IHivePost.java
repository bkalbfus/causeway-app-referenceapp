package domainapp.modules.simple.dom.so;

import org.apache.causeway.valuetypes.markdown.applib.value.Markdown;

import domainapp.modules.hive.api.HivePostJson;

public interface IHivePost {

	String getAccount();

	void setAccount(String account);

	String getPermLink();

	void setPermLink(String permLink);

	HivePostJson getPostJson();

	String getPostBody();

	void setPreview(Markdown preview);

	Markdown getPreview();

	boolean isReply();

	String getReplyAccount();

	String getReplyPermLink();

}