package es.uca.farmacia;

public class NombreComparator implements java.util.Comparator<Medicamento>, MedicamentoComparator
{
	@Override
	public int compare(Medicamento med1, Medicamento med2) 
	{
		return med1.getNombre().compareTo(med2.getNombre());
	}
	
	public String get(Medicamento med1)
	{
		return med1.getNombre();
	}
}
