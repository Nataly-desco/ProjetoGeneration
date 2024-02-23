package com.generation.ProjetoGeneration.Repository;

import com.generation.ProjetoGeneration.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    public Optional<UsuarioModel> findByEmail(String email);

}
