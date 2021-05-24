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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    

    // private Answer answer;

    // private Card card;

    // private Feedback feedback;

    // private Question question;

    // private Ranking ranking;

    // private Team team;

    // private User user;

    // public Game() {
    //     super();
    // }

    // public Game(String answers, String cards, String feedbacks, String questions, String rankings, String teams, String users) {
    //     super();
    //     answer = new Answer(answers);
    //     card = new Card(cards);
    //     feedback = new Feedback(feedbacks);
    //     question = new Question(questions);
    //     ranking = new Ranking(rankings);
    //     team = new Team(teams);
    //     user = new User(users);
    // }


    // public long getId() {
    //     return this.id;
    // }

    // public void setId(long id) {
    //     this.id = id;
    // }

    // public String getAnswer() {
    //     return this.answer.getAnswers();
    // }

    // public void setAnswer(String answers) {
    //     this.answer.setAnswers(answers);
    // }

    // public String getCard() {
    //     return this.card.getCards();
    // }

    // public void setCard(String cards) {
    //     this.card.setCards(cards);
    // }

    // public String getFeedback() {
    //     return this.feedback.getFeedbacks();
    // }

    // public void setFeedback(String feedbacks) {
    //     this.feedback.setFeedbacks(feedbacks);
    // }

    // public String getQuestion() {
    //     return this.question.getQuestions();
    // }

    // public void setQuestion(String questions) {
    //     this.question.setQuestions(questions);
    // }

    // public String getRanking() {
    //     return this.ranking.getRankings();
    // }

    // public void setRanking(String rankings) {
    //     this.ranking.setRankings(rankings);
    // }

    // public String getTeam() {
    //     return this.team.getTeams();
    // }

    // public void setTeam(String teams) {
    //     this.team.setTeams(teams);
    // }

    // public String getUser() {
    //     return this.user.getUsers();
    // }

    // public void setUser(String users) {
    //     this.user.setUsers(users);
    // }


    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


   
}