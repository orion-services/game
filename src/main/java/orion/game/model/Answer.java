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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;


@Entity
@SequenceGenerator(name="answer_seq", sequenceName = "answer_seq",initialValue = 1, allocationSize = 1)
@Data
@Table(name = "ANSWER")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANSWER_ID")
    private long id;
    
    private Timestamp time;
    
    private String textAnswer;

    public Answer(String textAnswer)    
    {   
        super();  
        this.textAnswer = textAnswer;   
    }    

    @OneToMany(
        mappedBy = "answer",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "ANSWER_ID")
    private List<Team> teams= new ArrayList<>();

    @ManyToOne
    private Question question;

    @ManyToOne
    private Feedback feedback;

    


    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Answer() {
        Calendar calendar = Calendar.getInstance();
        this.time = new Timestamp(calendar.getTimeInMillis());
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }
   
}