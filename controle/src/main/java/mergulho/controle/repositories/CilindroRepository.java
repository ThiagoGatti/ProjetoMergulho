package mergulho.controle.repositories;

import mergulho.controle.models.CilindroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CilindroRepository extends JpaRepository<CilindroModel, UUID> {


}
