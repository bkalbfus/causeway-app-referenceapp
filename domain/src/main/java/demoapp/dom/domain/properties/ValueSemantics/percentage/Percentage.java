package demoapp.dom.domain.properties.ValueSemantics.percentage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.causeway.applib.annotation.ValueSemantics;

//tag::class[]
@ValueSemantics(provider = "percentage")            // <.>
@Inherited
@Target({
        ElementType.METHOD, ElementType.FIELD,
        ElementType.PARAMETER,
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Percentage {}
//end::class[]
