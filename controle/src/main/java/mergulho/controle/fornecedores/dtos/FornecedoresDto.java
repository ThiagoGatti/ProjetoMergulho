package mergulho.controle.fornecedores.dtos;

import jakarta.validation.constraints.NotBlank;

public record FornecedoresDto(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        String estado,

        @NotBlank(message = "País é obrigatório")
        String pais
) {}
