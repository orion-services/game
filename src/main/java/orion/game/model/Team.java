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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;


@Entity
@SequenceGenerator(name="team_seq", sequenceName = "team_seq",initialValue = 1, allocationSize = 1)
@Data
@Table(name = "TEAM")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEAM_ID")
    @JsonbTransient
    private long id;   

    @ManyToMany(mappedBy="teams", cascade = CascadeType.ALL)
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="GAME_TEAM",
               joinColumns={@JoinColumn(name="GAME_ID")},
               inverseJoinColumns={@JoinColumn(name="TEAM_ID")})
    private List<Game> games;
 
    private String textTeam;

    public Team() {
        super();
    }

    public Team(String textTeam) {
        this.textTeam = textTeam;
    }

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
   
}