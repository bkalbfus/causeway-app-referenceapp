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
package demoapp.dom.progmodel.actions.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.MemberSupport;
import org.apache.causeway.applib.annotation.Optionality;
import org.apache.causeway.applib.annotation.Parameter;
import org.apache.causeway.applib.annotation.SemanticsOf;

import demoapp.dom.progmodel.actions.TvCharacter;
import demoapp.dom.progmodel.actions.TvShow;
import lombok.RequiredArgsConstructor;

//tag::class[]
@Action(semantics = SemanticsOf.SAFE)
@RequiredArgsConstructor
public class ActionValidatePage_selectTvCharactersByShowAndSexUsingName {

    private final ActionValidatePage page;

    @MemberSupport public ActionValidatePage act(
        @Parameter(optionality = Optionality.MANDATORY)
        final TvShow tvShow,
        @Parameter(optionality = Optionality.OPTIONAL)
        final TvCharacter.Sex sex,
        @Parameter(optionality = Optionality.OPTIONAL)
        final List<TvCharacter> tvCharacters                        // <.>
    ) {
        // ...
//end::class[]
        page.getSelectedTvCharacters().clear();
        page.getSelectedTvCharacters().addAll(tvCharacters);
        return page;
//tag::class[]
    }

    // ...
//end::class[]
    @MemberSupport public TvShow defaultTvShow() {                  // <1>
        return page.getPreselectTvShow2();
    }
    @MemberSupport public TvCharacter.Sex defaultSex() {            // <2>
        return page.getPreselectCharacterSex2();
    }
    @MemberSupport public List<TvCharacter> choicesTvCharacters() {
        return new ArrayList<>(page.getTvCharacters());
    }
//tag::class[]
    @MemberSupport public String validateTvCharacters(              // <1>
        final TvShow tvShowSelected,                                // <.>
        final TvCharacter.Sex sexSelected,                          // <2>
        final List<TvCharacter> tvCharacters                        // <.>
    ) {
        // ...
//end::class[]
        if (tvCharacters.isEmpty()) {
            return "Must specify at least one TV character";
        }
        List<TvCharacter> collect = choicesTvCharacters()
                .stream()
                .filter(tvCharacter -> tvShowSelected == null || tvShowSelected == tvCharacter.getTvShow())
                .filter(tvCharacter -> sexSelected == null || sexSelected == tvCharacter.getSex())
                .collect(Collectors.toList());
        return collect.containsAll(tvCharacters)
                ? null
                : "Not all of the characters were in that TV show/of the specified sex";
//tag::class[]
    }
}
//end::class[]
