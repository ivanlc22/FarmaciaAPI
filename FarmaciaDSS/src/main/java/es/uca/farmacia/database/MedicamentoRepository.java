package es.uca.farmacia.database;

import org.springframework.data.repository.CrudRepository;

import es.uca.farmacia.Medicamento;

public interface MedicamentoRepository extends CrudRepository<Medicamento, int[]>
{
	
}

