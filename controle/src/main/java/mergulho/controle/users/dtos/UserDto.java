package mergulho.controle.users.dtos;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {

    @NotNull(message = "O nome não pode ser nulo")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotNull(message = "O email não pode ser nulo")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Email inválido")
    private String email;

    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    @NotNull(message = "O número de celular não pode ser nulo")
    private String numeroCelular;

    @NotNull(message = "O sexo não pode ser nulo")
    private String sexo;

    private String pais;
    private String estado;
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String telefone;
    private String profissao;
    private String rg;
    private String cpf;

}
