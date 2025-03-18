package mergulho.controle.fornecedores.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_FORNECEDORES")
@Getter
@Setter
public class FornecedoresModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String estado;
    private String pais;

}
