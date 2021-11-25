package dw2.locadora.service;

import dw2.locadora.model.Classe;
import dw2.locadora.repository.ClasseRepository;
import dw2.locadora.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClasseService {
    private final ClasseRepository classeRepository;
    private static final String MSG = "Classe";

    public Classe save(Classe classe) {
        return classeRepository.save(classe);
    }

    public Classe update(Long id, Classe classe) {
        Classe existent = classeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
        BeanUtils.copyProperties(classe, existent, "id");
        return classeRepository.save(existent);
    }

    public void delete(Long id) {
        classeRepository.deleteById(id);
    }

    public List<Classe> get() {
        return classeRepository.findAll();
    }

    public Classe getById(Long id) {
        return classeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
    }
}
