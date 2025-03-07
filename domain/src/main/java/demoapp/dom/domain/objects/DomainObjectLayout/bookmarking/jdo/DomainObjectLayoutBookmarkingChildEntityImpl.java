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
package demoapp.dom.domain.objects.DomainObjectLayout.bookmarking.jdo;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import jakarta.inject.Named;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import org.springframework.context.annotation.Profile;

import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.Nature;
import org.apache.causeway.applib.annotation.Property;

import demoapp.dom.domain.objects.DomainObjectLayout.bookmarking.DomainObjectLayoutBookmarkingChildEntity;
import lombok.Getter;
import lombok.Setter;

@Profile("demo-jdo")
@PersistenceCapable(
    identityType = IdentityType.DATASTORE,
    schema = "demo",
    table = "DomainObjectLayoutBookmarkingChildEntity"
)
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY, column = "id")
@Named("demo.DomainObjectLayoutBookmarkingChildEntity")
//tag::class[]
// ...
@DomainObject(nature = Nature.ENTITY)
public class DomainObjectLayoutBookmarkingChildEntityImpl extends DomainObjectLayoutBookmarkingChildEntity {
    // ...
//end::class[]

    public DomainObjectLayoutBookmarkingChildEntityImpl(DomainObjectLayoutBookmarkingEntityImpl parent, String value) {
        setParent(parent);
        setName(value);
    }

    @Property
    @Getter @Setter
    private String name;

    @Property
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    private DomainObjectLayoutBookmarkingEntityImpl parent;

//tag::class[]
}
//end::class[]
