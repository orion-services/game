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
package orion.game.service;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;


import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import orion.game.data.AnswerDAO;
import orion.game.data.FeedbackDAO;
import orion.game.data.GameDAO;
import orion.game.data.QuestionDAO;
import orion.game.model.Answer;
import orion.game.model.Feedback;
import orion.game.model.Game;
import orion.game.model.Question;

@RequestScoped
@Path("/api/v1/")
public class PublicService {

    @Inject
    private GameDAO gameDAO;

    @Inject
    private QuestionDAO questionDAO;

    @Inject
    private FeedbackDAO feedbackDAO;

    @Inject
    private AnswerDAO answerDAO;

    @POST
    @APIResponse(responseCode = "200", description = "successfully")
    @APIResponse(responseCode = "409", description = "a conflict has occurred")
    @Tag(name="PLAYER")
    @Path("playerquestion")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Question createQuestion() {

        final Question game = new Question();

            String questions=gameDAO.randomQuestion();
            game.setQuestions(questions);
            questionDAO.create(game);           

                return game;
        
    }

    @POST
    @APIResponse(responseCode = "200", description = "successfully")
    @APIResponse(responseCode = "409", description = "a conflict has occurred")
    @Tag(name="PLAYER")
    @Path("playeranswer")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Answer createAnswer(@FormParam("id") final long id, @FormParam("answers") final String text) {

        final Answer game = answerDAO.find(id);


                game.setAnswers(text);
                answerDAO.update(game);           

                return game;
        
    }


    @POST
    @APIResponse(responseCode = "200", description = "successfully")
    @APIResponse(responseCode = "409", description = "a conflict has occurred")
    @Tag(name="PLAYER")
    @Path("playerfeedback")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Feedback createFeedback(@FormParam("id") final long id, @FormParam("feedbacks") final String feedbacks) throws WebApplicationException, NotFoundException, Exception {

        final Feedback game = feedbackDAO.find(id);

                game.setFeedbacks(feedbacks);
                feedbackDAO.update(game);           

                return game;
        
    }

    @GET
    @APIResponse(responseCode ="200", description ="successfully")
    @APIResponse(responseCode ="409", description ="a conflict has occurred")
    @Tag(name="CRUD")
    @Path("/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Game read(@PathParam("id") final long id) {
        return gameDAO.find(id);
    }


    

}
