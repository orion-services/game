package dev.orion.game.data;

import javax.enterprise.context.ApplicationScoped;

import dev.orion.game.entity.Game;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class GameDAO implements PanacheRepository<Game> {
    
}