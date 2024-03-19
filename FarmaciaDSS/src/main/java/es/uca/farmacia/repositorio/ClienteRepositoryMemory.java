package es.uca.farmacia.repositorio;

import java.io.IOException;
import java.util.Optional;

import es.uca.farmacia.Almacenamiento;
import es.uca.farmacia.AlmacenamientoTXT;
import es.uca.farmacia.Cliente;
import es.uca.farmacia.Clientes;


public class ClienteRepositoryMemory implements ClienteRepository 
{
	private Almacenamiento data;
	private Clientes clientes;
	
	public ClienteRepositoryMemory() throws ClassNotFoundException, IOException 
	{
		this.clientes = new Clientes();
		this.data = new AlmacenamientoTXT();
		this.clientes = data.cargarCliente(clientes);
	}

	 public Optional<Clientes> findAll() throws ClassNotFoundException, IOException
	 {
		 //clientes = data.cargarCliente(clientes);
		 return Optional.of(clientes);
	 }
	
	@Override
	public Clientes cargar(Clientes clientes)
	{	
		try 
		{		
			clientes = data.cargarCliente(clientes);
			this.clientes = clientes; 
			return clientes;
		}
		catch(ClassNotFoundException | IOException exception)
		{
			System.out.println(exception); 
			return null;
		}
	}

	@Override
	public Clientes guardar(Clientes clientes)
	{	
		try 
		{
			clientes = data.guardarCliente(clientes);
			this.clientes = clientes; 
			return clientes;
		}
		catch(ClassNotFoundException | IOException exception)
		{
			System.out.println(exception); 
			return null;
		}
	}
	
	@Override
	public Optional<Cliente> findById(String id) throws ClassNotFoundException, IOException 
	{
		//clientes = data.cargarCliente(clientes);
		for(Cliente cli: clientes.getClientes())
	    {
			if(cli.getIdUsuario().equals(id))
	        {
	        	return Optional.of(cli); 
	        }
	     }
		return Optional.empty();
	}
	
}
