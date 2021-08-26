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
