package com.aluraforohub.Repository;

import com.aluraforohub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findBylogin(String login);
}
