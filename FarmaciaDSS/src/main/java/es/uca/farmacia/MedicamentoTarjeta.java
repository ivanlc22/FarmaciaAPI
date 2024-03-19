package es.uca.farmacia;

import java.io.Serializable;

// Tipo de medicamento que usa la API externa Prescripciones. 
public class MedicamentoTarjeta implements Serializable
{
	private static final long serialVersionUID = -3311999526131207474L;
	private String codMed;
	private String codUsuario;
    private int udportoma;
    private int hportoma;
    
	public MedicamentoTarjeta(String codMed, String codUsuario, int udportoma, int hportoma) 
	{
		this.codMed = codMed;
		this.codUsuario = codUsuario;
		this.udportoma = udportoma;
		this.hportoma = hportoma;
	}
	
	public MedicamentoTarjeta() 
	{

	}

	public String getCodMed() {
		return codMed;
	}

	public void setCodMed(String codMed) {
		this.codMed = codMed;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public int getUdportoma() {
		return udportoma;
	}

	public void setUdportoma(int udportoma) {
		this.udportoma = udportoma;
	}

	public int getHportoma() {
		return hportoma;
	}

	public void setHportoma(int hportoma) {
		this.hportoma = hportoma;
	}
}
