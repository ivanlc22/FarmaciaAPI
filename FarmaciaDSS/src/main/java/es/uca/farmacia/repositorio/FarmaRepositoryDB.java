package es.uca.farmacia.repositorio;

import java.util.Optional;

import es.uca.farmacia.Farmacia;
import es.uca.farmacia.database.FarmaciaRepositorySpring;

public class FarmaRepositoryDB implements FarmaRepository
{

	private FarmaciaRepositorySpring farmarepo;
	
	public FarmaRepositoryDB(FarmaciaRepositorySpring farmarepo) {
		
		this.farmarepo = farmarepo;
	}
	
	@Override
	public Optional<Farmacia> find()
	{
		return farmarepo.findById(1);
	}
	
	@Override
	public Farmacia guardar(Farmacia farma)
	{
		farmarepo.save(farma);
		return farma;
	}
	
	@Override
	public Farmacia cargar(Farmacia farma) 
	{
		return farma;
	}

	
}

