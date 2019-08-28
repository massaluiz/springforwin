package br.com.st.springforwin.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.st.springforwin.controller.DTO.TopicoDTO;
import br.com.st.springforwin.controller.form.TopicoForm;
import br.com.st.springforwin.controller.form.TopicoFormUpdate;
import br.com.st.springforwin.modelo.Topico;
import br.com.st.springforwin.repository.CursoRepository;
import br.com.st.springforwin.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping("/all")
	public List<TopicoDTO> lista(String courseName) {	
		List<Topico> topicos = null;
		if (courseName != null) {
			topicos = topicoRepository.findByCursoNome(courseName);
		} else {
			topicos = topicoRepository.findAll();
		}
		return TopicoDTO.convert(topicos);
	}
	
	//Builder
	@PostMapping("/insertTopic")
	@Transactional
	public ResponseEntity<TopicoDTO> insertTopic(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
		Topico topico = topicoForm.convert(topicoForm, cursoRepository);
		topicoRepository.save(topico);
		URI responseURI = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(responseURI).body(new TopicoDTO(topico));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicoDTO> getById(@PathVariable Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			return ResponseEntity.ok(new TopicoDTO(topico.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/updateTopic/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> updateTopic(@PathVariable Long id, 
												 @RequestBody @Valid TopicoFormUpdate topicoFormUpdate) {
		Topico topico = topicoFormUpdate.update(id, topicoRepository);
		return ResponseEntity.ok(new TopicoDTO(topico));
		
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/deleteTopic/{id}")
	@Transactional
	public ResponseEntity deleteTopic(@PathVariable Long id) {
		topicoRepository.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
}
