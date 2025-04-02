package mergulho.controle.fornecedores;
import jakarta.validation.Valid;
import mergulho.controle.fornecedores.domain.FornecedoresModel;
import mergulho.controle.fornecedores.dtos.FornecedoresDto;
import mergulho.controle.fornecedores.usecases.FornecedoresUsecase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fornecedores")

public class FornecedoresController {
    private final FornecedoresUsecase fornecedorService;

    public FornecedoresController(FornecedoresUsecase fornecedorService) {
        this.fornecedorService = fornecedorService;
    }


    @GetMapping
    public String listarFornecedores(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(name = "search", required = false) String searchTerm,
            Model model
    ) {
        Page<FornecedoresModel> fornecedoresPage = fornecedorService.listarFornecedores(searchTerm, pageable);

        model.addAttribute("fornecedoresPage", fornecedoresPage);
        model.addAttribute("currentPage", fornecedoresPage.getNumber());
        model.addAttribute("totalPages", fornecedoresPage.getTotalPages());
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("size", pageable.getPageSize());

        return "fornecedores/list";
    }

    @GetMapping("/new")
    public String exibirFormCadastro(Model model) {
        model.addAttribute("fornecedor", new FornecedoresModel()); // Usar Model ao invés de DTO
        return "fornecedores/form";
    }

    @PostMapping
    public String criarFornecedor(
            @Valid @ModelAttribute("fornecedor") FornecedoresModel fornecedorModel,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "fornecedores/form";
        }
        FornecedoresDto dto = new FornecedoresDto(
                fornecedorModel.getNome(),
                fornecedorModel.getEstado(),
                fornecedorModel.getPais()
        );
        fornecedorService.criarFornecedor(dto);
        return "redirect:/fornecedores";
    }

    @GetMapping("/edit/{id}")
    public String exibirFormEdicao(@PathVariable Long id, Model model) {
        FornecedoresModel fornecedor = fornecedorService.buscarPorId(id);
        model.addAttribute("fornecedor", fornecedor);
        return "fornecedores/form";
    }

    @PostMapping("/update/{id}")
    public String atualizarFornecedor(
            @PathVariable Long id,
            @Valid @ModelAttribute("fornecedor") FornecedoresDto fornecedoresDto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "fornecedores/form";
        }
        fornecedorService.atualizarFornecedor(id, fornecedoresDto);
        return "redirect:/fornecedores";
    }

    @GetMapping("/delete/{id}")
    public String excluirFornecedor(@PathVariable Long id) {
        fornecedorService.excluirFornecedor(id);
        return "redirect:/fornecedores";
    }
}
