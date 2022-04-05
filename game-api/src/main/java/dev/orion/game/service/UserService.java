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
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.specimpl.ResponseBuilderImpl;

import dev.orion.game.model.Team;
import dev.orion.game.model.User;

@RequestScoped
@Tag(name="USER")
@Path("/api/v1/")
public class UserService extends BaseRepository{
    @POST
    @Path("playerlogin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String login(@RequestBody User user)
            throws WebApplicationException  {

        String message=null;
        
        try {
            final User userFind = userRepository.find("email", user.getEmail()).firstResult();

            if(userFind.getPassword().equals(userFind.MD5(user.getPassword()))){
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
@Path("playeruser")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public User createUser(@RequestBody User user) throws WebApplicationException{

    if(user.getName().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()){
        ResponseBuilderImpl builder = new ResponseBuilderImpl();
        builder.status(Response.Status.BAD_REQUEST);
        builder.entity("cannot be empty");
        Response response = builder.build();
        throw new WebApplicationException(response);
    }else{
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        userRepository.persist(user);

    }
    return user;
}



    @POST
    @Tag(name="USER")
    @Path("playerlist")
    @Consumes(MediaType.APPLICATION_JSON)  
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<User> listUsers(@RequestBody User user) throws WebApplicationException{

        final List<User> userList = userRepository.list("name", user.getName());

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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Team teams(@RequestBody ArrayList<String> names) throws WebApplicationException{
        
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
                team.addUser(userRepository.find("name", name).firstResult());
                teams.add(team);
            }
            teamRepository.persist(team);

            return team;
        }
    }




  



}
