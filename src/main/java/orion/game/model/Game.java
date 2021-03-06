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

import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;


@Entity
@SequenceGenerator(name="game_seq", sequenceName = "game_seq",initialValue = 1, allocationSize = 1)
@Data
@Table(name = "GAME")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GAME_ID")
    @JsonbTransient
    private long id;   

    @ManyToMany(mappedBy="games", cascade = CascadeType.MERGE)
    private List<Card> cards= new ArrayList<>();

    @ManyToMany(mappedBy="games", cascade = CascadeType.MERGE)
    private List<Ranking> rankings= new ArrayList<>();

    @ManyToMany(mappedBy="games", cascade = CascadeType.MERGE)
    private List<Team> teams= new ArrayList<>();

    @ManyToMany(mappedBy="games", cascade = CascadeType.MERGE)
    private List<Question> questions= new ArrayList<>();
 
    private String textGame;

    public Game() {
        super();
    }

    public Game(String textGame) {
        super();
        this.textGame = textGame;
    }

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addRanking(Ranking ranking) {
        this.rankings.add(ranking);
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }



   
}