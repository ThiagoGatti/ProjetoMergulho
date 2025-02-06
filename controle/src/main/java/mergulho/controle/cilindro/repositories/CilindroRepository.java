package mergulho.controle.cilindro.repositories;

import mergulho.controle.cilindro.domain.CilindroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CilindroRepository extends JpaRepository<CilindroModel, Long> {
}
