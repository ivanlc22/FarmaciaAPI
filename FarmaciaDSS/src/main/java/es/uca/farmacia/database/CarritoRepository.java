package es.uca.farmacia.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.uca.farmacia.Compra;

@Repository
public interface CarritoRepository extends CrudRepository<Compra, Integer>
{
	
}