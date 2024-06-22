package mergulho.controle.repositories;

import mergulho.controle.models.CilindroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CilindroRepository extends JpaRepository<CilindroModel, Long> {


}
