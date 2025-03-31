package domainapp.modules.simple.dom.so;

import java.util.Arrays;
import java.util.stream.Stream;

import org.apache.causeway.applib.graph.tree.TreeAdapter;

import domainapp.modules.hive.api.GetContentJson;
import domainapp.modules.hive.api.HivePostJson;
import jakarta.inject.Inject;

public class HivePostTreeAdapter implements TreeAdapter<IHivePost> {

	@Inject
	HiveService hiveService;
	
	
    @Override
    public int childCountOf(IHivePost value) {
        return (int) streamComments(value).count();
    }

    @Override
    public Stream<IHivePost> childrenOf(IHivePost value) {
        return streamComments(value)
        		.map((c)->new ReferenceDiscussion(value, c));
    }

    private Stream<HivePostJson> streamComments(IHivePost hivePostNode){
       HivePostJson[] comments = null;
       if(hivePostNode.isReply()) {
		   comments = hiveService.fetchComments(hivePostNode.getReplyAccount(),hivePostNode.getReplyPermLink());
       } else {
		   comments = hiveService.fetchComments(hivePostNode.getAccount(),hivePostNode.getPermLink());
       }
       
       return Arrays.stream(comments);
    }
}