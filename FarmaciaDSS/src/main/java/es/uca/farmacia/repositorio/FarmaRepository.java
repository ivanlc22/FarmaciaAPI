package es.uca.farmacia.repositorio;

import java.util.Optional;

import es.uca.farmacia.Farmacia;

public interface FarmaRepository 
{
	//public Optional<Farmacia> findById(int i);
	public Optional<Farmacia> find(); 
    public Farmacia cargar(Farmacia farma);
	public Farmacia guardar(Farmacia farma);
}
