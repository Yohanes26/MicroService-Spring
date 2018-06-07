package br.com.exemplo.resources;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import br.com.exemplo.models.Exemplo;
import br.com.exemplo.repository.ExemploRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST Exemplos")
@RestController
@RequestMapping("/exemplo")
public class ExemploResource {
	
	@Autowired
	private ExemploRepository er;
	
	@ApiOperation(value="Retorna lista de Exemplos")
	@GetMapping(produces="application/json")
	public @ResponseBody ArrayList<Exemplo> listaExemplo(){
		Iterable<Exemplo> listaExemplo = er.findAll();
		ArrayList<Exemplo> exemplos = new ArrayList<Exemplo>();
		for(Exemplo exemplo: listaExemplo){
			long codigo = exemplo.getCodigo();
			exemplo.add(linkTo(methodOn(ExemploResource.class).exemplo(codigo)).withSelfRel());
			exemplos.add(exemplo);
		}
		return exemplos;
	}
	
	@ApiOperation(value="Retorna um evento específico")
	@GetMapping(value="/{codigo}", produces="application/json")
	public @ResponseBody Exemplo exemplo(@PathVariable(value="codigo") long codigo){
		Exemplo exemplo = er.findByCodigo(codigo);
		exemplo.add(linkTo(methodOn(ExemploResource.class).listaExemplo()).withRel("Lista de Eventos"));
		return exemplo;
	}
	
	@ApiOperation(value="Salva um Exemplo")
	@PostMapping()
	public Exemplo cadastrarExemplo(@RequestBody @Valid Exemplo exemplo){
		return er.save(exemplo);
	}
	
	@ApiOperation(value="Deleta um Exemplo")
	@DeleteMapping()
	public Exemplo deletarExemplo(@RequestBody Exemplo exemplo){
		er.delete(exemplo);
		return exemplo;
	}

}
