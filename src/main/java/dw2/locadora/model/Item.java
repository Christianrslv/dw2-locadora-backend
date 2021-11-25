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
@Table(name = "item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "num_serie")
    private String numSerie;

    @Column(name = "dt_aquisicao")
    private LocalDate dtAcquisition;

    @Column(name = "tipo_item")
    private String typeItem;

    @ManyToOne
    @JoinColumn(name = "titulo_id")
    private Titulo title;
}
