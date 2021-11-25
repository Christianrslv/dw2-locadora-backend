package dw2.locadora.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "titulo")
public class Titulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "ano")
    private String year;

    @Column(name = "sinopse")
    private String synopsis;

    @Column(name = "categoria")
    private String category;

    @ManyToOne
    @JoinColumn(name = "diretor_id")
    private Diretor director;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @ManyToMany
    @JoinTable(
            name = "titulo_ator",
            joinColumns = @JoinColumn(name = "ator_id"),
            inverseJoinColumns = @JoinColumn(name = "titulo_id")
    )
    private List<Ator> actors;
}
