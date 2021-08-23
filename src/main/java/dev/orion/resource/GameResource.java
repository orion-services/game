package dev.orion.resource;

import dev.orion.controller.GameController;
import dev.orion.entity.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {

    @Inject
    private GameController gameController;

    @GET
    public List<Game> findAll() {
        return Game.listAll();
    }

    @POST
    @Transactional
    public Response createGame(Game game) {
        Game.persist(game);
        return Response.ok(game).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response createGame(@PathParam("idTeam") Long idTeam) {


        Game gameEntity = gameController.createGame(idTeam);

        return Response.ok(gameEntity).build();
    }


}