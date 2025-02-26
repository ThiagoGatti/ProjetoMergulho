package mergulho.controle.security.usecase;

import mergulho.controle.security.domain.AdminModel;
import mergulho.controle.security.dtos.AdminDto;
import mergulho.controle.security.repository.AdminRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AdminUsecase {
    private final AdminRepository repository;

    public AdminUsecase(AdminRepository repository) {
        this.repository = repository;
    }

    public boolean authenticate(AdminDto dto) {
        Optional<AdminModel> adminModel = repository.findByEmail(dto.email());
        return adminModel.isPresent() && adminModel.get().getSenha().equals(dto.senha());
    }

}
