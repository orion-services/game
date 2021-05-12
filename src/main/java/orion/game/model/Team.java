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
@Table(name = "TEAM")
public class Team {

@TableGenerator(name = "id_generator", table = "ID_GEN", pkColumnName = "gen_name", valueColumnName = "gen_value",
pkColumnValue="team_gen", initialValue=1000, allocationSize=10)
@Id
@GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
    private long id;
    
    @Column(name = "TEAM")
    private String team;

    public Team(String team) {
        super();
        this.team = team;
    }


    public Team() {
        super();
    }

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


   
}