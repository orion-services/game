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

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.specimpl.ResponseBuilderImpl;

import dev.orion.game.entity.Answer;
import dev.orion.game.entity.Card;
import dev.orion.game.entity.Feedback;
import dev.orion.game.entity.Game;
import dev.orion.game.entity.Question;
import dev.orion.game.entity.Team;
import dev.orion.game.entity.User;

@RequestScoped
@Path("/api/v1/")
public class GameController extends BaseController{

  


    @POST
    @Tag(name="GAME")
    @Path("playerquestion")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Question createQuestion(@FormParam("idGame") final Long idGame) throws WebApplicationException{

        final Game game = gameDAO.findById(idGame);
        final Question question =  new Question();

        if(idGame.equals(null)) {
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.entity("cannot be empty");
            Response response = builder.build();
            throw new WebApplicationException(response);

        }else{

            question.addGame(game); 
            questionDAO.persist(question);              
        }
            return question;
        
    }

    @POST
    @Tag(name="GAME")
    @Path("playeranswer")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Answer createAnswer(@FormParam("idQuestion") final Long idQuestion, @FormParam("idTeam") final Long idTeam, @FormParam("textAnswer") final String textAnswer) throws WebApplicationException{

        final Question question = questionDAO.findById(idQuestion);
        final Team team = teamDAO.findById(idTeam);
        final Answer answer =  new Answer();

        if(idQuestion.equals(null) || idTeam.equals(null) || textAnswer.equals(null)) {
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.entity("cannot be empty");
            Response response = builder.build();
            throw new WebApplicationException(response);
        }else{
            answer.setTextAnswer(textAnswer);
            answer.addTeam(team);
            answer.setQuestion(question);    
            answerDAO.persist(answer);   
        }
  
            return answer;

        
    }

    @POST
    @Tag(name="GAME")
    @Path("playerfeedback")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Feedback createFeedback(@FormParam("idUser") final Long idUser, @FormParam("idAnswer") final Long idAnswer, @FormParam("textFeedback") final String textFeedback) throws WebApplicationException{

        final Feedback feedback = new Feedback();
        final User user = userDAO.findById(idUser);
        final Answer answer = answerDAO.findById(idAnswer);
        
        if(idUser.equals(null) || idAnswer.equals(null) || textFeedback.isEmpty()) {
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.entity("cannot be empty");
        }else{
            feedback.addAnswer(answer);
            feedback.setUser(user);
            feedback.setTextFeedback(textFeedback);
            feedbackDAO.persist(feedback);
        }     
        return feedback;

        
    }


    @PUT
    @Tag(name="GAME")
    @Path("buycard")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game buyCard(@FormParam("idGame") final Long idGame) throws WebApplicationException{
        final Game game = gameDAO.findById(idGame);
        final Card card = new Card();

        if(idGame.equals(null)) {
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.entity("cannot be empty");
            Response response = builder.build();
            throw new WebApplicationException(response);
        }else{
            game.addCard(card);
            gameDAO.isPersistent(game);
        }
        return game;
    }

    @POST
    @Tag(name="GAME")
    @Path("playerteam")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Team teams(ArrayList<String> names) throws WebApplicationException{
        
        final ArrayList<Team> teams = new ArrayList<Team>();
        final Team team = new Team();
       
        if(names.isEmpty()) {
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.entity("cannot be empty");
            Response response = builder.build();
            throw new WebApplicationException(response);
        }else{
            for(String name: names){
                team.addUser(userDAO.find("name", name).firstResult());
                teams.add(team);
            }
            teamDAO.persist(team);
         }
      
            return team;
    }

    @POST
    @Tag(name="GAME")
    @Path("playergame")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game createGame(ArrayList<Long> ids) throws WebApplicationException{
      final ArrayList<Game> games = new ArrayList<Game>();
      final Game game = new Game();
       
      if(ids.isEmpty()){
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.entity("cannot be empty");
            Response response = builder.build();
            throw new WebApplicationException(response);
      }else{
            for(Long id: ids){
                game.addTeam(teamDAO.findById(id));
                games.add(game);
            }
            gameDAO.persist(game);
        }
            return game;
    }

        

   

}
