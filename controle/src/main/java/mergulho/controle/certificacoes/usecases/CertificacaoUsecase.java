package mergulho.controle.certificacoes.usecases;

import mergulho.controle.certificacoes.domain.CertificacaoModel;
import mergulho.controle.certificacoes.repository.CertificacaoRepository;
import mergulho.controle.certificadoras.domain.CertificadoraModel;
import mergulho.controle.certificadoras.repository.CertificadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificacaoUsecase {

    @Autowired
    private CertificacaoRepository certificacaoRepository;

    @Autowired
    private CertificadoraRepository certificadoraRepository;

    public List<CertificacaoModel> listarTodas() {
        return certificacaoRepository.findAll();
    }

    public CertificacaoModel salvarCertificacao(CertificacaoModel certificacao) {
        return certificacaoRepository.save(certificacao);
    }

    public void deletarCertificacao(Long id) {
        certificacaoRepository.deleteById(id);
    }
}