package dev.orion;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.orion.game.model.Team;
import dev.orion.game.model.User;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class GameServiceTest {

    @Test
    @DisplayName("Create team true")
    void teamCreateTrue() {
       
        User user1 = new User();
        user1.setName("guilherme");
        user1.setEmail("Z3VpbGhlcm1l");
        user1.setPassword("123456");

        User user2 = new User();
        user2.setName("pedro");
        user2.setEmail("asdasda");
        user2.setPassword("54443");

        Team team = new Team();
        team.addUser(user1);
        team.addUser(user2);

        assertTrue(team.getUsers().isEmpty());

    }


    @Test
    @DisplayName("Create team false")
    void teamCreateFalse() {
       
        User user1 = new User();
        user1.setName("");
        user1.setEmail("");
        user1.setPassword("");

        User user2 = new User();
        user2.setName("");
        user2.setEmail("");
        user2.setPassword("");

        Team team = new Team();
        team.addUser(user1);
        team.addUser(user2);

        assertTrue(team.getUsers().isEmpty());

    }

   
}
