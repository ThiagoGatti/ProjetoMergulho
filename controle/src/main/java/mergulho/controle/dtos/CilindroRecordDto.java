package mergulho.controle.dtos;

import jakarta.validation.constraints.NotBlank;

public record CilindroRecordDto(@NotBlank String dataFabricacao,@NotBlank String serialNum,@NotBlank String tipoCilindro) {



}
