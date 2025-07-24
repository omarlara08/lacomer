package com.lacomer.mx.demo.service;

import java.util.List;

import com.lacomer.mx.dto.UsuarioDto;
import com.lacomer.mx.model.Usuario;

public interface UsuarioService {

	List<UsuarioDto> listarTodos();

	UsuarioDto obtenerPorId(Long id);

	Usuario crearUsuario(Usuario usuario);

	Usuario actualizarUsuario(Long id, Usuario usuarioActualizado);

	void eliminarUsuario(Long id);

}
