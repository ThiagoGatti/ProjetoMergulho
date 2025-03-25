package mergulho.controle.certificadoras.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mergulho.controle.certificacoes.domain.CertificacaoModel;

import java.util.List;

@Entity
@Table(name = "TB_CERTIFICADORA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CertificadoraModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCertificadora;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "certificadora", cascade = CascadeType.ALL)
    private List<CertificacaoModel> certificacoes;
}