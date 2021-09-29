package dev.orion.game.controller;

import dev.orion.game.entity.*;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@RequestScoped
@Path("/api/v1/")
public class GameController extends BaseController{

    @POST
    @Path("playeruser")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User createUser(@FormParam("textUser") final String textUser) {
        final User user = new User();
        user.setTextUser(textUser);
        userDAO.persist(user);
        return user;
        
    }


    @POST
    @Path("playerteam")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Team createTeam(@FormParam("textTeam") final String textTeam, @FormParam("idUser") final long idUser) {
        
        final User user = userDAO.findById(idUser);
        final Team team = new Team();
        try {
            team.setTextTeam(textTeam);
            team.addUser(user);
            teamDAO.persist(team);

       } catch (Exception e) {
           System.out.println(e);
       }
     
        return team;
    }

    @POST
    @Path("playergame")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game createGame(@FormParam("idTeam") final long idTeam) {
      final Game game = new Game();
      final Team team = teamDAO.findById(idTeam);

        game.addTeam(team);
        gameDAO.persist(game);

        return game;
    }

    @PUT
    @Path("buycard")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game buyCard(@FormParam("idGame") final long idGame) {
        final Game game = gameDAO.findById(idGame);
        final Card card = new Card();

        game.addCard(card);
        gameDAO.isPersistent(game);

        return game;
    }


    @POST
    @Path("playerquestion")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Question createQuestion(@FormParam("idGame") final long idGame) {

        final Game game = gameDAO.findById(idGame);
        final Question question =  new Question();
        question.addGame(game); 
        questionDAO.persist(question);        
                return question;

        
    }

    @POST
    @Path("playeranswer")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Answer createAnswer(@FormParam("idQuestion") final long idQuestion, @FormParam("idTeam") final long idTeam, @FormParam("textAnswer") final String textAnswer) {

        final Question question = questionDAO.findById(idQuestion);
        final Team team = teamDAO.findById(idTeam);
        final Answer answer =  new Answer();

        answer.setTextAnswer(textAnswer);
        answer.addTeam(team);
        answer.setQuestion(question);    
        answerDAO.persist(answer);     
                return answer;

        
    }

    @POST
    @Path("playerfeedback")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Feedback createFeedback(@FormParam("idUser") final long idUser, @FormParam("idAnswer") final long idAnswer, @FormParam("textFeedback") final String textFeedback) {

        final Feedback feedback = new Feedback();
        final User user = userDAO.findById(idUser);
        final Answer answer = answerDAO.findById(idAnswer);

        feedback.addAnswer(answer);
        feedback.setUser(user);
        feedback.setTextFeedback(textFeedback);
        feedbackDAO.persist(feedback);
                
                return feedback;

        
    }

}
