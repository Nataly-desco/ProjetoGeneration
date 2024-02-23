package com.generation.ProjetoGeneration.Service;

import com.generation.ProjetoGeneration.Model.UsuarioLogin;
import com.generation.ProjetoGeneration.Model.UsuarioModel;
import com.generation.ProjetoGeneration.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<UsuarioModel> cadastrarUsuario(UsuarioModel usuario) {

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
            return Optional.empty();

        return Optional.of(usuarioRepository.save(usuario));
    }

    public Optional<UsuarioModel> atualizarUsuario(UsuarioModel usuario) {

        if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            Optional<UsuarioModel> buscaUsuario = usuarioRepository.findByEmail(usuario.getEmail());

            if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != usuario.getId()))
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Email já existe!", null);

            return Optional.ofNullable(usuarioRepository.save(usuario));
        }
        return Optional.empty();
    }

    public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

        Optional<UsuarioModel> usuario = usuarioRepository.findByEmail(usuarioLogin.get().getEmail());

        if (usuario.isPresent()) {

            usuarioLogin.get().setId(usuario.get().getId());
            usuarioLogin.get().setNome(usuario.get().getNome());
            usuarioLogin.get().setFoto(usuario.get().getFoto());

            return usuarioLogin;

        }

        return Optional.empty();
    }
}

