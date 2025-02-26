package mergulho.controle.users.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "TB_USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idUser;

    @NotNull(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    @Column(name = "nome")
    private String nome;

    @NotNull(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Column(name = "data_nascimento")
    private String dataNascimento;

    @NotNull(message = "Número de celular é obrigatório")
    @Column(name = "numero_celular")
    private String numeroCelular;

    @NotNull(message = "Sexo é obrigatório")
    @Column(name = "sexo")
    private String sexo;

    @Column(name = "pais")
    private String pais;

    @Column(name = "estado")
    private String estado;

    @Column(name = "cep")
    private String cep;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "numero")
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "profissao")
    private String profissao;

    @Column(name = "rg")
    private String rg;

    @Column(name = "cpf")
    private String cpf;


}
