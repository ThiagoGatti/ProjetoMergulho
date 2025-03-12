package mergulho.controle.users.usecases;

import mergulho.controle.users.domain.UserModel;
import mergulho.controle.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


@Service
public class UserUsecase {

    @Autowired
    private UserRepository userRepository;

    public Page<UserModel> searchUsers(String termo, Pageable pageable) {
        if(termo == null || termo.isEmpty()) {
            return userRepository.findAll(pageable);
        }
        return userRepository.findByNomeContainingOrCpfContaining(termo, pageable);
    }
}
