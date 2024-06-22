package mergulho.controle.controllers;

import mergulho.controle.repositories.CilindroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CilindroController {

    @Autowired
    CilindroRepository cilindroRepository;

    

}
