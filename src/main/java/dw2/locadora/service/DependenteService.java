package dw2.locadora.service;

import dw2.locadora.model.Dependente;
import dw2.locadora.repository.DependenteRepository;
import dw2.locadora.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DependenteService {
    private final DependenteRepository dependenteRepository;
    private static final String MSG = "Dependente";

    public Dependente save(Dependente dependente) {
        return dependenteRepository.save(dependente);
    }

    public Dependente update(Long id, Dependente dependente) {
        Dependente existent = dependenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
        BeanUtils.copyProperties(dependente, existent, "id");
        return dependenteRepository.save(existent);
    }

    public void delete(Long id) {
        dependenteRepository.deleteById(id);
    }

    public List<Dependente> get() {
        return dependenteRepository.findAll();
    }

    public Dependente getById(Long id) {
        return dependenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
    }
}
