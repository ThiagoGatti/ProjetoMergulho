package mergulho.controle.security;

import mergulho.controle.security.domain.AdminModel;
import mergulho.controle.security.dtos.AdminUpdateDto;
import mergulho.controle.security.usecase.AdminUsecase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admins")
public class AdminCrudController {

    private final AdminUsecase adminService;

    public AdminCrudController(AdminUsecase adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String listAdmins(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(value = "search", required = false) String searchTerm,
            Model model) {

        Page<AdminModel> adminsPage = adminService.searchAdmins(searchTerm, PageRequest.of(page, size));

        model.addAttribute("admins", adminsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", adminsPage.getTotalPages());
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("size", size);

        return "admin/list";
    }

    // Mostrar formulário de criação
    @GetMapping("/novo")
    public String showAddAdminForm(Model model) {
        model.addAttribute("admin", new AdminModel());
        return "admin/add";
    }

    // Processar criação
    @PostMapping
    public String addAdmin(@ModelAttribute("admin") AdminModel admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admins";
    }

    // Mostrar formulário de edição
    @GetMapping("/editar/{id}")
    public String showEditAdminForm(@PathVariable Long id, Model model) {
        model.addAttribute("admin", adminService.findAdminById(id));
        return "admin/edit";
    }

    // Processar atualização
    @PostMapping("/atualizar/{id}")
    public String updateAdmin(
            @PathVariable Long id,
            @ModelAttribute("admin") AdminUpdateDto adminDto,
            RedirectAttributes redirectAttributes
    ) {
        try {
            adminService.updateAdmin(id, adminDto);
            return "redirect:/admins";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admins/editar/" + id;
        }
    }
    // Excluir admin
    @GetMapping("/excluir/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "redirect:/admins";
    }
}


