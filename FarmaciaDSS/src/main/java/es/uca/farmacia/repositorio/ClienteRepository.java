package es.uca.farmacia.repositorio;

import java.io.IOException;
import java.util.Optional;

import es.uca.farmacia.Cliente;
import es.uca.farmacia.Clientes;

public interface ClienteRepository 
{
    public Clientes cargar(Clientes Clientes);
    public Clientes guardar(Clientes Clientes);
    public Optional<Cliente> findById(String id) throws ClassNotFoundException, IOException;
    public Optional<Clientes> findAll() throws ClassNotFoundException, IOException;
}
