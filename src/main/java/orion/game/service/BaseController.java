package orion.game.service;

import javax.inject.Inject;

import orion.game.data.AnswerDAO;
import orion.game.data.CardDAO;
import orion.game.data.FeedbackDAO;
import orion.game.data.GameDAO;
import orion.game.data.QuestionDAO;
import orion.game.data.RankingDAO;
import orion.game.data.TeamDAO;
import orion.game.data.UserDAO;

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
