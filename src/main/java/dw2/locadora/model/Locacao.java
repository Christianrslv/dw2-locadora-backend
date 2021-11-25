package dw2.locadora.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "locacao")
public class Locacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dt_locacao")
    private LocalDate dtLocacao;

    @Column(name = "dt_devoluvao_prevista")
    private LocalDate dtDevolucaoPrevista;

    @Column(name = "dt_devolucao_efetiva")
    private LocalDate dtDevolucaoEfetiva;

    @Column(name = "valor_cobrado")
    private Double valorCobrado;

    @Column(name = "multa_cobrada")
    private Double multaCobrada;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Socio customer;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
