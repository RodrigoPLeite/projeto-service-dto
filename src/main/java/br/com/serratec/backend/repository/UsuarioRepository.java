package br.com.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.backend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByNome(String nome);
	Usuario findByEmail(String email);
}
