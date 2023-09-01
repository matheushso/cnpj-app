package br.com.cnpj.api.repository;

import br.com.cnpj.api.model.entity.CNPJ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CNPJRepository extends JpaRepository<CNPJ, Long> {

}
