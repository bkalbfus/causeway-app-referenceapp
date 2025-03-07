package demoapp.dom.progmodel.customvaluetypes.compositevalues;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.MemberSupport;

import lombok.RequiredArgsConstructor;

//tag::default-mixin[]
@Action        // <.>
@ActionLayout  // <.>
@RequiredArgsConstructor
public class ComplexNumber_default {

    private final ComplexNumber mixee;

    @MemberSupport public ComplexNumber act(
            final double re,
            final double im
    ) {
        return ComplexNumber.of(re, im);
    }
    @MemberSupport public double defaultRe() {
        return mixee.getRe();
    }
    @MemberSupport public double defaultIm() {
        return mixee.getIm();
    }
}
//end::default-mixin[]
