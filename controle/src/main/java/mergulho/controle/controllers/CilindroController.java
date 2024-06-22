package mergulho.controle.controllers;

import jakarta.validation.Valid;
import mergulho.controle.dtos.CilindroRecordDto;
import mergulho.controle.models.CilindroModel;
import mergulho.controle.repositories.CilindroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CilindroController {

    @Autowired
    CilindroRepository cilindroRepository;


    @PostMapping("/cilindros")
    public ResponseEntity<CilindroModel> saveCilindro(@RequestBody @Valid CilindroRecordDto cilindroRecordDto) {
        var cilindroModel = new CilindroModel();
        BeanUtils.copyProperties(cilindroRecordDto,cilindroModel);
        return ResponseEntity.status(HttpStatus.CREATED).

        body(cilindroRepository.save(cilindroModel));
    }

}
