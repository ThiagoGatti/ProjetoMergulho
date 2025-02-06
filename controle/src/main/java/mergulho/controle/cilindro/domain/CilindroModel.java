package mergulho.controle.cilindro.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "TB_CILINDRO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CilindroModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCilindro;

    @NotNull(message = "A data de fabricação não pode ser nula")
    @Column(name = "data_fabricacao")
    private String dataFabricacao;

    @NotNull(message = "O serial number não pode ser nulo")
    @Column(name = "serial_num")
    private String serialNum;

    @NotNull(message = "O tipo do cilindro não pode ser nulo")
    @Column(name = "tipo_cilindro")
    private String tipoCilindro;
}
