package com.duarteg.Lombok.prjLombok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duarteg.Lombok.prjLombok.entities.Usuario;

public interface UsuarioRepositorio extends JpaRepository <Usuario, Long> {

}
