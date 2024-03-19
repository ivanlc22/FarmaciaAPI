package es.uca.farmacia.repositorio;

import java.io.IOException;
import java.util.Optional;

import es.uca.farmacia.Cliente;
import es.uca.farmacia.Clientes;
import es.uca.farmacia.database.ClienteRepositorySpring;


public class ClienteRepositoryDB implements ClienteRepository 
{
	private ClienteRepositorySpring clienterepo;
	
	public ClienteRepositoryDB(ClienteRepositorySpring clienterepo) {
		this.clienterepo = clienterepo;
	}
	
	public Optional<Clientes> findAll() throws ClassNotFoundException, IOException
	{
		return clienterepo.findById(1);
	}
	
	@Override
	public Clientes cargar(Clientes clientes)
	{
		return clientes;
	}

	@Override
	public Clientes guardar(Clientes clientes)
	{
		clienterepo.save(clientes);
		return clientes;
	}
	
	@Override
	public Optional<Cliente> findById(String id) 
	{
		//Cliente cli = new Cliente("xxx");
		//cli.setIdUsuario(id);
        Optional<Clientes> clientes = clienterepo.findById(1);

        for(Cliente cli: clientes.get().getClientes())
        {
           if(cli.getIdUsuario().equals(id))
           {
        	  return Optional.of(cli); 
           }
        }
        
        return Optional.empty(); 
		//return Optional.of(clienterepo.findById(1).get().getCliente(cli));
	}
}

