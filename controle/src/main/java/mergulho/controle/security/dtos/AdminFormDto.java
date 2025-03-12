package mergulho.controle.security.dtos;

public record AdminFormDto(
        String nome,
        String email,
        String senha,
        String confirmacaoSenha
) {}