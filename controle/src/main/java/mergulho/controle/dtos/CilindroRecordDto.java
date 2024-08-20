package mergulho.controle.dtos;


import jakarta.validation.constraints.NotNull;

public record CilindroRecordDto(
        @NotNull(message = "A data de fabricação não pode ser nula")
        String dataFabricacao,
        @NotNull(message = "O serial number não pode ser nulo")
        String serialNum,
        @NotNull(message = "O tipo do cilindro não pode ser nulo")
        String tipoCilindro)
{
}
