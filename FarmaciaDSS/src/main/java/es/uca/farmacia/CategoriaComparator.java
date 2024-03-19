package es.uca.farmacia;

public class CategoriaComparator implements java.util.Comparator<Medicamento>, MedicamentoComparator  
{
	@Override
	public int compare(Medicamento med1, Medicamento med2) 
	{
		return med1.getCategoria().compareTo(med2.getCategoria());
	}

	public String get(Medicamento med1)
	{
		return med1.getCategoria();
	}
}
