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
package demoapp.dom.progmodel.actions.choices;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

import org.apache.causeway.applib.annotation.Collection;
import org.apache.causeway.applib.annotation.CollectionLayout;
import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.DomainObjectLayout;
import org.apache.causeway.applib.annotation.Editing;
import org.apache.causeway.applib.annotation.LabelPosition;
import org.apache.causeway.applib.annotation.Nature;
import org.apache.causeway.applib.annotation.ObjectSupport;
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.annotation.PropertyLayout;
import org.apache.causeway.valuetypes.asciidoc.applib.value.AsciiDoc;

import demoapp.dom._infra.asciidocdesc.HasAsciiDocDescription;
import demoapp.dom._infra.resources.AsciiDocReaderService;
import demoapp.dom.progmodel.actions.TvCharacter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Named("demo.ActionChoices")
@DomainObject(
        nature=Nature.VIEW_MODEL,
        editing=Editing.ENABLED
)
@DomainObjectLayout(cssClassFa="fa-list-ul")
@XmlRootElement(name = "root")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ActionChoicesPage implements HasAsciiDocDescription {

    @ObjectSupport public String title() {
        return "Action Choices";
    }

    @Property
    @PropertyLayout(labelPosition=LabelPosition.NONE)
    public AsciiDoc getSingleChoiceDescription() {
        return asciiDocReaderService.readFor(this, "singleChoiceDescription");
    }
    @Property
    @PropertyLayout(labelPosition=LabelPosition.NONE)
    public AsciiDoc getMultipleChoicesDescription() {
        return asciiDocReaderService.readFor(this, "multipleChoicesDescription");
    }
    @Property
    @PropertyLayout(labelPosition=LabelPosition.NONE)
    public AsciiDoc getDependentChoiceDescription() {
        return asciiDocReaderService.readFor(this, "dependentChoiceDescription");
    }
    @Property
    @PropertyLayout(labelPosition=LabelPosition.NONE)
    public AsciiDoc getParameterMatchingDescription() {
        return asciiDocReaderService.readFor(this, "parameterMatchingDescription");
    }

    @Collection
    @CollectionLayout
    @Getter
    private final Set<TvCharacter> tvCharacters = new LinkedHashSet<>();

    @Collection
    @CollectionLayout
    @Getter
    private final Set<TvCharacter> selectedTvCharacters = new LinkedHashSet<>();

    @Inject @XmlTransient AsciiDocReaderService asciiDocReaderService;

}
