package dev.orion.game.data;

import javax.enterprise.context.ApplicationScoped;

import dev.orion.game.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserDAO implements PanacheRepository<User> {
    
}