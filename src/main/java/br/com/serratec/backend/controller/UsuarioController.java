package br.com.serratec.backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import br.com.serratec.backend.dto.UsuarioDTO;
import br.com.serratec.backend.dto.UsuarioInserirDTO;
import br.com.serratec.backend.exception.EmailException;
import br.com.serratec.backend.model.Usuario;
import br.com.serratec.backend.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> inserir(@RequestBody UsuarioInserirDTO usuarioInserirDTO) {
		try {
			UsuarioDTO usuarioDTO = usuarioService.inserir(usuarioInserirDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					  .buildAndExpand(usuarioDTO.getId())
					  .toUri();
			return ResponseEntity.created(uri).body(usuarioDTO);
		} catch (EmailException e) {
			e.printStackTrace();
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	/*
	@GetMapping
	public List<Usuario> listar() {
		return usuarioService.listar();
	}
	*/
	
	public ResponseEntity<List<UsuarioDTO>> listar() {
		return ResponseEntity.ok(usuarioService.listar());
	}
}
