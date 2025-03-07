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
package demoapp.dom.types.jodatime.jodadatetime.jpa;

import jakarta.inject.Named;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Profile;

import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.Editing;
import org.apache.causeway.applib.annotation.Optionality;
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.annotation.PropertyLayout;
import org.apache.causeway.applib.annotation.Title;
import org.apache.causeway.persistence.jpa.applib.integration.CausewayEntityListener;

import demoapp.dom.types.jodatime.jodadatetime.persistence.JodaDateTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Profile("demo-jpa")
//tag::class[]
@Entity
@Table(
      schema = "demo",
      name = "JodaDateTimeJpa"
)
@EntityListeners(CausewayEntityListener.class)
@Named("demo.JodaDateTimeEntity")
@DomainObject
@NoArgsConstructor                                                             // <.>
public class JodaDateTimeJpa
        extends JodaDateTimeEntity {

//end::class[]
    public JodaDateTimeJpa(final DateTime initialValue) {
        this.readOnlyProperty = initialValue;
        this.readWriteProperty = initialValue;
    }

//tag::class[]
    @Id
    @GeneratedValue
    private Long id;

    @Title(prepend = "DateTime JPA entity: ")
    @PropertyLayout(fieldSetId = "read-only-properties", sequence = "1")
    @Column(nullable = false)                                                   // <.>
    @Getter @Setter
    private DateTime readOnlyProperty;

    @Property(editing = Editing.ENABLED)                                        // <.>
    @PropertyLayout(fieldSetId = "editable-properties", sequence = "1")
    @Column(nullable = false)
    @Getter @Setter
    private DateTime readWriteProperty;

    @Property(optionality = Optionality.OPTIONAL)                               // <.>
    @PropertyLayout(fieldSetId = "optional-properties", sequence = "1")
    @Column(nullable = true)                                                    // <.>
    @Getter @Setter
    private DateTime readOnlyOptionalProperty;

    @Property(editing = Editing.ENABLED, optionality = Optionality.OPTIONAL)
    @PropertyLayout(fieldSetId = "optional-properties", sequence = "2")
    @Column(nullable = true)
    @Getter @Setter
    private DateTime readWriteOptionalProperty;

}
//end::class[]
