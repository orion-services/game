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
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.specimpl.ResponseBuilderImpl;

import dev.orion.game.entity.Team;
import dev.orion.game.entity.User;

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
               return message="login sucessful";

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
    @FormParam("password") final String password) throws WebApplicationException{

        final User user = new User();
        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.entity("cannot be empty");
            Response response = builder.build();
            throw new WebApplicationException(response);
        }else{
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            userDAO.persist(user);

        }
        return user;
    }

    @POST
    @Tag(name="USER")
    @Path("playerlist")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)  
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<User> listUsers(@FormParam("name") String nameuser) throws WebApplicationException{

        final List<User> userList = userDAO.list("name", nameuser);

        if(userList==null){
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.entity("cannot be empty");
            Response response = builder.build();
            throw new WebApplicationException(response);
        }else{
            return userList;
        }
    }

    @POST
    @Tag(name="GAME")
    @Path("playerteam")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Team teams(@FormParam("names") ArrayList<String> names) throws WebApplicationException{
        
        final ArrayList<Team> teams = new ArrayList<Team>();
        final Team team = new Team();
       
        if(names.isEmpty()){
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

            return team;
        }
    }




  



}
