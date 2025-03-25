package mergulho.controle.certificacoes.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import mergulho.controle.certificadoras.domain.CertificadoraModel;
import java.time.LocalDate;

@Entity
@Table(name = "TB_CERTIFICACAO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CertificacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCertificacao;

    @Column(nullable = false)
    private String nome;

    private String descricao;
    private Integer cargaHoraria;
    private LocalDate validade;

    @ManyToOne
    @JoinColumn(name = "certificadora_id")
    private CertificadoraModel certificadora;
}