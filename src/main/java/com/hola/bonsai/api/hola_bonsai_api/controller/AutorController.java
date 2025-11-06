package com.hola.bonsai.api.hola_bonsai_api.controller;

import com.hola.bonsai.api.hola_bonsai_api.dto.AutorDTO;
import com.hola.bonsai.api.hola_bonsai_api.entity.Autor;
import com.hola.bonsai.api.hola_bonsai_api.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private AutorService autorService;

    /*Constructor del controlador, donde se inicializa el servicio autor servicio*/
    private AutorController(AutorService autorService) {
        this.autorService = autorService;
    };

    /*Crea un registro */
    @PostMapping
    public ResponseEntity<AutorDTO> save(@RequestBody AutorDTO autorDTO){
        Autor autor = new Autor();
        autor.setNombre(autorDTO.getNombre());
        autor.setApellido(autorDTO.getApellido());
        autor.setTelefono(autorDTO.getTelefono());
        autor.setCorreo(autorDTO.getCorreo());
        Autor autorSaved = autorService.save(autor);

        AutorDTO autorDTOSaved = new AutorDTO();
        autorDTOSaved.setId(autorSaved.getId());
        autorDTOSaved.setNombre(autorSaved.getNombre());
        autorDTOSaved.setApellido(autorSaved.getApellido());
        autorDTOSaved.setTelefono(autorSaved.getTelefono());
        autorDTOSaved.setCorreo(autorSaved.getCorreo());
        return new ResponseEntity<>(autorDTOSaved,  HttpStatus.CREATED);
    }

    /*Obtiene todos los registros */
    @GetMapping
    public ResponseEntity<List<AutorDTO>>  findAll(){
        List<AutorDTO> autoresDto = autorService.findAll().stream().map(
                autor -> {
                    AutorDTO autorDTO = new AutorDTO();
                    autorDTO.setId(autor.getId());
                    autorDTO.setNombre(autor.getNombre());
                    autorDTO.setApellido(autor.getApellido());
                    autorDTO.setTelefono(autor.getTelefono());
                    autorDTO.setCorreo(autor.getCorreo());
                    return autorDTO;
                }
        ).toList();
        if(autoresDto.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(autoresDto);
    }

    /*Obtiene un registro por id */
    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> findById(@PathVariable Long id){
        Optional<Autor> autorOptional = autorService.findById(id);
        return autorOptional.map(
                autor -> {
                    AutorDTO autorDTO = new AutorDTO();
                    autorDTO.setId(autor.getId());
                    autorDTO.setNombre(autor.getNombre());
                    autorDTO.setApellido(autor.getApellido());
                    autorDTO.setTelefono(autor.getTelefono());
                    autorDTO.setCorreo(autor.getCorreo());
                    return ResponseEntity.ok(autorDTO);
                }
        ).orElseGet(
                () -> ResponseEntity.notFound().build()
        );

    }

    /*Actualiza in registro */
    @PutMapping
    public ResponseEntity<AutorDTO> update(@RequestBody AutorDTO autorDTO){
        Autor autor = new Autor();
        autor.setId(autorDTO.getId());
        autor.setNombre(autorDTO.getNombre());
        autor.setApellido(autorDTO.getApellido());
        autor.setTelefono(autorDTO.getTelefono());
        autor.setCorreo(autorDTO.getCorreo());
        return autorService.update(autor).map(
                autorUpdated -> {
                    AutorDTO autorDTOUpdated = new AutorDTO();
                    autorDTOUpdated.setId(autorUpdated.getId());
                    autorDTOUpdated.setNombre(autorUpdated.getNombre());
                    autorDTOUpdated.setApellido(autorUpdated.getApellido());
                    autorDTOUpdated.setTelefono(autorUpdated.getTelefono());
                    autorDTOUpdated.setCorreo(autorUpdated.getCorreo());
                    return ResponseEntity.ok(autorDTOUpdated);
                }
        ).orElseGet( ()-> ResponseEntity.notFound().build());
    }

    /*Elimina un registro por id */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(autorService.deleteById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}