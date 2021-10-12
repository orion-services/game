package dev.orion.game.controller;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;
import dev.orion.game.entity.User;
import dev.orion.game.entity.Team;

@RequestScoped
@Tag(name="USER")
@Path("/api/v1/")
public class UserController extends BaseController{
    @POST
    @Path("playerlogin")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String login(@FormParam("email") final String email, @FormParam("password") final String password)
            throws WebApplicationException  {

        String message=null;
        
        try {
            final User user = userDAO.find("email", email).firstResult();

            if(user.getPassword().equals(user.MD5(password))){
               return message="ok";

            } else { 
            message = "The user provides a wrong e-mail or password";
            throw new WebApplicationException("The Wrong password", Response.Status.NOT_FOUND);
        }
        } catch (Exception e) {
            message = "The login method generates a DAO Exception";
            throw new WebApplicationException(message, Response.Status.NOT_FOUND);
    }
        
}

    @POST
    @Tag(name="USER")
    @Path("playeruser")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User createUser(@FormParam("name") final String name, @FormParam("email") final String email,
    @FormParam("password") final String password) {
        final User user = new User();
        try {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            userDAO.persist(user);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        
        return user;
        
    }

    @GET
    @Tag(name="USER")
    @Path("playerall")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<User> listall() {

        String errorMessage = null;
        try {
            return userDAO.listAll();
        
        } catch (Exception e) {
            errorMessage = "The database is out of service ";
            throw new WebApplicationException(errorMessage, Response.Status.INTERNAL_SERVER_ERROR);
        
        }
        
    }

    @POST
    @Tag(name="USER")
    @Path("playerteamsolo")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Team teamSolo(
        @FormParam("textTeam") final String textTeam, 
        @FormParam("name") final String name
        ) {
        
        final User user = userDAO.find("name", name).firstResult();
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
    @Tag(name="USER")
    @Path("playerteam1x1")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Team team1x1(
        @FormParam("textTeam") final String textTeam, 
        @FormParam("name1") final String name1, 
        @FormParam("name2") final String name2
        ) {
        
        final User user1 = userDAO.find("name", name1).firstResult();
        final User user2 = userDAO.find("name", name2).firstResult();
        final Team team = new Team();
        try {
            team.setTextTeam(textTeam);
            team.addUser(user1);
            team.addUser(user2);
            teamDAO.persist(team);

       } catch (Exception e) {
           System.out.println(e);
       }
     
        return team;
    }

    @POST
    @Tag(name="USER")
    @Path("playerteam2x2")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Team team2x2(
        @FormParam("textTeam") final String textTeam, 
        @FormParam("name1") final String name1, 
        @FormParam("name2") final String name2,
        @FormParam("name3") final String name3,
        @FormParam("name4") final String name4
        ) {
        
            final User user1 = userDAO.find("name", name1).firstResult();
            final User user2 = userDAO.find("name", name2).firstResult();
            final User user3 = userDAO.find("name", name3).firstResult();
            final User user4 = userDAO.find("name", name4).firstResult();
        final Team team = new Team();
        try {
            team.setTextTeam(textTeam);
            team.addUser(user1);
            team.addUser(user2);
            team.addUser(user3);
            team.addUser(user4);
            teamDAO.persist(team);

       } catch (Exception e) {
           System.out.println(e);
       }
     
        return team;
    }

    @POST
    @Tag(name="USER")
    @Path("playerteam3x3")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Team team3x3(
        @FormParam("textTeam") final String textTeam, 
        @FormParam("name1") final String name1, 
        @FormParam("name2") final String name2,
        @FormParam("name3") final String name3,
        @FormParam("name4") final String name4,
        @FormParam("name5") final String name5,
        @FormParam("name6") final String name6
        ) {
        
            final User user1 = userDAO.find("name", name1).firstResult();
            final User user2 = userDAO.find("name", name2).firstResult();
            final User user3 = userDAO.find("name", name3).firstResult();
            final User user4 = userDAO.find("name", name4).firstResult();
            final User user5 = userDAO.find("name", name5).firstResult();
            final User user6 = userDAO.find("name", name6).firstResult();
        final Team team = new Team();
        try {
            team.setTextTeam(textTeam);
            team.addUser(user1);
            team.addUser(user2);
            team.addUser(user3);
            team.addUser(user4);
            team.addUser(user5);
            team.addUser(user6);
            teamDAO.persist(team);

       } catch (Exception e) {
           System.out.println(e);
       }
     
        return team;
    }



}
