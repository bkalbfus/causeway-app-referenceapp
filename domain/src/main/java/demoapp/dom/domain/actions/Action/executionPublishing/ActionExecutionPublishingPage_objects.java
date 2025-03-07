package demoapp.dom.domain.actions.Action.executionPublishing;

import java.util.List;

import jakarta.inject.Inject;

import org.apache.causeway.applib.annotation.Collection;
import org.apache.causeway.applib.annotation.MemberSupport;

import demoapp.dom._infra.values.ValueHolderRepository;
import lombok.RequiredArgsConstructor;

//tag::class[]
@Collection()
@RequiredArgsConstructor
public class ActionExecutionPublishingPage_objects {

    @SuppressWarnings("unused")
    private final ActionExecutionPublishingPage page;

    @MemberSupport public List<? extends ActionExecutionPublishingEntity> coll() {
        return objectRepository.all();
    }

    @Inject ValueHolderRepository<String, ? extends ActionExecutionPublishingEntity> objectRepository;
}
//end::class[]
