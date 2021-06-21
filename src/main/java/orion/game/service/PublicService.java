/**
 * @License
 * Copyright 2020 Orion Services
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
package orion.game.service;


import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;


import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import orion.game.data.AnswerDAO;
import orion.game.data.CardDAO;
import orion.game.data.FeedbackDAO;
import orion.game.data.GameDAO;
import orion.game.data.QuestionDAO;
import orion.game.model.Answer;
import orion.game.model.Card;
import orion.game.model.Feedback;
import orion.game.model.Game;
import orion.game.model.Question;
import orion.game.model.Ranking;
import orion.game.model.Team;
import orion.game.model.User;

@RequestScoped
@Path("/api/v1/")
public class PublicService extends BaseController{

    @POST
    @APIResponse(responseCode = "200", description = "successfully")
    @APIResponse(responseCode = "409", description = "a conflict has occurred")
    @Tag(name="PLAYER")
    @Path("playerteam")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Team createTeam() {

        final Team team = new Team();
        final User user =  new User("user 1");
        team.addUser(user);
            teamDAO.create(team);           
                return team;

        
    }


    @POST
    @APIResponse(responseCode = "200", description = "successfully")
    @APIResponse(responseCode = "409", description = "a conflict has occurred")
    @Tag(name="PLAYER")
    @Path("playergame")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game createGame() {

        final Game game = new Game();

        final Card card = new Card("teste");
        final Question question = new Question("teste");
        final Ranking ranking = new Ranking("teste");
        final User user =  new User("user 1");
        final Team team = new Team("teste");
        team.addUser(user);

        game.addCard(card);
        game.addQuestion(question);
        game.addRanking(ranking);
        game.addTeam(team);

                gameDAO.create(game); 
            

                return game;
        
    }

    @POST
    @APIResponse(responseCode = "200", description = "successfully")
    @APIResponse(responseCode = "409", description = "a conflict has occurred")
    @Tag(name="PLAYER")
    @Path("playerquestion")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Question createQuestion() {

        final Question quest = new Question();

            String textQuestion=questionDAO.randomQuestion();
            quest.setTextQuestion(textQuestion);
            questionDAO.create(quest);           

                return quest;
        
    }

    @POST
    @APIResponse(responseCode = "200", description = "successfully")
    @APIResponse(responseCode = "409", description = "a conflict has occurred")
    @Tag(name="PLAYER")
    @Path("playeranswer")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Answer createAnswer(@FormParam("id") final long id, @FormParam("textAnswer") final String textAnswer) {

        final Answer answ = new Answer();
        final Question quest = new Question();
                quest.setId(id);
                answ.setQuestion(quest);
                answ.setTextAnswer(textAnswer);
                answerDAO.create(answ);           

                return answ;
        
    }


//create the feedback text

    @POST
    @APIResponse(responseCode = "200", description = "successfully")
    @APIResponse(responseCode = "409", description = "a conflict has occurred")
    @Tag(name="PLAYER")
    @Path("playerfeedback1")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Feedback createFeedback1(@FormParam("textFeedback") final String textFeedback) {

        final Feedback feedb = new Feedback();
                feedb.setTextFeedback(textFeedback);
                feedbackDAO.create(feedb);           

                return feedb;
        
    }

// looks for the "id answer" and persists in the bank the "id feedback" to have the relationship

    @POST
    @APIResponse(responseCode = "200", description = "successfully")
    @APIResponse(responseCode = "409", description = "a conflict has occurred")
    @Tag(name="PLAYER")
    @Path("playerfeedback2")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Answer createFeedback2(@FormParam("idAns") final long idAns, @FormParam("idFee") final long idFee) {

        final Answer answ =  answerDAO.find(idAns);
        final Feedback feedb = new Feedback();
                feedb.setId(idFee);
                answ.setFeedback(feedb);
                answerDAO.update(answ);           

                return answ;
        
    }



    

}
