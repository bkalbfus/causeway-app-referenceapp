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
package demoapp.dom.domain.actions.ActionLayout.promptStyle;

import jakarta.inject.Named;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.DomainObjectLayout;
import org.apache.causeway.applib.annotation.Nature;
import org.apache.causeway.applib.annotation.ObjectSupport;
import org.apache.causeway.applib.annotation.Optionality;
import org.apache.causeway.applib.annotation.Property;

import demoapp.dom._infra.asciidocdesc.HasAsciiDocDescription;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//tag::class[]
@Named("demo.ActionLayoutPromptStylePage")
@DomainObject(nature=Nature.VIEW_MODEL)
@DomainObjectLayout(cssClassFa="fa-question-circle")
@XmlRootElement(name = "root")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ActionLayoutPromptStylePage implements HasAsciiDocDescription {

    @ObjectSupport public String title() {
        return "@ActionLayout#promptStyle";
    }

    @Property(optionality = Optionality.OPTIONAL)
    @XmlElement(required = false)
    @Getter @Setter
    private String name;

    @Property(optionality = Optionality.OPTIONAL)
    @XmlElement(required = false)
    @Getter @Setter
    private String notes;

}
//end::class[]
