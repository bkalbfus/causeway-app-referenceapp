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
package demoapp.dom.domain.actions.Action.executionPublishing;

import jakarta.inject.Inject;

import org.springframework.stereotype.Service;

import demoapp.dom._infra.seed.SeedServiceAbstract;
import demoapp.dom._infra.values.ValueHolderRepository;

@Service
public class ActionExecutionPublishingEntitySeeding
extends SeedServiceAbstract {

    @Inject
    public ActionExecutionPublishingEntitySeeding(
            ValueHolderRepository<String, ? extends ActionExecutionPublishingEntity> entities) {
        super(entities);
    }

}
