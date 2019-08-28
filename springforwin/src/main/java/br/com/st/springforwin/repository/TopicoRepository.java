package br.com.st.springforwin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.st.springforwin.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	List<Topico> findByCursoNome(String courseName);
	
	//Another way to implement query methods.
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :courseName")
	List<Topico> carregarPorNomeCurso(@Param("courseName") String courseName);
}
