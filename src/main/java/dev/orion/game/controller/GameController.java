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
    @Path("playerteam")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Team teams(ArrayList<String> names){
        
        final ArrayList<Team> teams = new ArrayList<Team>();
        final Team team = new Team();
       
        if(names.isEmpty()){
            throw new IllegalStateException("a was null");
        }else{
        try {
            for(String name: names){
                team.addUser(userDAO.find("name", name).firstResult());
                teams.add(team);
            }
            teamDAO.persist(team);
        } catch (Exception e) {
                System.out.println(e);
            }
            return team;
        }
    }

    @POST
    @Tag(name="GAME")
    @Path("playergame")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game createGame(ArrayList<Long> ids) {
      final ArrayList<Game> games = new ArrayList<Game>();
      final Game game = new Game();
       
      if(ids.isEmpty()){
            throw new IllegalStateException("a was null");
        }else{
            try {
                for(Long id: ids){
                    game.addTeam(teamDAO.findById(id));
                    games.add(game);
                }
                gameDAO.persist(game);
            } catch (Exception e) {
                System.out.println(e);
            }
            return game;
        }
      }
        

   

}
