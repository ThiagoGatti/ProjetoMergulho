package mergulho.controle.security.dtos;

public record AdminUpdateDto(
        Long id,
        String nome,
        String email,
        String currentPassword,
        String newPassword,
        String confirmPassword
) {}
