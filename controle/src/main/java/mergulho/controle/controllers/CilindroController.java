package mergulho.controle.controllers;

import mergulho.controle.models.CilindroModel;
import mergulho.controle.repositories.CilindroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CilindroController {

    @Autowired
    private CilindroRepository cilindroRepository;

    @GetMapping("/getAllCilindro")
    public ResponseEntity<List<CilindroModel>> getAllCilindro() {

        try {
            List<CilindroModel> cilindroList = new ArrayList<>();
            cilindroRepository.findAll().forEach(cilindroList::add);
            if (cilindroList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cilindroList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getCilindroID/{id}")
    public ResponseEntity<CilindroModel> getCilindroID(@PathVariable Long id) {
            Optional<CilindroModel> cilindroData = cilindroRepository.findById(id);

            if (cilindroData.isPresent()) {
                return new ResponseEntity<>(cilindroData.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/addCilindro")
    public ResponseEntity<CilindroModel> addCilindro(@RequestBody CilindroModel cilindro) {
        CilindroModel cilindroObj = cilindroRepository.save(cilindro);


        return new ResponseEntity<>(cilindroObj, HttpStatus.OK);

    }
    @PostMapping("/updateCilindroID/{id}")
    public ResponseEntity<CilindroModel> updateCilindroID(@PathVariable Long id, @RequestBody CilindroModel newCilindroData) {

        Optional<CilindroModel> cilindroData = cilindroRepository.findById(id);

        if(cilindroData.isPresent()) {
            CilindroModel updatedCilindroData = cilindroData.get();
            updatedCilindroData.setDataFabricacao(newCilindroData.getDataFabricacao());
            updatedCilindroData.setTipoCilindro(newCilindroData.getTipoCilindro());
            updatedCilindroData.setSerialNum(newCilindroData.getSerialNum());

            CilindroModel cilindroObj = cilindroRepository.save(updatedCilindroData);
            return new ResponseEntity<>(cilindroObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteCilindroID/{id}")
    public ResponseEntity<HttpStatus> deleteCilindroID(@PathVariable Long id) {
        cilindroRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
