package mergulho.controle.security.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_ADMIN")
@Getter
@Setter
public class AdminModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
}
