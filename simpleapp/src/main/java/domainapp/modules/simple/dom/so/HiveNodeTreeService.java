package domainapp.modules.simple.dom.so;

import org.apache.causeway.applib.graph.tree.TreeNode;
import org.apache.causeway.applib.graph.tree.TreePath;
import org.apache.causeway.applib.services.factory.FactoryService;
import org.springframework.stereotype.Service;

import domainapp.modules.simple.SimpleModule;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Provider;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Named(SimpleModule.NAMESPACE + ".HiveNodeTreeService")

@Service
@RequiredArgsConstructor(onConstructor_ = { @Inject })
public class HiveNodeTreeService {

    final Provider<HttpSession> httpSessionProvider;
    final FactoryService factoryService;

    public TreeNode<IHivePost> sessionTree(ReferenceDiscussion rd) {
        val session = httpSessionProvider.get();
        val cacheKey = TreeNode.class.getName() + "|" + System.identityHashCode(rd);
        TreeNode<IHivePost> tree = (TreeNode<IHivePost>) session.getAttribute(cacheKey);
        if(tree == null) {
            tree = newTree(rd, factoryService);
            session.setAttribute(cacheKey, tree);
        }
        return tree;

    }
private static TreeNode<IHivePost> newTree(ReferenceDiscussion rd, FactoryService factoryService) {
    TreeNode<IHivePost> tree;
//    val rootFile = FileSystems.getDefault().getRootDirectories().iterator().next().toFile();
    IHivePost rootNode = rd;
    tree = TreeNode.root(rootNode, HivePostTreeAdapter.class, factoryService);
    tree.expand(TreePath.of(0)); // expand the root node
    return tree;
}
	

}
