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

 
package dev.orion.game.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;




@Entity
@SequenceGenerator(name="team_seq", sequenceName = "team_seq",initialValue = 1, allocationSize = 1)
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    @JoinColumn(name = "answer_id",referencedColumnName = "id")
    private Answer answer;

    @ManyToMany(mappedBy="teams", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnoreProperties("teams")
    private List<User> users= new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinTable(name="game_team",
               joinColumns={@JoinColumn(name="team_id", referencedColumnName = "id")},
               inverseJoinColumns={@JoinColumn(name="game_id", referencedColumnName = "id")})
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnoreProperties("teams")
    private List<Game> games= new ArrayList<>();
 
    private String textTeam;

    public Team() {
        super();
    }

    public Team(String textTeam) {
        this.textTeam = textTeam;
    }

    public void addUser(User user) {
        this.users.add(user);
        user.getTeams().add(this);
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Answer getAnswer() {
        return this.answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public String getTextTeam() {
        return this.textTeam;
    }

    public void setTextTeam(String textTeam) {
        this.textTeam = textTeam;
    }
   
}