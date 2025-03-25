package mergulho.controle.certificadoras.repository;

import mergulho.controle.certificadoras.domain.CertificadoraModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificadoraRepository extends JpaRepository<CertificadoraModel, Long> {
    @Query("SELECT c FROM CertificadoraModel c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<CertificadoraModel> findByNomeContaining(@Param("termo") String termo, Pageable pageable);
}