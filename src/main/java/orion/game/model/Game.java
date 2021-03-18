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

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
    
    @JsonbTransient
    @Column(name = "QUESTION")
    private Question question;
  
    @JsonbTransient
    @Column(name = "ANSWER")
    private Answer answer;

    @JsonbTransient
    @Column(name = "FEEDBACK")
    private Feedback feedback;

    public Game(String playerQuestion, Answer answer, Feedback feedback) {
        super();
        question = new Question(playerQuestion);
        this.answer = answer;
        this.feedback = feedback;
    }

    public String getPlayerQuestion() {
        return this.question.getPlayerQuestion();
    }
    
    public void setPlayerQuestion(String playerQuestion) {
        this.question.setPlayerQuestion(playerQuestion);
    }

    public Game() {
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