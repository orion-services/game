package dev.orion.game.data;

import javax.enterprise.context.ApplicationScoped;

import dev.orion.game.entity.Card;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CardDAO implements PanacheRepository<Card> {
    
}