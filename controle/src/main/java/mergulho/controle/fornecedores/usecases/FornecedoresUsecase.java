package mergulho.controle.fornecedores.usecases;

import mergulho.controle.fornecedores.domain.FornecedoresModel;
import mergulho.controle.fornecedores.dtos.FornecedoresDto;
import mergulho.controle.fornecedores.repository.FornecedoresRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FornecedoresUsecase {
    private final FornecedoresRepository repository;

    public FornecedoresUsecase(FornecedoresRepository repository) {
        this.repository = repository;
    }

    public Page<FornecedoresModel> listarFornecedores(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public FornecedoresModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }

    public FornecedoresModel criarFornecedor(FornecedoresDto dto) {
        FornecedoresModel fornecedor = new FornecedoresModel();
        fornecedor.setNome(dto.nome());
        fornecedor.setEstado(dto.estado());
        fornecedor.setPais(dto.pais());
        return repository.save(fornecedor);
    }

    public FornecedoresModel atualizarFornecedor(Long id, FornecedoresDto dto) {
        FornecedoresModel fornecedor = buscarPorId(id);
        fornecedor.setNome(dto.nome());
        fornecedor.setEstado(dto.estado());
        fornecedor.setPais(dto.pais());
        return repository.save(fornecedor);
    }

    public void excluirFornecedor(Long id) {
        repository.deleteById(id);
    }
}

