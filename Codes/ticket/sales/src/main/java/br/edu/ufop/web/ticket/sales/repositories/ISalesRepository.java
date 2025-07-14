package br.edu.ufop.web.ticket.sales.repositories;

import br.edu.ufop.web.ticket.sales.models.SalesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ISalesRepository extends JpaRepository<SalesModel, UUID> {
}
