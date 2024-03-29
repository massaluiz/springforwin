package br.com.st.springforwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.st.springforwin.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
	Curso findByNome(String courseName);
}
