package mergulho.controle.security;

import mergulho.controle.security.dtos.AdminDto;
import mergulho.controle.security.usecases.AdminUsecase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AdminController {
    private final AdminUsecase useCase;

    public AdminController(AdminUsecase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public String login(@RequestBody AdminDto dto) {
        if (useCase.authenticate(dto)) {
            return "Login bem-sucedido!";
        }
        return "Falha na autenticação. Verifique suas credenciais.";
    }
}