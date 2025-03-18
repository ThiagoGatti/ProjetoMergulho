package mergulho.controle.fornecedores.repository;

import mergulho.controle.fornecedores.domain.FornecedoresModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedoresRepository extends JpaRepository<FornecedoresModel, Long> {
}
