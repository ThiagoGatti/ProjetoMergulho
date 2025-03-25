package mergulho.controle.certificacoes;

import mergulho.controle.certificacoes.domain.CertificacaoModel;
import mergulho.controle.certificacoes.dtos.CertificacaoDto;
import mergulho.controle.certificacoes.usecases.CertificacaoUsecase;
import mergulho.controle.certificadoras.domain.CertificadoraModel;
import mergulho.controle.certificadoras.repository.CertificadoraRepository;
import mergulho.controle.certificadoras.usecases.CertificadoraUsecase;
import mergulho.controle.certificacoes.repository.CertificacaoRepository;
import org.antlr.v4.runtime.misc.Interval;
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

@Controller
@RequestMapping("/certificacoes")
public class CertificacaoController {

    @Autowired
    private CertificacaoUsecase certificacaoUsecase;

    @Autowired
    private CertificadoraUsecase certificadoraUsecase;

    @Autowired
    private CertificacaoRepository certificacaoRepository;

    @Autowired
    private CertificadoraRepository certificadoraRepository;

    @GetMapping
    public String listarCertificacoes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sort,
            Model model) {

        Pageable pageable = PageRequest.of(page, size,
                sort != null ? Sort.by(sort) : Sort.by("nome"));

        Page<CertificacaoModel> certificacoesPage;

        if (search != null && !search.isEmpty()) {
            certificacoesPage = certificacaoRepository.search(search, pageable);
        } else {
            certificacoesPage = certificacaoRepository.findAll(pageable);
        }

        model.addAttribute("certificacoes", certificacoesPage);
        model.addAttribute("searchTerm", search);
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);

        return "certificacoes/list";
    }


    @GetMapping("/new")
    public String mostrarFormulario(
            @RequestParam(required = false) Long certificadoraId,
            Model model) {

        CertificacaoDto dto = new CertificacaoDto();
        if (certificadoraId != null) {
            dto.setCertificadoraId(certificadoraId);
        }

        model.addAttribute("certificacao", dto);
        model.addAttribute("certificadoras", certificadoraUsecase.listarTodas());
        return "certificacoes/form";
    }
    @PostMapping
    public String salvarCertificacao(@ModelAttribute("certificacao") CertificacaoDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("certificadoras", certificadoraUsecase.listarTodas());
            return "certificacoes/form";
        }

        CertificacaoModel certificacao = new CertificacaoModel();
        BeanUtils.copyProperties(dto, certificacao);

        CertificadoraModel certificadora = certificadoraUsecase.listarTodas().stream()
                .filter(c -> c.getIdCertificadora().equals(dto.getCertificadoraId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Certificadora não encontrada"));

        certificacao.setCertificadora(certificadora);
        certificacaoUsecase.salvarCertificacao(certificacao);
        return "redirect:/certificacoes";
    }

    @GetMapping("/delete/{id}")
    public String deletarCertificacao(@PathVariable Long id) {
        certificacaoUsecase.deletarCertificacao(id);
        return "redirect:/certificacoes";
    }
}