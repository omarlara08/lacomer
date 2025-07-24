package com.lacomer.mx.demo.controller;

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


import com.lacomer.mx.demo.service.UsuarioService;
import com.lacomer.mx.dto.DireccionDto;
import com.lacomer.mx.dto.UsuarioDto;
import com.lacomer.mx.model.Usuario;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
	
	 @Autowired
	    private UsuarioService usuarioService;

	    @GetMapping
	    public ResponseEntity<List<UsuarioDto>> obtenerTodos() {
	        List<UsuarioDto> usuarios = usuarioService.listarTodos();
	        return ResponseEntity.ok(usuarios);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<UsuarioDto> obtenerPorId(@PathVariable Long id) {
	        UsuarioDto usuario = usuarioService.obtenerPorId(id);	     
	        return ResponseEntity.ok(usuario);
	    }


		@PostMapping
	    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
	        Usuario nuevo = usuarioService.crearUsuario(usuario);
	        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
	    }

	
	    @PutMapping("/{id}")
	    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
	        Usuario actualizado = usuarioService.actualizarUsuario(id, usuarioActualizado);
	        return ResponseEntity.ok(actualizado);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
	        usuarioService.eliminarUsuario(id);
	        return ResponseEntity.noContent().build();
	    }


}
