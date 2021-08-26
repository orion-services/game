package dev.orion;

import org.eclipse.microprofile.auth.LoginConfig;

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

        resources.add(GameController.class);
        resources.add(CORSFilter.class);

        return resources;
    }
}
