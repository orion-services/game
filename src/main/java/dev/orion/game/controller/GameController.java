package dev.orion.game.controller;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

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
    public Question createQuestion(@FormParam("idGame") final long idGame) {

        final Game game = gameDAO.findById(idGame);
        final Question question =  new Question();
        question.addGame(game); 
        questionDAO.persist(question);        
                return question;

        
    }

    @POST
    @Tag(name="GAME")
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
    @Tag(name="GAME")
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


    @PUT
    @Tag(name="GAME")
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
    @Tag(name="GAME")
    @Path("playergamesolo")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game createGameSolo(@FormParam("idTeam") final long idTeam) {
      final Game game = new Game();
      final Team team = teamDAO.findById(idTeam);

        game.addTeam(team);
        gameDAO.persist(game);

        return game;
    }

    @POST
    @Tag(name="GAME")
    @Path("playergame1x1")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game createGame1x1(
        @FormParam("idTeam1") final long idTeam1,
        @FormParam("idTeam2") final long idTeam2) {

      final Game game = new Game();
      final Team team1 = teamDAO.findById(idTeam1);
      final Team team2 = teamDAO.findById(idTeam2);

        game.addTeam(team1);
        game.addTeam(team2);
        gameDAO.persist(game);

        return game;
    }

    @POST
    @Tag(name="GAME")
    @Path("playergame2x2")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game createGame2x2(
        @FormParam("idTeam1") final long idTeam1,
        @FormParam("idTeam2") final long idTeam2,
        @FormParam("idTeam3") final long idTeam3,
        @FormParam("idTeam4") final long idTeam4) {

      final Game game = new Game();
      final Team team1 = teamDAO.findById(idTeam1);
      final Team team2 = teamDAO.findById(idTeam2);
      final Team team3 = teamDAO.findById(idTeam3);
      final Team team4 = teamDAO.findById(idTeam4);

        game.addTeam(team1);
        game.addTeam(team2);
        game.addTeam(team3);
        game.addTeam(team4);
        gameDAO.persist(game);

        return game;
    }

    @POST
    @Tag(name="GAME")
    @Path("playergame3x3")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game createGame3x3(
        @FormParam("idTeam1") final long idTeam1,
        @FormParam("idTeam2") final long idTeam2,
        @FormParam("idTeam3") final long idTeam3,
        @FormParam("idTeam4") final long idTeam4,
        @FormParam("idTeam5") final long idTeam5,
        @FormParam("idTeam6") final long idTeam6) {

      final Game game = new Game();
      final Team team1 = teamDAO.findById(idTeam1);
      final Team team2 = teamDAO.findById(idTeam2);
      final Team team3 = teamDAO.findById(idTeam3);
      final Team team4 = teamDAO.findById(idTeam4);
      final Team team5 = teamDAO.findById(idTeam5);
      final Team team6 = teamDAO.findById(idTeam6);

        game.addTeam(team1);
        game.addTeam(team2);
        game.addTeam(team3);
        game.addTeam(team4);
        game.addTeam(team5);
        game.addTeam(team6);
        gameDAO.persist(game);

        return game;
    }



}
