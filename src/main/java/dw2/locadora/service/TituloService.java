package dw2.locadora.service;

import dw2.locadora.model.Ator;
import dw2.locadora.model.Titulo;
import dw2.locadora.repository.TituloRepository;
import dw2.locadora.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TituloService {
    private final TituloRepository tituloRepository;
    private static final String MSG = "Titulo";

    public Titulo save(Titulo titulo){
        return tituloRepository.save(titulo);
    }

    public Titulo update(Long id, Titulo titulo){
        Titulo existent = tituloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
        BeanUtils.copyProperties(titulo, existent, "id");
        return tituloRepository.save(existent);
    }

    public void delete(Long id){
        tituloRepository.deleteById(id);
    }

    public List<Titulo> get() {
        return tituloRepository.findAll();
    }

    public Titulo getById(Long id){
        return tituloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
    }
}
