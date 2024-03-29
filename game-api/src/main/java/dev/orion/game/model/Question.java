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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;




@Entity
@SequenceGenerator(name="question_seq", sequenceName = "question_seq",initialValue = 1, allocationSize = 1)
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    @OneToMany(
        mappedBy = "question",
        cascade = CascadeType.PERSIST,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    @JsonIgnore
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Answer> answers= new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinTable(name="game_question",
               joinColumns={@JoinColumn(name="question_id",referencedColumnName = "id")},
               inverseJoinColumns={@JoinColumn(name="game_id",referencedColumnName = "id")})
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Game> games= new ArrayList<>();
 
    private String textQuestion;

    public Question(String textQuestion) {
        super();
        this.textQuestion = textQuestion;
    }


    public Question() {
        super();
    }

    public void addGame(Game game) {
        this.games.add(game);
        game.getQuestions().add(this);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
        answer.setQuestion(this);
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public String getTextQuestion() {
        return this.textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

   
}