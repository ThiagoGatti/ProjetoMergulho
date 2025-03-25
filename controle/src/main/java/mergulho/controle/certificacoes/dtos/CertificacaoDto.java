package mergulho.controle.certificacoes.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CertificacaoDto {
    @NotBlank(message = "O nome da certificação é obrigatório")
    private String nome;

    private String descricao;
    private Integer cargaHoraria;
    private LocalDate validade;

    @NotNull(message = "A certificadora é obrigatória")
    private Long certificadoraId;
}