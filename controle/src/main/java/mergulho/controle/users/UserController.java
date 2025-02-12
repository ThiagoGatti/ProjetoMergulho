package mergulho.controle.users;


import jakarta.validation.Valid;
import mergulho.controle.cilindro.domain.CilindroModel;
import mergulho.controle.cilindro.dtos.CilindroDto;
import mergulho.controle.users.domain.UserModel;
import mergulho.controle.users.dtos.UserDto;
import mergulho.controle.users.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listUsers(Model model) {
        List<UserModel> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "users/form";
    }

    @PostMapping
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/form";
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userRepository.save(userModel);
        return "redirect:/users";
    }

    // 🔹 Formulário de edição do usuário
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        UserModel user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));
        model.addAttribute("user", user);
        return "users/edit";
    }

    // 🔹 Atualizar usuário
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @Valid @ModelAttribute("user") UserDto userDto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/edit";
        }

        UserModel existingUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));
        BeanUtils.copyProperties(userDto, existingUser);
        userRepository.save(existingUser);
        return "redirect:/users";
    }

    // 🔹 Remover usuário
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}
