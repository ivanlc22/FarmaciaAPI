package es.uca.farmacia.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.uca.farmacia.Cliente;
import es.uca.farmacia.Clientes;


@Repository
public interface ClienteRepositorySpring extends CrudRepository<Clientes, Integer>
{
	
}
