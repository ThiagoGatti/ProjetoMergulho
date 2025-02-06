package mergulho.controle.users;


import jakarta.validation.Valid;
import mergulho.controle.cilindro.domain.CilindroModel;
import mergulho.controle.cilindro.dtos.CilindroDto;
import mergulho.controle.users.domain.UserModel;
import mergulho.controle.users.dtos.UserDto;
import mergulho.controle.users.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Lista de usuários
    @GetMapping
    public String listUsers(Model model) {
        List<UserModel> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users/list";  // Carrega o template list.html
    }

    // Formulário para adicionar novo usuário
    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "users/form";  // Carrega o template form.html
    }

    // Salvar o usuário
    @PostMapping
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/form";  // Se houver erros, volta para o formulário
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userRepository.save(userModel);
        return "redirect:/users";  // Redireciona para a lista de usuários
    }
}
