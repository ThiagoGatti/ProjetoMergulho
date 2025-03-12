package mergulho.controle.users.repositories;

import mergulho.controle.users.domain.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query("SELECT u FROM UserModel u WHERE " +
            "LOWER(u.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(u.cpf) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<UserModel> findByNomeContainingOrCpfContaining(
            @Param("termo") String termo,
            Pageable pageable
    );

}
