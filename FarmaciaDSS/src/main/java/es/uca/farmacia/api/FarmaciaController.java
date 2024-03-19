package es.uca.farmacia.api;

import java.io.IOException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.uca.farmacia.Cliente;
import es.uca.farmacia.Clientes;
import es.uca.farmacia.Medicamento;
import es.uca.farmacia.MedicamentoTarjeta;
import es.uca.farmacia.Medicamentos;

@RestController
public class FarmaciaController
{
    @Autowired
    private FarmaciaService service;
        
    @GetMapping("/medicamentos")
    public Medicamentos listarTodo() throws ClassNotFoundException, IOException
    {
		  return service.listarTodo();   
    }

    @GetMapping("/medicamentos-por-nombre/{nombre}")
    public Optional<Medicamento> buscarporNombre(@PathVariable String nombre) throws ClassNotFoundException, IOException
    {
        return service.listarporNombre(nombre);
    }

    @GetMapping("/medicamentos-por-categoria/{categoria}")
    public Optional<Medicamentos> buscarporCategoria(@PathVariable String categoria) throws ClassNotFoundException, IOException
    {
        return service.listarporCategoria(categoria);
    }

    @GetMapping("/medicamentos-por-pactivo/{pActivo}")
    public Optional<Medicamentos> buscarporPA(@PathVariable String pActivo) throws ClassNotFoundException, IOException
    {
        return service.listarporPA(pActivo);
    }
    
    @PostMapping("/medicamentos/{nombre}/stock/{cantidad}")
    public String addStock(@PathVariable String nombre, @PathVariable int cantidad) throws ClassNotFoundException, IOException, AddressException, MessagingException
    {
    	service.addStock(nombre, cantidad);
    	String response = "El medicamento " + nombre + " ha incrementado su stock en " + cantidad + " unidades.";
    	return response;
    }
    
    // MODIFICAR MEDICAMENTOS
    
    @PostMapping("/medicamentos")
    public String addMedicamento(@RequestBody Medicamento med) throws ClassNotFoundException, IOException 
    {
    	service.addMedicamento(med);
    	String response = "El medicamento " + med.getNombre() + " ha sido anadido con exito.";
    	return response;
    }

    @DeleteMapping("/medicamentos/{med}")
    public String deleteMedicamento(@PathVariable(value = "med") String medNombre) throws ClassNotFoundException, IOException 
    {
    	//Medicamento med = service.listarporNombre(medNombre);	
    	service.deleteMedicamento(medNombre); 	
    	String response = "El medicamento " + medNombre + " ha sido eliminado con exito.";
    	return response;
    }
      
    // MODIFICAR COMPRA

    @GetMapping("/clientes/{idUsuario}/carrito")
    public Medicamentos listarCarrito(@PathVariable(value = "idUsuario") String idUsuario) throws ClassNotFoundException, IOException
    {
		  return service.listarCarrito(idUsuario);   
    }
    
    @PostMapping("/clientes/{idUsuario}/{med}")
    public String addCarrito(@PathVariable(value = "idUsuario") String idUsuario, @PathVariable(value = "med") String medNombre) throws ClassNotFoundException, IOException 
    {
    	service.addCarrito(idUsuario, medNombre);
    	String response = "El medicamento " + medNombre + " ha sido anadido con exito al carrito.";
    	return response;
    }

    @DeleteMapping("/clientes/{idUsuario}/{med}")
    public String deleteCarrito(@PathVariable(value = "idUsuario") String idUsuario, @PathVariable(value = "med") String medNombre) throws ClassNotFoundException, IOException 
    {
    	service.deleteCarrito(idUsuario, medNombre); 	
    	String response = "El medicamento " + medNombre + " ha sido eliminado con exito del carrito.";
    	return response;
    }
    
    @GetMapping("/clientes/{idUsuario}/Comprar")
    public String finalizarCompra(@PathVariable(value = "idUsuario") String idUsuario) throws ClassNotFoundException, IOException
    {
      service.finalizarCompra(idUsuario);
      String response = "Compra realizada. Se ha generado el ticket con exito.";
      return response;
    }

    // GESTIONAR CLIENTES

    @GetMapping("/clientes/{idUsuario}")
    public Optional<Cliente> listarCliente(@PathVariable(value = "idUsuario") String idUsuario) throws ClassNotFoundException, IOException
    {
      return service.listarCliente(idUsuario);
    }
    
    @GetMapping("/clientes")
    public Optional<Clientes> listarClientes() throws ClassNotFoundException, IOException
    {
      return service.listarClientes();
    }
    
    @PostMapping("/clientes/{correo}")
    public String addCliente(@PathVariable(value = "correo") String correo) throws ClassNotFoundException, IOException 
    {
    	service.addCliente(correo);
    	String response = "El cliente con correo " + correo + " se ha registrado con exito";
    	return response;
    }
    
    @DeleteMapping("/clientes/{idUsuario}")
    public String deleteCliente(@PathVariable(value = "idUsuario") String idUsuario) throws ClassNotFoundException, IOException 
    {
    	service.deleteCliente(idUsuario); 	
    	String response = "El cliente " + idUsuario + " ha sido eliminado con exito.";
    	return response;
    }

    // GESTIONAR TARJETAS
    
    @GetMapping("/tarjetas/{tarjeta}")
    public JSONArray consultarTarjeta(@PathVariable(value = "tarjeta") String tarjeta)
    {
      return service.consultarTarjeta(tarjeta);
    }
    
    @PostMapping("/tarjetas")
    public String addMedTarjeta(@RequestBody MedicamentoTarjeta medtarjeta) throws ClassNotFoundException, IOException 
    {
    	service.addMedTarjeta(medtarjeta);
    	String response = "El medicamento con código " + medtarjeta.getCodMed() + " ha sido anadido con exito.";
    	return response;
    }
    
    @PatchMapping("/tarjetas/{tarjeta}/{codMed}")
    public String dispensarMedTarjeta(@PathVariable(value = "tarjeta") String tarjeta, @PathVariable(value = "codMed") String codMed)
    {
    	service.dispensarMedTarjeta(codMed, tarjeta); 
    	String response = "El medicamento con código " + codMed + " ha sido dispensado con exito.";
		return response;
    }
}
