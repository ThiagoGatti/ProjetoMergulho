package mergulho.controle.certificacoes.repository;

import mergulho.controle.certificacoes.domain.CertificacaoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificacaoRepository extends JpaRepository<CertificacaoModel, Long> {
    @Query("SELECT c FROM CertificacaoModel c WHERE " +
            "LOWER(c.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(c.certificadora.nome) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<CertificacaoModel> search(@Param("termo") String termo, Pageable pageable);

    Page<CertificacaoModel> findByCertificadora_IdCertificadora(Long certificadoraId, Pageable pageable);
}