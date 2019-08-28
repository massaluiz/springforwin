package br.com.st.springforwin.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.st.springforwin.modelo.Curso;
import br.com.st.springforwin.modelo.Topico;
import br.com.st.springforwin.repository.CursoRepository;

public class TopicoForm {

	@NotNull
	@NotEmpty
	@Length(min=5, max=100)
	private String title;
	@NotNull
	@NotEmpty
	private String mesage;
	@NotNull
	@NotEmpty
	private String courseName;
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Topico convert(TopicoForm topicoForm, CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(topicoForm.courseName);
		return new Topico(topicoForm.title, topicoForm.mesage, curso);
	}
}
