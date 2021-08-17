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
package dev.orion.entity;

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
import javax.persistence.TableGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;




@Entity
@SequenceGenerator(name="user_seq", sequenceName = "user_seq",initialValue = 1, allocationSize = 1)
@Table(name = "USER")
public class User extends PanacheEntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID")
    private long id;
    

    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "USER_ID")
    private List<Feedback> feedbacks= new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="TEAM_USER",
               joinColumns={@JoinColumn(name="TEAM_ID")},
               inverseJoinColumns={@JoinColumn(name="USER_ID")})
    private List<Team> teams= new ArrayList<>();
 
    private String textUser;

    public User(String textUser) {
        super();
        this.textUser = textUser;
    }


    public User() {
        super();
    }

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }
   

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Feedback> getFeedbacks() {
        return this.feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getTextUser() {
        return this.textUser;
    }

    public void setTextUser(String textUser) {
        this.textUser = textUser;
    }

   
}