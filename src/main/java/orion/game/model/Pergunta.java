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
@Data
@Table(name = "pergunta")
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    //...

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "game", 
      joinColumns = 
        { @JoinColumn(name = "pergunta_id", referencedColumnName = "id") },
      inverseJoinColumns = 
        { @JoinColumn(name = "resposta_id", referencedColumnName = "id") })
    private Resposta resposta;

    //... getters and setters
}
