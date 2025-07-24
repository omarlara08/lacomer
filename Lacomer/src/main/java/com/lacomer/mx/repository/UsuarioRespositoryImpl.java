package com.lacomer.mx.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lacomer.mx.dto.UsuarioDto;
import com.lacomer.mx.model.Usuario;

@Repository
public class UsuarioRespositoryImpl implements UsuarioRepository {
	
	@PersistenceContext
    private EntityManager em;


	@Override
	public List<UsuarioDto> listarTodos() {
		 List<Usuario> listaUsu =  em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
		 List<UsuarioDto> listaDto = new ArrayList<UsuarioDto>();
		 for (Usuario usuario : listaUsu) {
			 UsuarioDto dto = new UsuarioDto();
				dto.setNombre(usuario.getNombre());
				dto.setApellidoMaterno(usuario.getApellidoPaterno());
				dto.setApellidoPaterno(usuario.getApellidoMaterno());
				dto.setCorreo(usuario.getCorreo());
				listaDto.add(dto);
		}
	return listaDto;
	}



	@Override
	public UsuarioDto obtenerPorId(Long id) {
		Usuario usuario =  em.find(Usuario.class, id);
		UsuarioDto dto = new UsuarioDto();
		dto.setNombre(usuario.getNombre());
		dto.setApellidoMaterno(usuario.getApellidoPaterno());
		dto.setApellidoPaterno(usuario.getApellidoMaterno());
		dto.setCorreo(usuario.getCorreo());
		return dto;
		

	}

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		  em.persist(usuario);
	        return usuario;

	}

	@Override
	public Usuario actualizarUsuario(Long id, Usuario usuario) {
		 Usuario existente = em.find(Usuario.class, id);
	        if (existente != null) {
	            existente.setNombre(usuario.getNombre());
	            existente.setApellidoPaterno(usuario.getApellidoPaterno());
	            existente.setApellidoMaterno(usuario.getApellidoMaterno());
	            existente.setCorreo(usuario.getCorreo());
	            em.merge(existente);
	        }
	        return existente;

	}

	@Override
	public void eliminarUsuario(Long id) {
		  Usuario usuario = em.find(Usuario.class, id);
	        if (usuario != null) {
	            em.remove(usuario);
	        }

		
	}

}
