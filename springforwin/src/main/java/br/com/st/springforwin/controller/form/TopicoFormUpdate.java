package br.com.st.springforwin.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.st.springforwin.modelo.Topico;
import br.com.st.springforwin.repository.CursoRepository;
import br.com.st.springforwin.repository.TopicoRepository;

public class TopicoFormUpdate {

	@NotNull
	@NotEmpty
	@Length(min=5, max=100)
	private String title;
	@NotNull
	@NotEmpty
	private String mesage;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMesage() {
		return mesage;
	}
	public void setMesage(String mesage) {
		this.mesage = mesage;
	}
	
	public Topico convert(TopicoFormUpdate topicoFormUpdate, CursoRepository cursoRepository) {
		return new Topico(topicoFormUpdate.title, topicoFormUpdate.mesage);
	}
	
	public Topico update(Long id, TopicoRepository topicRepository) {
		Topico topico = topicRepository.getOne(id);
		topico.setTitulo(this.title);
		topico.setMensagem(this.mesage);
		return topico;
	}
}
