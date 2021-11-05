package br.com.serratec.backend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.serratec.backend.config.MailConfig;
import br.com.serratec.backend.dto.UsuarioDTO;
import br.com.serratec.backend.dto.UsuarioInserirDTO;
import br.com.serratec.backend.exception.EmailException;
import br.com.serratec.backend.model.Usuario;
import br.com.serratec.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MailConfig mailconfig;
	
	public List<UsuarioDTO> listar() {
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		List<Usuario> usuarios = usuarioRepository.findAll();
		for (Usuario usuario : usuarios) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
			usuariosDTO.add(usuarioDTO);
		}
		return usuariosDTO;
	}
	
	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException {
		if(usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()) != null) {
			throw new EmailException("Email já existe! Insira outro.");
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		
		usuario.setSenha(bCryptPasswordEncoder.encode(usuarioInserirDTO.getSenha()));
		usuarioRepository.save(usuario);
		mailconfig.enviarEmail(usuario.getEmail(), "Cadastro De Usuario", "Deu certo!");
		return new UsuarioDTO(usuario);
	}
}
