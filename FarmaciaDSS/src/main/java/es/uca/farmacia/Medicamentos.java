package es.uca.farmacia;
import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.*;

@Entity
@Table(name = "MEDICAMENTOS_LISTA")
public class Medicamentos implements Iterable<Medicamento>, Serializable
{
	@Id
	private final int id;
	private static int counter = 0;
	@Transient
	private static final long serialVersionUID = 8894558811563520038L;
	@JsonIgnore
	private transient int numMeds = 0;
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Medicamento.class, cascade = {CascadeType.ALL})
	private List<Medicamento> medicamentos;
	
	public Medicamentos(int numero) 
	{	
		this.id = counter++;
		medicamentos = new ArrayList<Medicamento>(numero);
		numMeds = 0;
	}
	
	public Medicamentos() 
	{
		this.id = counter++;
	}
	
	public Iterator<Medicamento> iterator()
	{	
		return medicamentos.iterator();
	}
	
	public boolean addMedicamento(Medicamento m) 
	{	
		numMeds++;
		return medicamentos.add(m);
	}
	
	public boolean removeMedicamento(Medicamento m) 
	{	
		numMeds--;
		return medicamentos.remove(m);
	}
	
	public void removeAll()
	{
		Iterator<Medicamento> it = medicamentos.iterator(); 
		@SuppressWarnings("unused")
		Medicamento m;
		while(it.hasNext())
		{
			m = it.next();
			it.remove();
		}
		numMeds = 0;
	}
	
	public Medicamento getMedicamento(int pos)
	{
		Iterator<Medicamento> it = medicamentos.iterator();
		Medicamento m = new Medicamento();
		int i = 0;
		while(it.hasNext() && i<=pos)
		{
			m = it.next();
			i++;
		}
		
		return m;
	}

	public int getNumMeds() { return numMeds; }
	public List<Medicamento> getMedicamentos() { return medicamentos; }
}



