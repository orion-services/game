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
    @Path("playeruser")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User createUser(@FormParam("textUser") final String textUser) {

        final User user =  new User();
        user.setTextUser(textUser);
            userDAO.create(user);           
                return user;

        
    }

    @POST
    @APIResponse(responseCode = "200", description = "successfully")
    @APIResponse(responseCode = "409", description = "a conflict has occurred")
    @Tag(name="PLAYER")
    @Path("playerteam")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Team createTeam(@FormParam("textTeam") final String textTeam, @FormParam("idUser") final long idUser) {

        final User user =  userDAO.find(idUser);
        final Team team =  new Team();
        team.setTextTeam(textTeam);
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
    public Game createGame(@FormParam("idTeam") final long idTeam, @FormParam("textGame") final String textGame) {

        final Game game = new Game();
        final Team team =  teamDAO.find(idTeam);
        game.setTextGame(textGame);
        game.addTeam(team);
            gameDAO.create(game);           
                return game;

        
    }

    @POST
    @APIResponse(responseCode = "200", description = "successfully")
    @APIResponse(responseCode = "409", description = "a conflict has occurred")
    @Tag(name="GAME")
    @Path("buycard")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game buyCard(@FormParam("idGame") final long idGame) {

        final Game game = gameDAO.find(idGame);
        final Card card = new Card();
        game.addCard(card);
            gameDAO.update(game);         
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
    public Question createQuestion(@FormParam("idGame") final long idGame) {

        final Game game = gameDAO.find(idGame);
        final Question question =  new Question();
        question.addGame(game);
            questionDAO.create(question);           
                return question;

        
    }

    @POST
    @APIResponse(responseCode = "200", description = "successfully")
    @APIResponse(responseCode = "409", description = "a conflict has occurred")
    @Tag(name="PLAYER")
    @Path("playeranswer")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Answer createAnswer(@FormParam("idQuestion") final long idQuestion, @FormParam("idTeam") final long idTeam, @FormParam("textAnswer") final String textAnswer) {

        final Question question = questionDAO.find(idQuestion);
        final Team team = teamDAO.find(idTeam);
        final Answer answer =  new Answer();

        answer.setTextAnswer(textAnswer);
        answer.addTeam(team);
        answer.setQuestion(question);
            answerDAO.create(answer);           
                return answer;

        
    }

    @POST
    @APIResponse(responseCode = "200", description = "successfully")
    @APIResponse(responseCode = "409", description = "a conflict has occurred")
    @Tag(name="PLAYER")
    @Path("playerfeedback")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Feedback createFeedback(@FormParam("idUser") final long idUser, @FormParam("idAnswer") final long idAnswer, @FormParam("textFeedback") final String textFeedback) {

        final Feedback feedback = new Feedback();
        final User user = userDAO.find(idUser);
        final Answer answer = answerDAO.find(idAnswer);

        feedback.addAnswer(answer);
        feedback.setUser(user);
        feedback.setTextFeedback(textFeedback);
        

            feedbackDAO.create(feedback);           
                return feedback;

        
    }


 



    

}
