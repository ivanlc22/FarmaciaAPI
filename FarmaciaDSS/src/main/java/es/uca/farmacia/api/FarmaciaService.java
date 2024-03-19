package es.uca.farmacia.api;

import java.io.IOException;
import java.util.Optional;

import org.json.simple.JSONArray;

import es.uca.farmacia.Cliente;
import es.uca.farmacia.Clientes;
import es.uca.farmacia.Medicamento;
import es.uca.farmacia.MedicamentoTarjeta;
import es.uca.farmacia.Medicamentos;

public interface FarmaciaService 
{	
	// Farmacia
	public Medicamentos listarTodo();
	public Optional<Medicamento> listarporNombre(String nombre);
	public Optional<Medicamentos> listarporCategoria(String categoria) throws ClassNotFoundException, IOException;
	public Optional<Medicamentos> listarporPA(String PA) throws ClassNotFoundException, IOException;
    public void addMedicamento(Medicamento med) throws ClassNotFoundException, IOException;
    public void deleteMedicamento(String med) throws ClassNotFoundException, IOException;
	public void addStock(String nombre, int stock);
    // Compra
	public Medicamentos listarCarrito(String idUsuario) throws ClassNotFoundException, IOException;
	public void finalizarCompra(String idUsuario) throws ClassNotFoundException, IOException;
	public void addCarrito(String idUsuario, String med) throws ClassNotFoundException, IOException;
	public void deleteCarrito(String idUsuario, String med) throws ClassNotFoundException, IOException; 
	public Optional<Medicamento> buscarMedCarrito(String medNombre, String idUsuario) throws ClassNotFoundException, IOException;
	// Cliente
	public Optional<Cliente> listarCliente(String idUsuario) throws ClassNotFoundException, IOException;
	public Optional<Clientes> listarClientes() throws ClassNotFoundException, IOException;
	public void addCliente(String correo) throws ClassNotFoundException, IOException;
	public void addCliente(Cliente cliente) throws ClassNotFoundException, IOException;
	public void deleteCliente(String idUsuario) throws ClassNotFoundException, IOException;
	// Prescripciones
	public JSONArray consultarTarjeta(String tarjeta);
	public void addMedTarjeta(MedicamentoTarjeta medtarjeta);
	public void dispensarMedTarjeta(String codMed, String tarjeta);
	
}