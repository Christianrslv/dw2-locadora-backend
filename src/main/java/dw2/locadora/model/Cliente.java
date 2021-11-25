package dw2.locadora.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public abstract class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "num_inscricao")
    private String numInscricao;

    @Column(name = "nome")
    private String name;

    @Column(name = "dt_nascimento")
    private LocalDate dtNascimento;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "esta_ativo")
    private Boolean estahAtivo;

    @OneToMany(mappedBy = "customer")
    private List<Locacao> locacoes;
}
