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

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.specimpl.ResponseBuilderImpl;

import dev.orion.game.model.Answer;
import dev.orion.game.model.Card;
import dev.orion.game.model.Feedback;
import dev.orion.game.model.Game;
import dev.orion.game.model.Question;
import dev.orion.game.model.Team;
import dev.orion.game.model.User;

@RequestScoped
@Path("/api/v1/")
public class GameService extends BaseRepository{

  


    @POST
    @Tag(name="GAME")
    @Path("playerquestion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Question createQuestion(@RequestBody Game game) throws WebApplicationException{

        game = gameRepository.findById(game.getId());
        final Question question =  new Question();

        if(game.getId().equals(null)) {
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.entity("cannot be empty");
            Response response = builder.build();
            throw new WebApplicationException(response);

        }else{

            question.addGame(game); 
            questionRepository.persist(question);              
        }
            return question;
        
    }

    @POST
    @Tag(name="GAME")
    @Path("playeranswer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Answer createAnswer(
        @RequestBody Question question, @RequestBody Team team, @RequestBody Answer answer) throws WebApplicationException{

        question = questionRepository.findById(question.getId());
        team = teamRepository.findById(team.getId());

        if(question.getId().equals(null) || team.getId().equals(null) || answer.getTextAnswer().equals(null)) {
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.entity("cannot be empty");
            Response response = builder.build();
            throw new WebApplicationException(response);
        }else{
            answer.setTextAnswer(answer.getTextAnswer());
            answer.addTeam(team);
            answer.setQuestion(question);    
            answerRepository.persist(answer);   
        }
  
            return answer;

        
    }

    @POST
    @Tag(name="GAME")
    @Path("playerfeedback")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Feedback createFeedback(
        @RequestBody User user, @RequestBody Answer answer, @RequestBody Feedback feedback) throws WebApplicationException{

        user = userRepository.findById(user.getId());
        answer = answerRepository.findById(user.getId());
        
        if(user.getId().equals(null) || answer.getId().equals(null) || feedback.getTextFeedback().isEmpty()) {
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.entity("cannot be empty");
        }else{
            feedback.addAnswer(answer);
            feedback.setUser(user);
            feedback.setTextFeedback(feedback.getTextFeedback());
            feedbackRepository.persist(feedback);
        }     
        return feedback;

        
    }


    @PUT
    @Tag(name="GAME")
    @Path("buycard")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game buyCard(@RequestBody Game game) throws WebApplicationException{
        game = gameRepository.findById(game.getId());
        final Card card = new Card();

        if(game.getId().equals(null)) {
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.entity("cannot be empty");
            Response response = builder.build();
            throw new WebApplicationException(response);
        }else{
            game.addCard(card);
            gameRepository.isPersistent(game);
        }
        return game;
    }

    @POST
    @Tag(name="GAME")
    @Path("playerteam")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Team teams(@RequestBody ArrayList<User> users) throws WebApplicationException{
        
        final ArrayList<Team> teams = new ArrayList<Team>();
        final Team team = new Team();
       
        if(users.isEmpty()) {
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.entity("cannot be empty");
            Response response = builder.build();
            throw new WebApplicationException(response);
        }else{
            for(User name: users){
                team.addUser(userRepository.find("name", name).firstResult());
                teams.add(team);
            }
            teamRepository.persist(team);
         }
      
            return team;
    }

    @POST
    @Tag(name="GAME")
    @Path("playergame")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game createGame(@RequestBody ArrayList<Long> ids) throws WebApplicationException{
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
                game.addTeam(teamRepository.findById(id));
                games.add(game);
            }
            gameRepository.persist(game);
        }
            return game;
    }

        

   

}
