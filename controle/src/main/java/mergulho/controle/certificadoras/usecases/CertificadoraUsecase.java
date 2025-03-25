package mergulho.controle.certificadoras.usecases;

import mergulho.controle.certificadoras.domain.CertificadoraModel;
import mergulho.controle.certificadoras.repository.CertificadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificadoraUsecase {

    @Autowired
    private CertificadoraRepository certificadoraRepository;

    public List<CertificadoraModel> listarTodas() {
        return certificadoraRepository.findAll();
    }

    public CertificadoraModel salvarCertificadora(CertificadoraModel certificadora) {
        return certificadoraRepository.save(certificadora);
    }

    public void deletarCertificadora(Long id) {
        certificadoraRepository.deleteById(id);
    }
}