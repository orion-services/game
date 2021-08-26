package dev.orion.game.data;

import javax.enterprise.context.ApplicationScoped;

import dev.orion.game.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class TeamDAO implements PanacheRepository<Team> {
    
}