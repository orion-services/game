package dev.orion.game.data;

import javax.enterprise.context.ApplicationScoped;

import dev.orion.game.entity.Feedback;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class FeedbackDAO implements PanacheRepository<Feedback> {
    
}