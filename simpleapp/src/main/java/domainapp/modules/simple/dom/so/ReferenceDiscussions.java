package domainapp.modules.simple.dom.so;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.DomainService;
import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.annotation.PromptStyle;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.inject.ServiceInjector;
import org.apache.causeway.applib.services.repository.RepositoryService;

import domainapp.modules.hive.api.GetContentJson;
import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.types.Name;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named(SimpleModule.NAMESPACE + ".ReferenceDiscussions")
@DomainService
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class ReferenceDiscussions {
	@Inject HiveService hiveService;
	@Inject ServiceInjector serviceInjector;
    @Inject RepositoryService repositoryService;


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public TestMarkdown createTestMarkdown(
            @Name final String name) {

        return repositoryService.persist(TestMarkdown.withName(name));
    }


//    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
//    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    /**
     * 
     * @param keywords List<String>
     * @return list of ReferenceDisscussion ordered from 
     * <ol>
     *    <li>best match (all keywords match)</li>
     *    <li>partial match (some keywords match)</li>
     * </ol>
     *     
     */
//    public List<ReferenceDiscussion> findByKeywordsLike(
//            final List<String> keywords) {
//    	return new ArrayList<ReferenceDiscussion>();
//    }


    //TODO:  need helper method for the keywords.  See if there is a Causeway facility to populate a simple list of strings
//    @Action(semantics = SemanticsOf.SAFE)
//    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
//    public List<ReferenceDiscussion> findByKeywords(
//            final List<String> keywords) {
//    	return new ArrayList<ReferenceDiscussion>();
//    }


//    @Action(semantics = SemanticsOf.SAFE_AND_REQUEST_CACHEABLE)
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public ReferenceDiscussion findById(final String account, final String permlink) {
         GetContentJson postJson = hiveService.fetchPost(account, permlink);
         ReferenceDiscussion result = new ReferenceDiscussion(postJson);
         return serviceInjector.injectServicesInto(result);
    }



//    @Action(semantics = SemanticsOf.SAFE)
//    public List<ReferenceDiscussion> listAll() {
//        return simpleObjectRepository.findAll();
//    }
//


    public void ping() {
    	// TODO:  use a status api for Hive Condensor
//        jpaSupportService.getEntityManager(ReferenceDiscussion.class)
//            .mapEmptyToFailure()
//            .mapSuccessAsNullable(entityManager -> {
//                final TypedQuery<ReferenceDiscussion> q = entityManager.createQuery(
//                                "SELECT p FROM ReferenceDiscussion p ORDER BY p.name",
//                                ReferenceDiscussion.class)
//                        .setMaxResults(1);
//                return q.getResultList();
//            })
//        .ifFailureFail();
    }

}
