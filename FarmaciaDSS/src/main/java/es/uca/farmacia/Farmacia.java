package es.uca.farmacia;
import java.util.*;

import javax.persistence.*;
import java.io.*;

@Entity
@Table(name = "FARMACIAS")
public class Farmacia implements Iterable<Medicamento>, Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id = 1; 
	@Transient
	private static final long serialVersionUID = 1110361732316592376L;
	@OneToOne(targetEntity = Medicamentos.class, cascade = {CascadeType.ALL})
	private Medicamentos medicamentos;
	@Transient
	private int numMed;
	@Transient
	private int N  = 1000; 
	
	public Farmacia() 
	{	
		this.numMed = 0;
		this.medicamentos = new Medicamentos(N);
		//this.medList = new ArrayList<Medicamento>(N);
	}
	
	public Medicamentos getMedicamentos() 
	{
		return medicamentos;
	}

	public void setMedicamentos(Medicamentos medicamentos) {
		this.medicamentos = medicamentos;
	}

	public int getNumMed() 
	{
		return numMed;
	}

	public boolean addMed(Medicamento med) 
	{
		assert(this.getNumMed() < N);

		for(Medicamento med_: medicamentos) 
		{
			if(med_.getNombre().equals(med.getNombre())) 
			{	
				//med.setStock(med.getStock()+med_.getStock());
				return false; 
			}
		}

		 //medList.add(med);
		
		++numMed;
		return medicamentos.addMedicamento(med);
	}
	
	public boolean deleteMed(Medicamento med) 
	{
		assert(med != null);
		--numMed;
		return medicamentos.removeMedicamento(med);
	}
	
	public Iterator<Medicamento> iterator()
	{
		return medicamentos.iterator();
	}
}



