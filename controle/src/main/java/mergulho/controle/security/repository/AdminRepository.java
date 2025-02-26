package mergulho.controle.security.repository;

import mergulho.controle.security.domain.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminModel, Long> {
    Optional<AdminModel> findByEmail(String email);
}
