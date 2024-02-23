package com.generation.ProjetoGeneration.Controller;


import com.generation.ProjetoGeneration.Model.TemaModel;
import com.generation.ProjetoGeneration.Repository.TemaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/tema")
public class TemaController {

    @Autowired
    private TemaRepository temaRepository;
    @GetMapping
    public ResponseEntity<List<TemaModel>> getAll(){
        return ResponseEntity.ok(temaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemaModel>getById(@PathVariable Long id) {
        return temaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PostMapping
    public ResponseEntity<TemaModel>post(@Valid @RequestBody TemaModel tema){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(temaRepository.save(tema));
    }
    @PutMapping
    public ResponseEntity<TemaModel>put (@Valid @RequestBody TemaModel tema){
        return temaRepository.findById(tema.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(temaRepository.save(tema)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<TemaModel> tema = temaRepository.findById(id);

        if(tema.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        temaRepository.deleteById(id);
    }
}
