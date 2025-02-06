package mergulho.controle.cilindro;

import jakarta.validation.Valid;
import mergulho.controle.cilindro.dtos.CilindroRecordDto;
import mergulho.controle.cilindro.domain.CilindroModel;
import mergulho.controle.cilindro.repositories.CilindroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cilindros")
public class CilindroController {

    @Autowired
    private CilindroRepository cilindroRepository;

    @GetMapping
    public String listCilindros(Model model) {
        List<CilindroModel> cilindros = cilindroRepository.findAll(); // Corrigido erro de sintaxe
        model.addAttribute("cilindros", cilindros);
        return "cilindros/list";
    }

    @GetMapping("/new")
    public String newCilindroForm(Model model) {
        model.addAttribute("cilindro", new CilindroRecordDto("", "", ""));
        return "cilindros/form";
    }

    @PostMapping
    public String saveCilindro(@Valid @ModelAttribute("cilindro") CilindroRecordDto cilindroRecordDto,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cilindros/form";
        }
        CilindroModel cilindroModel = new CilindroModel();
        BeanUtils.copyProperties(cilindroRecordDto, cilindroModel);
        cilindroRepository.save(cilindroModel);

        return "redirect:/cilindros";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCilindro(@PathVariable Long id) {
        if (cilindroRepository.existsById(id)) {
            cilindroRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
