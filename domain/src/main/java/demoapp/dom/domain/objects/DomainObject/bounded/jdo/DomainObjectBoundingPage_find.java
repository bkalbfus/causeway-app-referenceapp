package demoapp.dom.domain.objects.DomainObject.bounded.jdo;

import org.springframework.context.annotation.Profile;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.MemberSupport;
import org.apache.causeway.applib.annotation.SemanticsOf;

import demoapp.dom.domain.objects.DomainObject.bounded.DomainObjectBoundingPage;
import lombok.RequiredArgsConstructor;

//tag::class[]
@Profile("demo-jdo")
@Action(semantics = SemanticsOf.SAFE)
@RequiredArgsConstructor
public class DomainObjectBoundingPage_find {

    @SuppressWarnings("unused")
    private final DomainObjectBoundingPage page;

    @MemberSupport
    public DomainObjectBoundingEntityImpl act(final DomainObjectBoundingEntityImpl domainObjectBounding) {  // <.>
        return domainObjectBounding;
    }

}
//end::class[]
