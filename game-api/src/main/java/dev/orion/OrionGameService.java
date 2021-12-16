/**
 * Copyright 2021 Game Service @ https://github.com/orion-services/game
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

 

package dev.orion;

import org.eclipse.microprofile.auth.LoginConfig;

import dev.orion.game.controller.UserController;
import dev.orion.game.controller.GameController;
import io.quarkus.vertx.http.runtime.cors.CORSFilter;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.DeclareRoles;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * path application
 */
@ApplicationPath("/game")
@LoginConfig(authMethod = "MP-JWT", realmName = "jwt-jaspi")
@DeclareRoles({ "protected" })
public class OrionGameService extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();

        resources.add(UserController.class);
        resources.add(GameController.class);
        resources.add(CORSFilter.class);

        return resources;
    }
}
