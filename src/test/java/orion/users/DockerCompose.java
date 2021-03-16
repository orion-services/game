/**
 * @License
 * Copyright 2020 Orion Services
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package orion.game;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;

/**
 * This class creates the containers to run integration tests for Orion Talk Service
 */
public class DockerCompose implements BeforeAllCallback, AfterAllCallback {
    
    // Configure the a game service container from Dockerfile
    @Container
    public static ApplicationContainer game = new ApplicationContainer()
        .withAppContextRoot("/orion-game-service/game")
        .waitingFor(Wait.forHttp("/"))
        .withNetworkAliases("service")
        .withExposedPorts(9082);

    public static MySQLContainer<?> mysql;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {

        // create a private network for the containers
        Network network =  Network.newNetwork();

        // Set network for game service
        game.withNetwork(network);
        game.withNetworkAliases("game");

        // create a MySQL container to work with game service
        mysql = new MySQLContainer<>("mysql:latest");
        mysql.withDatabaseName("orion-game-service");
        mysql.withUsername("orion-game-service");
        mysql.withPassword("orion-game-service");
        mysql.withNetwork(network);
        mysql.withNetworkAliases("db");
        mysql.withExposedPorts(3308);
        mysql.waitingFor(Wait.forListeningPort());
        

        game.start();
        mysql.start();
        
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        game.stop();
        mysql.stop();
    }
}