package es.uca.farmacia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="CLIENTES_LISTA")
public class Clientes implements Serializable
{
	@Transient
	private static final long serialVersionUID = 4865568332813799960L;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Cliente.class, cascade = {CascadeType.MERGE})
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "clientes", cascade = {CascadeType.ALL})
	private List<Cliente> clientes;
	@JoinColumn(name="ID_CLIENTES")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id=1;
	
	public Clientes() 
	{
		this.clientes = new ArrayList<Cliente>(1000);
	}
	
	public List<Cliente> getClientes() 
	{
		return clientes;
	}
	
	public boolean addCliente(String correo) 
	{
		Cliente cli = new Cliente(correo);
		return clientes.add(cli);
	}

	public boolean addCliente(Cliente cliente)
	{
		return clientes.add(cliente);
	}
	
	public boolean deleteCliente(Cliente cliente)
	{
		return clientes.remove(cliente);
	}
	
	public Cliente getCliente(Cliente cliente) 
	{
		for(Cliente cli: clientes) 
		{
			if(cli.getIdUsuario() == cliente.getIdUsuario()) 
			{
				return cli;
			}
		}
		
		return null;
	}
	
	public int getId() 
	{
		return id;
	}
	
}