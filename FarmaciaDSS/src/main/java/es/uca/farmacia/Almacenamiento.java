package es.uca.farmacia;
import java.io.IOException;

public interface Almacenamiento {
	public Farmacia guardarFarmacia(Farmacia farma) throws IOException, ClassNotFoundException;
	public Farmacia cargarFarmacia(Farmacia farma) throws IOException, ClassNotFoundException;
	public Clientes guardarCliente(Clientes clientes) throws IOException, ClassNotFoundException;
	public Clientes cargarCliente(Clientes clientes) throws IOException, ClassNotFoundException;
}
