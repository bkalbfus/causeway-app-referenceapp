/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package demoapp.dom.types.jodatime.jodalocaldate.vm;

import jakarta.inject.Named;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.joda.time.LocalDate;

import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.Editing;
import org.apache.causeway.applib.annotation.Nature;
import org.apache.causeway.applib.annotation.Optionality;
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.annotation.PropertyLayout;
import org.apache.causeway.applib.annotation.Title;

import demoapp.dom._infra.asciidocdesc.HasAsciiDocDescription;
import demoapp.dom.types.jodatime.jodalocaldate.holder.JodaLocalDateHolder2;
import lombok.Getter;
import lombok.Setter;

//tag::class[]
@XmlRootElement(name = "root")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@Named("demo.JodaLocalDateVm")
@DomainObject(
        nature=Nature.VIEW_MODEL)
@lombok.NoArgsConstructor                                                       // <.>
public class JodaLocalDateVm
        implements HasAsciiDocDescription, JodaLocalDateHolder2 {

//end::class[]
    public JodaLocalDateVm(final LocalDate initialValue) {
        this.readOnlyProperty = initialValue;
        this.readWriteProperty = initialValue;
    }

//tag::class[]
    @Title(prepend = "LocalDate view model: ")
    @PropertyLayout(fieldSetId = "read-only-properties", sequence = "1")
    @XmlJavaTypeAdapter(org.apache.causeway.valuetypes.jodatime.applib.jaxb.JodaTimeJaxbAdapters.LocalDateToStringAdapter.class)
    @XmlElement(required = true)                                                // <.>
    @Getter @Setter
    private LocalDate readOnlyProperty;

    @Property(editing = Editing.ENABLED)                                        // <.>
    @PropertyLayout(fieldSetId = "editable-properties", sequence = "1")
    @XmlJavaTypeAdapter(org.apache.causeway.valuetypes.jodatime.applib.jaxb.JodaTimeJaxbAdapters.LocalDateToStringAdapter.class)
    @XmlElement(required = true)
    @Getter @Setter
    private LocalDate readWriteProperty;

    @Property(optionality = Optionality.OPTIONAL)                               // <.>
    @PropertyLayout(fieldSetId = "optional-properties", sequence = "1")
    @XmlJavaTypeAdapter(org.apache.causeway.valuetypes.jodatime.applib.jaxb.JodaTimeJaxbAdapters.LocalDateToStringAdapter.class)
    @Getter @Setter
    private LocalDate readOnlyOptionalProperty;

    @Property(editing = Editing.ENABLED, optionality = Optionality.OPTIONAL)
    @PropertyLayout(fieldSetId = "optional-properties", sequence = "2")
    @XmlJavaTypeAdapter(org.apache.causeway.valuetypes.jodatime.applib.jaxb.JodaTimeJaxbAdapters.LocalDateToStringAdapter.class)
    @Getter @Setter
    private LocalDate readWriteOptionalProperty;

}
//end::class[]
