package dw2.locadora.service;

import dw2.locadora.model.Socio;
import dw2.locadora.repository.SocioRepository;
import dw2.locadora.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SocioService {
    private final SocioRepository socioRepository;
    private static final String MSG = "Socio";

    public Socio save(Socio socio) {
        return socioRepository.save(socio);
    }

    public Socio update(Long id, Socio socio) {
        Socio existent = socioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
        BeanUtils.copyProperties(socio, existent, "id");
        return socioRepository.save(existent);
    }

    public void delete(Long id) {
        socioRepository.deleteById(id);
    }

    public List<Socio> get() {
        return socioRepository.findAll();
    }

    public Socio getById(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
    }
}
