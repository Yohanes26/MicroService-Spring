package br.com.exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.exemplo.models.Exemplo;

public interface ExemploRepository extends JpaRepository<Exemplo, String>{

	Exemplo findByCodigo(long codigo);
}
