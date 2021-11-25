package dw2.locadora.service;

import dw2.locadora.model.Diretor;
import dw2.locadora.repository.DiretorRepository;
import dw2.locadora.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DiretorService {
    private final DiretorRepository diretorRepository;
    private static final String MSG = "Diretor";

    public Diretor save(Diretor diretor) {
        return diretorRepository.save(diretor);
    }

    public Diretor update(Long id, Diretor diretor) {
        Diretor existent = diretorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
        BeanUtils.copyProperties(diretor, existent, "id");
        return diretorRepository.save(existent);
    }

    public void delete(Long id) {
        diretorRepository.deleteById(id);
    }

    public List<Diretor> get() {
        return diretorRepository.findAll();
    }

    public Diretor getById(Long id) {
        return diretorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
    }
}
