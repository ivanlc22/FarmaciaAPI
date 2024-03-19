package es.uca.farmacia;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AlmacenamientoTXT implements Almacenamiento 
{
	
	public Farmacia guardarFarmacia(Farmacia farma) throws IOException, ClassNotFoundException 
	{
		ObjectOutputStream datosFarma = new ObjectOutputStream(new FileOutputStream("DataBaseFarma.txt"));
		datosFarma.writeObject(farma);
		datosFarma.close();
		
		return farma;
	}
	
	public Farmacia cargarFarmacia(Farmacia farma) throws IOException, ClassNotFoundException 
	{
		//Farmacia farma = new Farmacia();
		ObjectInputStream cargaFarma = new ObjectInputStream(new FileInputStream("DataBaseFarma.txt"));
		farma = (Farmacia) cargaFarma.readObject();
		cargaFarma.close();
		return farma;
	}
	
	public Clientes guardarCliente(Clientes clientes) throws IOException, ClassNotFoundException 
	{
		ObjectOutputStream datosCompra = new ObjectOutputStream(new FileOutputStream("DataBaseClientes2.txt"));
		datosCompra.writeObject(clientes);
		datosCompra.close();
		
		return clientes;
	}
		
	public Clientes cargarCliente(Clientes clientes) throws IOException, ClassNotFoundException 
	{
		ObjectInputStream cargaCompra = new ObjectInputStream(new FileInputStream("DataBaseClientes2.txt"));
		clientes = (Clientes) cargaCompra.readObject();
		cargaCompra.close();
		
		return clientes;
	}
}