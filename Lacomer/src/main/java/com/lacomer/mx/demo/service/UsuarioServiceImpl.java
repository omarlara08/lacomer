package com.lacomer.mx.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;




import com.lacomer.mx.dto.DireccionDto;
import com.lacomer.mx.dto.UsuarioDto;
import com.lacomer.mx.model.Usuario;
import com.lacomer.mx.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<UsuarioDto> listarTodos() {
	
		return usuarioRepository.listarTodos();
	}

	@Override
	public UsuarioDto obtenerPorId(Long id) {
		DireccionDto direccion = obtenerDireccion();
		
		return usuarioRepository.obtenerPorId(id);
	}

	private DireccionDto obtenerDireccion() {
		
		DireccionDto dto = new DireccionDto();
		WebClient webClient = WebClient.builder()
			    .baseUrl("https://api-sepomex.hckdrk.mx")
			    .defaultHeader(HttpHeaders.AUTHORIZATION, "Token token=token")
			    .build();
		
		JsonNode root = webClient.get()
			    .uri(uriBuilder -> uriBuilder
			        .path("/query/info_cp/{cp}")
			        .queryParam("type", "json")
			        .build(15000))
			    .retrieve()
			    .bodyToMono(JsonNode.class)
			    .block();



		 JsonNode resultado = root.get("response").get("cp");

		    dto.setCodigoPostal(resultado.get("codigo_postal").asText());
		    dto.setEstado(resultado.get("estado").asText());
		    dto.setMunicipio(resultado.get("municipio").asText());

		    // Obtener la primera colonia del arreglo
		    JsonNode colonias = resultado.get("colonias");
		    if (colonias.isArray() && colonias.size() > 0) {
		        dto.setColonia(colonias.get(0).asText());
		    }
		
		return dto;

	}

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		return usuarioRepository.crearUsuario(usuario) ;
	}

	@Override
	public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {		
		return usuarioRepository.actualizarUsuario(id, usuarioActualizado);
	}

	@Override
	public void eliminarUsuario(Long id) {
		 usuarioRepository.eliminarUsuario(id);
		
	}

}
