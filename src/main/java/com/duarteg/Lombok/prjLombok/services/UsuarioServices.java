package com.duarteg.Lombok.prjLombok.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duarteg.Lombok.prjLombok.entities.Usuario;
import com.duarteg.Lombok.prjLombok.repository.UsuarioRepositorio;

@Service
public class UsuarioServices {

	private final UsuarioRepositorio usuariorepositorio;
	
	@Autowired
	public UsuarioServices(UsuarioRepositorio usuariorepositorio) {
		this.usuariorepositorio =  usuariorepositorio;
	}
	
	// preparando as buscas por id
	public Usuario findUsuarioById(Long id) {
		Optional <Usuario> Usuario = usuariorepositorio.findById(id);
		return Usuario.orElse(null);
	}
	
	// preparando a busca geral
	public List<Usuario> findAllUsuario() {
		return usuariorepositorio.findAll();
	}
	
	// salvando o Jogo
		public Usuario insertUsuario(Usuario usuario) {
			return usuariorepositorio.save(usuario);
		}
	
		// fazendo o update do jogo com o optional
		public Usuario updateUsuario(Long id, Usuario novoUsuario) {
			Optional<Usuario> usuarioOptional = usuariorepositorio.findById(id);
			if (usuarioOptional.isPresent()) {
				Usuario usuarioExistente = usuarioOptional.get();
				usuarioExistente.setNome(novoUsuario.getNome());
				usuarioExistente.setEmail(novoUsuario.getEmail());
				return usuariorepositorio.save(usuarioExistente);
			} else {
				return null;
			}
		}
		
		// deletando o update do usuario com o optional
		public boolean deleteUsuario(Long id) {
			Optional<Usuario> usuarioExistente = usuariorepositorio.findById(id);
			if (usuarioExistente.isPresent()) {
				usuariorepositorio.deleteById(id);
				return true;
			} else {
				return false;
			}
		}


}
