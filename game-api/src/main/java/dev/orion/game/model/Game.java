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

 
package dev.orion.game.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Entity
@SequenceGenerator(name="game_seq", sequenceName = "game_seq",initialValue = 1, allocationSize = 1)
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   

    @ManyToMany(mappedBy="games", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Card> cards= new ArrayList<>();

    @ManyToMany(mappedBy="games", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Ranking> rankings= new ArrayList<>();

    @ManyToMany(mappedBy="games", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Team> teams= new ArrayList<>();

    @ManyToMany(mappedBy="games", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Question> questions= new ArrayList<>();
 
    private String textGame;

    public Game() {
        super();
    }

    public Game(String textGame) {
        super();
        this.textGame = textGame;
    }


    public void addCard(Card card) {
        this.cards.add(card);
        card.getGames().add(this);
    }

    public void addRanking(Ranking ranking) {
        this.rankings.add(ranking);
        ranking.getGames().add(this);
    }

    public void addTeam(Team team) {
        this.teams.add(team);
        team.getGames().add(this);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
        question.getGames().add(this);
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Ranking> getRankings() {
        return this.rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getTextGame() {
        return this.textGame;
    }

    public void setTextGame(String textGame) {
        this.textGame = textGame;
    }

   
}