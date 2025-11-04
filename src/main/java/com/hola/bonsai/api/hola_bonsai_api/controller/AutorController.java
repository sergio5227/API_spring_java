package com.hola.bonsai.api.hola_bonsai_api.controller;

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
    public ResponseEntity<Autor> save(@RequestBody Autor autor){
        return new ResponseEntity<>(autorService.save(autor),  HttpStatus.CREATED);
    }

    /*Obtiene todos los registros */
    @GetMapping
    public ResponseEntity<List<Autor>>  findAll(){
        return ResponseEntity.ok(autorService.findAll());
    }

    /*Obtiene un registro por id */
    @GetMapping("/{id}")
    public ResponseEntity<Autor> findById(@PathVariable Long id){
        Optional<Autor> autorOptional = autorService.findById(id);
        return autorOptional.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build()
        );
    }

    /*Actualiza in registro */
    @PutMapping
    public ResponseEntity<Autor> update(@RequestBody Autor autor){
        return autorService.update(autor).map(ResponseEntity::ok).orElseGet( ()-> ResponseEntity.notFound().build());
    }

    /*Elimina un registro por id */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(autorService.deleteById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}