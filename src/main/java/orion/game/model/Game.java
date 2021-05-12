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
package orion.game.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;


@Entity
@Data
@Table(name = "GAME")
public class Game {

@TableGenerator(name = "id_generator", table = "ID_GEN", pkColumnName = "gen_name", valueColumnName = "gen_value",
pkColumnValue="game_gen", initialValue=1000, allocationSize=10)
@Id
@GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
    private long id;
    
    @Column(name = "ANSWER")
    private Answer answer;

    @Column(name = "CARD")
    private Card card;

    @Column(name = "FEEDBACK")
    private Feedback feedback;

    @Column(name = "QUESTION")
    private Question question;

    @Column(name = "RANKING")
    private Ranking ranking;

    @Column(name = "TEAM")
    private Team team;

    @Column(name = "USER")
    private User user;

    public Game(){
        this.answer = new Answer();
        this.card = new Card();
        this.feedback = new Feedback();
        this.question = new Question();
        this.ranking = new Ranking();
        this.team = new Team();
        this.user = new User();
    }

    public Game(long id) {
        super();
        this.id = id;
        this.answer = new Answer();
        this.card = new Card();
        this.feedback = new Feedback();
        this.question = new Question();
        this.ranking = new Ranking();
        this.team = new Team();
        this.user = new User();
    }

    public Game(long id, Answer answer, Card card, Feedback feedback, Question question, Ranking ranking, Team team, User user) {
        super();
        this.id = id;
        this.answer = answer;
        this.card = card;
        this.feedback = feedback;
        this.question = question;
        this.ranking = ranking;
        this.team = team;
        this.user = user;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Answer getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer.setAnswer(answer);
    }

    public Card getCard() {
        return this.card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Feedback getFeedback() {
        return this.feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback.setFeedback(feedback);
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question.setQuestion(question);
    }

    public Ranking getRanking() {
        return this.ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


   
}