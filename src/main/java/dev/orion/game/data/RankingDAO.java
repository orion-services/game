package dev.orion.game.data;

import javax.enterprise.context.ApplicationScoped;

import dev.orion.game.entity.Ranking;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class RankingDAO implements PanacheRepository<Ranking> {
    
}