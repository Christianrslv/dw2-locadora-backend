package dw2.locadora.service;

import dw2.locadora.model.Cliente;
import dw2.locadora.repository.ClienteRepository;
import dw2.locadora.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private static final String MSG = "Cliente";

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente cliente) {
        Cliente existent = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
        BeanUtils.copyProperties(cliente, existent, "id");
        return clienteRepository.save(existent);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    public List<Cliente> get() {
        return clienteRepository.findAll();
    }

    public Cliente getById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG));
    }
}
