package com.hola.bonsai.api.hola_bonsai_api.service;

import com.hola.bonsai.api.hola_bonsai_api.entity.Autor;
import com.hola.bonsai.api.hola_bonsai_api.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) { this.autorRepository = autorRepository; }

    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    public Optional<Autor> findById(Long id) { return autorRepository.findById(id); }

    public Boolean deleteById(Long id) {
        return autorRepository.findById(id).map(
                autor-> {
                    autorRepository.delete(autor);
                    return true;
                }
        ).orElseGet(()-> false);
    }

    public  Optional<Autor> update(Autor autor) {
        return autorRepository.findById(autor.getId()).map(
                autorBD-> {
                    autorBD.setNombre(autor.getNombre());
                    autorBD.setApellido(autor.getApellido());
                    autorBD.setTelefono(autor.getTelefono());
                    autorBD.setCorreo(autor.getCorreo());
                    return autorRepository.save(autorBD);
                }
        );
    }

}