package mergulho.controle.security.usecases;

import mergulho.controle.security.domain.AdminModel;
import mergulho.controle.security.dtos.AdminDto;
import mergulho.controle.security.dtos.AdminUpdateDto;
import mergulho.controle.security.repository.AdminRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminUsecase {
    private final AdminRepository repository;

    public AdminUsecase(AdminRepository repository) {
        this.repository = repository;
    }

    // Métodos CRUD
    public List<AdminModel> findAllAdmins() {
        return repository.findAll();
    }

    public AdminModel findAdminById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public AdminModel saveAdmin(AdminModel admin) {
        admin.setId(null);
        return repository.save(admin);
    }

    public AdminModel updateAdmin(Long id, AdminUpdateDto adminDetails) {
        AdminModel admin = repository.findById(id).orElseThrow();

        if (adminDetails.newPassword() != null && !adminDetails.newPassword().isBlank()) {
            if (!admin.getSenha().equals(adminDetails.currentPassword())) {
                throw new IllegalArgumentException("Senha atual incorreta!");
            }

            if (!adminDetails.newPassword().equals(adminDetails.confirmPassword())) {
                throw new IllegalArgumentException("As novas senhas não coincidem!");
            }

            admin.setSenha(adminDetails.newPassword());
        }

        admin.setNome(adminDetails.nome());
        admin.setEmail(adminDetails.email());

        return repository.save(admin);
    }

    public void deleteAdmin(Long id) {
        repository.deleteById(id);
    }
    public boolean authenticate(AdminDto dto) {
        Optional<AdminModel> adminModel = repository.findByEmail(dto.email());
        return adminModel.isPresent() && adminModel.get().getSenha().equals(dto.senha());
    }
    public Page<AdminModel> searchAdmins(String searchTerm, Pageable pageable) {
        if (searchTerm != null && !searchTerm.isEmpty()) {
            return repository.findByNomeContainingOrEmailContaining(searchTerm, searchTerm, pageable);
        }
        return repository.findAll(pageable);
    }
}
