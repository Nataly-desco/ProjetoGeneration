package com.generation.ProjetoGeneration.Controller;

import com.generation.ProjetoGeneration.Model.PostagemModel;
import com.generation.ProjetoGeneration.Repository.PostagemRepository;
import com.generation.ProjetoGeneration.Repository.TemaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private TemaRepository temaRepository;
    @GetMapping
    public ResponseEntity<List<PostagemModel>> getAll(){
        return ResponseEntity.ok(postagemRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostagemModel>getById(@PathVariable Long id) {
        return postagemRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PostMapping
    public ResponseEntity<PostagemModel>post(@Valid @RequestBody PostagemModel postagem){
        if(temaRepository.existsById(postagem.getTema().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(postagemRepository.save(postagem));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe", null);
    }
    @PutMapping
    public ResponseEntity<PostagemModel>put (@Valid @RequestBody PostagemModel postagem){
    if (postagemRepository.existsById(postagem.getId())){
        if (temaRepository.existsById(postagem.getTema().getId()))
            return ResponseEntity.status(HttpStatus.OK)
                    .body(postagemRepository.save(postagem));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tema não exixte!", null);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @ResponseStatus
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<PostagemModel> postagem = postagemRepository.findById(id);

        if(postagem.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        postagemRepository.deleteById(id);
    }
}
