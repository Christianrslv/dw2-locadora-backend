package dw2.locadora.service;

import dw2.locadora.model.Locacao;
import dw2.locadora.repository.LocacaoRepository;
import dw2.locadora.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LocacaoService {
    private final LocacaoRepository locacaoRepository;
    private static final String MSG = "Locacao";

    public Locacao save(Locacao locacao) {
        return locacaoRepository.save(locacao);
    }

    public Locacao update(Long id, Locacao locacao) {
        Locacao existent = locacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
        BeanUtils.copyProperties(locacao, existent, "id");
        return locacaoRepository.save(existent);
    }

    public void delete(Long id) {
        locacaoRepository.deleteById(id);
    }

    public List<Locacao> get() {
        return locacaoRepository.findAll();
    }

    public Locacao getById(Long id) {
        return locacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
    }
}
