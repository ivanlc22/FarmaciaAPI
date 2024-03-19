package es.uca.farmacia;
import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "COMPRAS")
public class Compra implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final int id;
	private static int counter = 0;
	@Transient
	private static final long serialVersionUID = -243992666817482405L;
	@OneToOne(targetEntity = Medicamentos.class, cascade = {CascadeType.ALL})
	private Medicamentos carrito;
	private int numElem;
	@Transient
	private int N = 100;
	private int numTickets;
	
	public Compra()
	{
		this.id = counter++;
		this.numElem = 0;
		this.numTickets = 0;
		carrito = new Medicamentos(N);
	}
	
	public int getNumElem() {
		return numElem;
	}

	public Medicamentos getCarrito() {
		return carrito;
	}

	public void setCarrito(Medicamentos carrito) {
		this.carrito = carrito;
	}

	public void meterCarrito(Medicamento med)
	{
		assert(numElem < N);
		if((med.getStock() > 0))
		{
			med.setStock(med.getStock()-1);
			++numElem;
			carrito.addMedicamento(med);
			System.out.println("Se ha introducido correctamente el Medicamento " + med.getNombre() + ".\n");
		}
		else
		{
			System.out.println("No hay stock del medicamento '" + med.getNombre() + "' disponible.\n");
			System.out.println("Se le notificará cuando esté disponible.\n");
		}
	}
	
	public void sacarCarrito(Medicamento med)
	{
		assert(numElem > 0);
		med.setStock(med.getStock()+1);
		--numElem;
		carrito.removeMedicamento(med);
		System.out.println("Se ha eliminado correctamente el Medicamento " + med.getNombre() + ".\n");
	}
	
	public void finCompra()
	{
		if(numElem > 0)
		{
			System.out.println("Se han comprado los siguientes medicamentos: ");
			System.out.println(this.toString()); 
			System.out.println(" Precio total de la compra = " + getPrice() + "e.\n");
			GeneratePDF.generar(carrito, numTickets);
			++numTickets;
			
			numElem = 0;
			carrito.removeAll();	
		}
		else System.out.println("No se ha podido realizar la compra porque el carrito esta vacio.\n");
	}
	
	public int getPrice()
	{
		int resultado = carrito.getMedicamentos().stream()
				 		 	   .map(Medicamento::getPrecio)
				 		       .reduce(0, (x,y) -> x + y);
		return resultado;
	}
	
	@Override
	public String toString()
	{
		String resultado = "";
		int i = 0;
		
		for(Medicamento med: carrito)
		{
			resultado = resultado + (i+1 + ". " + med.getNombre() + "............................................" + med.getPrecio() + "e.\n");
			i++;
		}
		
		return resultado;
	}
}