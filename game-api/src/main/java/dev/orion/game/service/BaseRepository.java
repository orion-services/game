/**
 * Copyright 2021 Game Service @ https://github.com/orion-services/game
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.orion.game.service;

import dev.orion.game.data.*;
import javax.inject.Inject;

public class BaseRepository {
    @Inject
    protected QuestionRepository questionRepository;

    @Inject
    protected FeedbackRepository feedbackRepository;

    @Inject
    protected AnswerRepository answerRepository;

    @Inject
    protected GameRepository gameRepository;

    @Inject
    protected CardRepository cardRepository;

    @Inject
    protected RankingRepository rankingRepository;

    @Inject
    protected UserRepository userRepository;

    @Inject
    protected TeamRepository teamRepository;
}
