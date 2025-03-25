package mergulho.controle.certificadoras;

import mergulho.controle.certificadoras.domain.CertificadoraModel;
import mergulho.controle.certificadoras.dtos.CertificadoraDto;
import mergulho.controle.certificadoras.repository.CertificadoraRepository;
import mergulho.controle.certificadoras.usecases.CertificadoraUsecase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/certificadoras")
public class CertificadoraController {

    @Autowired
    private CertificadoraUsecase certificadoraUsecase;

    @Autowired
    private CertificadoraRepository certificadoraRepository;
    @GetMapping
    public String listarCertificadoras(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sort,
            Model model) {

        Pageable pageable = PageRequest.of(page, size,
                sort != null ? Sort.by(sort) : Sort.by("nome"));

        Page<CertificadoraModel> certificadorasPage;

        if (search != null && !search.isEmpty()) {
            certificadorasPage = certificadoraRepository.findByNomeContaining(search, pageable);
        } else {
            certificadorasPage = certificadoraRepository.findAll(pageable);
        }

        model.addAttribute("certificadoras", certificadorasPage); // Agora é uma Page
        model.addAttribute("searchTerm", search);
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);

        return "certificadoras/list";
    }

    @GetMapping("/new")
    public String mostrarFormulario(Model model) {
        model.addAttribute("certificadora", new CertificadoraDto());
        return "certificadoras/form";
    }

    @PostMapping
    public String salvarCertificadora(@ModelAttribute("certificadora") CertificadoraDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "certificadoras/form";
        }

        CertificadoraModel model = new CertificadoraModel();
        BeanUtils.copyProperties(dto, model);
        certificadoraUsecase.salvarCertificadora(model);
        return "redirect:/certificadoras";
    }

    @GetMapping("/delete/{id}")
    public String deletarCertificadora(@PathVariable Long id) {
        certificadoraUsecase.deletarCertificadora(id);
        return "redirect:/certificadoras";
    }
    @GetMapping("/certificacoes/new")
    public String redirectToNewCertificacao(
            @RequestParam Long certificadoraId,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("certificadoraId", certificadoraId);
        return "redirect:/certificacoes/new";
    }

}