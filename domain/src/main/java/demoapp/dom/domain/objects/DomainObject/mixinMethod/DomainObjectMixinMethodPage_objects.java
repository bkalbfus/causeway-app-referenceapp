package demoapp.dom.domain.objects.DomainObject.mixinMethod;

import java.util.List;

import jakarta.inject.Inject;

import org.apache.causeway.applib.annotation.Collection;
import org.apache.causeway.applib.annotation.CollectionLayout;
import org.apache.causeway.applib.annotation.DomainObject;

import demoapp.dom._infra.values.ValueHolderRepository;
import lombok.RequiredArgsConstructor;

//tag::class[]
@DomainObject(mixinMethod = "collection")                               // <.>
@Collection()
@CollectionLayout()
@RequiredArgsConstructor
public class DomainObjectMixinMethodPage_objects {
    // ...
//end::class[]
    @SuppressWarnings("unused")
    private final DomainObjectMixinMethodPage page;

//tag::class[]
    public List<? extends DomainObjectMixinMethodEntity> collection() { // <.>
        // ...
//end::class[]
        return objectRepository.all();
//tag::class[]
    }
//end::class[]

    @Inject
    ValueHolderRepository<String, ? extends DomainObjectMixinMethodEntity> objectRepository;

//tag::class[]
}
//end::class[]
