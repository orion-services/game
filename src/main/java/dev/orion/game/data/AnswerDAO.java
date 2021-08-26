package dev.orion.game.data;

import javax.enterprise.context.ApplicationScoped;

import dev.orion.game.entity.Answer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AnswerDAO implements PanacheRepository<Answer> {
    
}