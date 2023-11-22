package com.duarteg.Lombok.prjLombok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duarteg.Lombok.prjLombok.entities.Usuario;
import com.duarteg.Lombok.prjLombok.services.UsuarioServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.TableGenerator;
import jakarta.validation.Valid;

@Tag(name="Usuarios", description = "API REST DE GERENCIAMENTO DE USUÁRIOS")
@RestController
@RequestMapping("/usuario")
public class UsarioController {
	
	private final UsuarioServices usuarioservice;

	@Autowired
	public UsarioController(UsuarioServices usuarioservice) {
		this.usuarioservice = usuarioservice;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza usuário por ID")
	public ResponseEntity<Usuario> findUsuariobyId(@PathVariable Long id) {
		Usuario usuario = usuarioservice.findUsuarioById(id);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary ="Apresenta todos os usuários")
	public ResponseEntity<List<Usuario>> findAllUsuarioscontrol() {
		List<Usuario> usuarios = usuarioservice.findAllUsuario();
		return ResponseEntity.ok(usuarios);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra um usuário")
	public ResponseEntity<Usuario> insertUsuariosControl(@RequestBody @Valid Usuario usuario) {
		Usuario novoUsuario = usuarioservice.insertUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altere um usuário")
	public ResponseEntity<Usuario> updateUsuarioControl(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
		Usuario mudausuario = usuarioservice.updateUsuario(id, usuario);
		if (mudausuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Excluir um usuário")
	public ResponseEntity<String> deleteUsuarioControl(@PathVariable Long id) {
		boolean remover = usuarioservice.deleteUsuario(id);
		if (remover) {
			return ResponseEntity.ok().body("usuario Excluido com sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
