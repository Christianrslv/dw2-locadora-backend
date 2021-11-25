package dw2.locadora.service;

import dw2.locadora.model.Ator;
import dw2.locadora.repository.AtorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dw2.locadora.service.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AtorService {
    private final AtorRepository atorRepository;
    private static final String MSG = "Ator";

    public Ator save(Ator ator){
        return atorRepository.save(ator);
    }

    public Ator update(Long id, Ator ator){
        Ator existent = atorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
        BeanUtils.copyProperties(ator, existent, "id");
        return atorRepository.save(existent);
    }

    public void delete(Long id){
        atorRepository.deleteById(id);
    }

    public List<Ator> get() {
        return atorRepository.findAll();
    }

    public Ator getById(Long id){
        return atorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
    }
}
