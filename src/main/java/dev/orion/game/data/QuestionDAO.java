package dev.orion.game.data;

import javax.enterprise.context.ApplicationScoped;

import dev.orion.game.entity.Question;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class QuestionDAO implements PanacheRepository<Question> {
    
}