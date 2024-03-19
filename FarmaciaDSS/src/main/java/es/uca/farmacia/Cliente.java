package es.uca.farmacia;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import lombok.Setter;

@Setter
@Entity
@Table(name = "CLIENTES")
public class Cliente implements Serializable
{
	@Id
	@JoinColumn(name="idUsuario")
	private String idUsuario;
	private String correo;
	@OneToOne(targetEntity = Compra.class, cascade = {CascadeType.MERGE})
	private Compra compra;
	@Transient
	private static final long serialVersionUID = 9017681237479717727L;
	@OneToOne(targetEntity = Medicamentos.class, cascade = {CascadeType.ALL})
	private Medicamentos medicamentosNotificar; 

	public Cliente(String correo) 
	{
		this.idUsuario = UUID.randomUUID().toString();
		this.correo = correo;	
		this.compra = new Compra();
		this.medicamentosNotificar = new Medicamentos(100);
	}
	
	public Medicamentos getMedicamentosNotificar() {
		return medicamentosNotificar;
	}

	public void setMedicamentosNotificar(Medicamentos medicamentosNotificar) {
		this.medicamentosNotificar = medicamentosNotificar;
	}

	public Cliente() {}
	
	public String getIdUsuario()
	{
		return idUsuario;
	}
	
	public void setIdUsuario(String id) 
	{
		this.idUsuario = id;
	}
	
	public String getCorreo() 
	{
		return correo;
	}

	public void setCorreo(String correo) 
	{
		this.correo = correo;
	}

	public Compra getCompra() 
	{
		return compra;
	}
	
	public void setCompra(Compra compra) 
	{
		this.compra = compra;
	}
}
