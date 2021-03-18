package orion.game.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import lombok.Data;
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "playerQuestion")
    private String playerQuestion;

    //...

    public Question(String playerQuestion) {
      this.playerQuestion = playerQuestion;
  }


  public String getPlayerQuestion() {
    return this.playerQuestion;
}

public void setPlayerQuestion(String playerQuestion) {
    this.playerQuestion = playerQuestion;
}



    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "game", 
      joinColumns = 
        { @JoinColumn(name = "question_id", referencedColumnName = "id") },
      inverseJoinColumns = 
        { @JoinColumn(name = "answer_id", referencedColumnName = "id") })
    private Answer answer;


    //... getters and setters
}
