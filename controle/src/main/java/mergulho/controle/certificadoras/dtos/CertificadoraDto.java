package mergulho.controle.certificadoras.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificadoraDto {
    @NotBlank(message = "O nome da certificadora é obrigatório")
    private String nome;
}