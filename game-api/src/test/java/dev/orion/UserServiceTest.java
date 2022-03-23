package dev.orion;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.orion.game.model.*;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class UserServiceTest {


    @Test
    @DisplayName("Create User")
    void userCreateTrue() {
        User user = new User();
        user.setName("guilherme");
        user.setEmail("Z3VpbGhlcm1l");
        user.setPassword("123456");
        assertEquals("guilherme", user.getName());
        assertEquals("Z3VpbGhlcm1l", user.getPassword());

    }

    @Test
    @DisplayName("Create User Error")
    void userCreateError() {
        User user = new User();
        user.setName("guilherme");
        user.setEmail("Z3VpbGhlcm1l");
        user.setPassword("123456");
        assertNotEquals("geralt", user.getName());
        assertNotEquals("o3xzghlcm1l", user.getPassword());
        
    }

    @Test
    @DisplayName("Create User Empty")
    void userCreateEmpty() {
        User user = new User();
        user.setName("");
        user.setPassword("");
        assertNotEquals("guilherme", user.getName());
        assertNotEquals("Z3VpbGhlcm1l", user.getPassword());
    }

    @Test
    @DisplayName("Create User Null")
    void userCreateNull() {
        User user = new User();
        user.setName(null);
        user.setPassword(null);
        assertNotEquals("guilherme", user.getName());
        assertNotEquals("Z3VpbGhlcm1l", user.getPassword());
    }


}
