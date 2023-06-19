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
package demoapp.dom.homepage;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.HomePage;
import org.apache.causeway.applib.annotation.Nature;
import org.apache.causeway.applib.annotation.ObjectSupport;
import org.apache.causeway.applib.services.user.UserService;
import org.apache.causeway.valuetypes.asciidoc.applib.value.AsciiDoc;

import demoapp.dom._infra.asciidocdesc.HasAsciiDocDescription;
import demoapp.dom._infra.resources.AsciiDocReaderService;

//tag::class[]
@Named("demo.Homepage")
@DomainObject(nature=Nature.VIEW_MODEL)
@HomePage                                                           // <.>
public class DemoHomePage
        implements HasAsciiDocDescription {                         // <.>

    @ObjectSupport public String title() {                          // <.>
        return "Hello, " + userService.currentUserNameElseNobody();
    }

    public AsciiDoc getWelcome() {                                  // <.>
        return asciiDocReaderService.readFor(this, "welcome");
    }

    @Inject UserService userService;                                // <.>
    @Inject AsciiDocReaderService asciiDocReaderService;
}
//end::class[]
