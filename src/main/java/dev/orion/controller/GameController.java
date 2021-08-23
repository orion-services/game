package dev.orion.controller;

import dev.orion.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class GameController {




    @POST
    @Path("playeruser")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User createUser(@FormParam("textUser") final String textUser) {
        final User gameEntity = new User();
        gameEntity.setTextUser(textUser);
        return gameEntity;
    }

    @POST
    @Path("playerteam")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Team createTeam(@FormParam("textTeam") final String textTeam, @FormParam("idUser") final long idUser) {
       final User userEntity = User.findById(idUser);
       final Team gameEntity = new Team();
        gameEntity.setTextTeam(textTeam);
        gameEntity.addUser(userEntity);
        return gameEntity;
    }

    @POST
    @Path("playergame")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game createGame(@FormParam("idTeam") final long idTeam) {
      final Game gameEntity = new Game();
      final Team teamEntity = Team.findById(idTeam);

        gameEntity.addTeam(teamEntity);

        return gameEntity;
    }

    @POST
    @Path("buycard")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game buyCard(@FormParam("idGame") final long idGame) {
        final Game gameEntity = Game.findById(idGame);
        final Card cardEntity = new Card();

        gameEntity.addCard(cardEntity);

        return gameEntity;
    }


    @POST
    @Path("playerquestion")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Question createQuestion(@FormParam("idGame") final long idGame) {

        final Game gameEntity = Game.findById(idGame);
        final Question questionEntity =  new Question();
        questionEntity.addGame(gameEntity);         
                return questionEntity;

        
    }

    @POST
    @Path("playeranswer")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Answer createAnswer(@FormParam("idQuestion") final long idQuestion, @FormParam("idTeam") final long idTeam, @FormParam("textAnswer") final String textAnswer) {

        final Question questionEntity = Question.findById(idQuestion);
        final Team teamEntity = Team.findById(idTeam);
        final Answer answerEntity =  new Answer();

        answerEntity.setTextAnswer(textAnswer);
        answerEntity.addTeam(teamEntity);
        answerEntity.setQuestion(questionEntity);         
                return answerEntity;

        
    }

    @POST
    @Path("playerfeedback")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Feedback createFeedback(@FormParam("idUser") final long idUser, @FormParam("idAnswer") final long idAnswer, @FormParam("textFeedback") final String textFeedback) {

        final Feedback feedbackEntity = new Feedback();
        final User userEntity = User.findById(idUser);
        final Answer answerEntity = Answer.findById(idAnswer);

        feedbackEntity.addAnswer(answerEntity);
        feedbackEntity.setUser(userEntity);
        feedbackEntity.setTextFeedback(textFeedback);
                
                return feedbackEntity;

        
    }

}
