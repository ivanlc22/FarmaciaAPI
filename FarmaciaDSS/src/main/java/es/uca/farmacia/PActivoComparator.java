package es.uca.farmacia;

public class PActivoComparator implements java.util.Comparator<Medicamento>, MedicamentoComparator
{
	@Override
	public int compare(Medicamento med1, Medicamento med2) 
	{
	    return med1.getComposicion().compareTo(med2.getComposicion());
	}

	public String get(Medicamento med1)
	{
		return med1.getComposicion();
	}
}

