package es.uca.farmacia;

import java.util.Optional;

public class BuscarMed 
{	
	private static boolean parecidos(String a, String b)
	{
		return (a.contains(b));
	}

	public static Optional<Medicamentos> buscarporcategoria(String categoria, Medicamentos meds)
	{
		CategoriaComparator comparator = new CategoriaComparator();
		Medicamento medAux = new Medicamento();
		medAux.setCategoria(categoria);
		Optional<Medicamentos> medsAuxOptional;
		
		medsAuxOptional = busqueda(medAux, meds, comparator);
		
		//return medsAux;
		if(medsAuxOptional.isPresent())
			return medsAuxOptional;
		else
			return Optional.empty();
	}
	
	public static Optional<Medicamentos> buscarporpactivo(String pActivo, Medicamentos meds)
	{
		PActivoComparator comparator = new PActivoComparator();	
		Medicamento medAux = new Medicamento();
		medAux.setComposicion(pActivo);
		Optional<Medicamentos> medsAuxOptional;
		
		
		medsAuxOptional = busqueda(medAux, meds, comparator);
		
		
		if(medsAuxOptional.isPresent())
			return medsAuxOptional;
		else
			return Optional.empty();
	}
	
	public static Optional<Medicamento> buscarpornombre(String nombre, Medicamentos meds)
	{
		NombreComparator comparator = new NombreComparator();	
		Medicamento medAux = new Medicamento();
		medAux.setNombre(nombre);
		Optional<Medicamentos> medsAuxOptional;
		
		medsAuxOptional = busqueda(medAux, meds, comparator); 	// Devuelve igual o parecido 
		
		if(medsAuxOptional.isPresent())
			return Optional.of(medsAuxOptional.get().iterator().next());
		else return Optional.empty();
	}

	public static Optional<Medicamentos> busqueda(Medicamento medAux, Medicamentos meds, MedicamentoComparator comparator)
	{
		//int nMed = 0;
		Optional<Medicamentos> res = Optional.of(new Medicamentos(100));
		Optional<Medicamentos> resParecidos = Optional.of(new Medicamentos(100));

		boolean flag = false;
		boolean notempty = false;
		
		for(Medicamento med: meds) 
		{
			if(comparator.compare(med, medAux) == 0) 
			{	
				res.get().addMedicamento(med);
				//nMed++;
				flag = true;
				notempty = true;
			}
			else
			{
				if(!flag && parecidos(comparator.get(med), comparator.get(medAux)))
				{
					res.get().addMedicamento(med);
					//nMed++;
					flag = true;
					notempty = true;
				}
				else if(!flag)
				{
					if(comparator.get(med).length() == comparator.get(medAux).length())	// Buscar parecidos.
					{
						int sim = 0;
						for(int i=0; i<(comparator.get(medAux).length()); i++) 
						{
							if(comparator.get(med).charAt(i) == comparator.get(medAux).charAt(i))
							{
								sim++;	
							}
						}
						
						if(sim >= comparator.get(medAux).length() - 2)	// POSIBLE CANDIDATO 	
						{
							resParecidos.get().addMedicamento(med);
							//nMed++;
							notempty = true;
						}
					}
				}
			}
		}
		
		if(flag) 
		{
			if(notempty==true)
			{
				return res;
			}
			else
			{
				return Optional.empty();
			}
		}
		else 
		{
			if(notempty==true)
			{
				return resParecidos;
			}
			else
			{
				return Optional.empty();
			}
		}	
	}
}
