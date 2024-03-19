package es.uca.farmacia.repositorio;

import java.io.IOException;
import java.util.Optional;

import es.uca.farmacia.Almacenamiento;
import es.uca.farmacia.AlmacenamientoTXT;
import es.uca.farmacia.Farmacia;

public class FarmaRepositoryMemory implements FarmaRepository
{
	private Almacenamiento data;
	private Farmacia farmacia;
	
	public FarmaRepositoryMemory() throws ClassNotFoundException, IOException 
	{
		this.farmacia = new Farmacia();
		this.data = new AlmacenamientoTXT();
		this.farmacia = data.cargarFarmacia(farmacia);
	}
	
	public Optional<Farmacia> find()
	{		
		return Optional.of(farmacia); 
	}

    public Farmacia cargar(Farmacia farma)
    {
		try
		{
			farma = data.cargarFarmacia(farma);	
			this.farmacia = farma;
			return farma;
		}
		catch(ClassNotFoundException | IOException exception)
		{
			System.out.println(exception); 
			return null;
		}
    }
	
	public Farmacia guardar(Farmacia farma)
	{	
		try 
		{
			farma = data.guardarFarmacia(farma);
			this.farmacia = farma;
			return farma;
		}
		catch(ClassNotFoundException | IOException exception)
		{
			System.out.println(exception); 
			return null;
		}
	}
}