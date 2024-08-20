package mergulho.controle.controllers;

import jakarta.validation.Valid;
import mergulho.controle.dtos.CilindroRecordDto;
import mergulho.controle.service.MailService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import mergulho.controle.models.CilindroModel;
import mergulho.controle.repositories.CilindroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CilindroController {

    @Autowired
    private CilindroRepository cilindroRepository;
    @Autowired
    private MailService mailService;

    @GetMapping("/cilindros")
    public String listCilindros(Model model) {
        model.addAttribute("cilindros", cilindroRepository.findAll());
        return "cilindros/list";
    }

    @GetMapping("/cilindros/new")
    public String newCilindroForm(Model model) {
        model.addAttribute("cilindro", new CilindroModel());
        return "cilindros/form";
    }

    @PostMapping("/cilindros")
    public String saveCilindro(@Valid CilindroRecordDto cilindroRecordDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cilindros/form";
        }
        CilindroModel cilindroModel = new CilindroModel();
        BeanUtils.copyProperties(cilindroRecordDto, cilindroModel);
        cilindroRepository.save(cilindroModel);

        return "redirect:/cilindros";
    }
    @DeleteMapping("/deleteCilindroID/{id}")
    public ResponseEntity<HttpStatus> deleteCilindroID(@PathVariable Long id) {
        cilindroRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
