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

package dev.orion.game.controller;

import dev.orion.game.data.*;
import javax.inject.Inject;

public class BaseController {
    @Inject
    protected QuestionDAO questionDAO;

    @Inject
    protected FeedbackDAO feedbackDAO;

    @Inject
    protected AnswerDAO answerDAO;

    @Inject
    protected GameDAO gameDAO;

    @Inject
    protected CardDAO cardDAO;

    @Inject
    protected RankingDAO rankingDAO;

    @Inject
    protected UserDAO userDAO;

    @Inject
    protected TeamDAO teamDAO;
}
