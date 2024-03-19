package es.uca.farmacia.api;

import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uca.farmacia.BuscarMed;
import es.uca.farmacia.Cliente;
import es.uca.farmacia.Clientes;
import es.uca.farmacia.Farmacia;
import es.uca.farmacia.Medicamento;
import es.uca.farmacia.MedicamentoTarjeta;
import es.uca.farmacia.Medicamentos;
import es.uca.farmacia.UsePrescripcionesAPI;
import es.uca.farmacia.enumFormas;
import es.uca.farmacia.notificaciones.Notificacion;
import es.uca.farmacia.repositorio.ClienteRepository;
import es.uca.farmacia.repositorio.FarmaRepository;

@Service
@Transactional
public class FarmaciaServiceImpl implements FarmaciaService
{	
	public FarmaRepository repo;
	public ClienteRepository clienterepo;
	
	@Autowired
    public FarmaciaServiceImpl(FarmaRepository repo, ClienteRepository clienterepo) throws ClassNotFoundException, IOException
    {	
		this.repo = repo;
		this.clienterepo = clienterepo;		
    }
	
	@Override
    public Medicamentos listarTodo()
    {
		if(repo.find().isEmpty())
		{
			return null;
		}
		else
		{
			return repo.find().get().getMedicamentos();
		}
    }

	@Override
	public Optional<Medicamento> listarporNombre(String nombre)
	{
		try
		{
			return BuscarMed.buscarpornombre(nombre, repo.find().get().getMedicamentos());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<Medicamentos> listarporCategoria(String categoria) throws ClassNotFoundException, IOException 
	{
		return BuscarMed.buscarporcategoria(categoria,  repo.find().get().getMedicamentos());
	}
	
	@Override
	public Optional<Medicamentos> listarporPA(String PA) throws ClassNotFoundException, IOException 
	{
		return BuscarMed.buscarporpactivo(PA, repo.find().get().getMedicamentos());
	}
	
	@Override
	public void addMedicamento(Medicamento med) throws ClassNotFoundException, IOException 
	{
		repo.find().get().addMed(med);
		repo.guardar(repo.find().get());
	}
	
	@Override
	public void deleteMedicamento(String medNombre) throws ClassNotFoundException, IOException 
	{
		Medicamento med = BuscarMed.buscarpornombre(medNombre, repo.find().get().getMedicamentos()).get();
		repo.find().get().deleteMed(med);
		repo.guardar(repo.find().get());
	}

	@Override
	public void addStock(String medNombre, int stock)
	{
		try
		{
			assert(stock>0);
			Medicamento med = BuscarMed.buscarpornombre(medNombre, repo.find().get().getMedicamentos()).get();
			med.setStock(med.getStock() + stock);
			
			// Notificar a los usuarios interesados.
			Notificacion.notificar(clienterepo.findAll().get(), med);
			
			repo.guardar(repo.find().get());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	// Carrito de cada cliente.

	@Override
    public Medicamentos listarCarrito(String idUsuario) throws ClassNotFoundException, IOException
    {
		return clienterepo.findById(idUsuario).get().getCompra().getCarrito();
    }

	@Override
	public void finalizarCompra(String idUsuario) throws ClassNotFoundException, IOException
	{
		clienterepo.findById(idUsuario).get().getCompra().finCompra();
		clienterepo.guardar(clienterepo.findAll().get());}
	
	@Override
	public void addCarrito(String idUsuario, String medNombre) throws ClassNotFoundException, IOException 
	{
		Medicamento med = BuscarMed.buscarpornombre(medNombre, repo.find().get().getMedicamentos()).get();

		if(med.getStock() != 0)
		{
			// Se introduce en el carrito.
			clienterepo.findById(idUsuario).get().getCompra().meterCarrito(med);
			clienterepo.guardar(clienterepo.findAll().get());
		}
		else
		{
			// Se introduce el medicamento en la lista de noficacion del cliente. 
			boolean exists = false;
			for(Medicamento m: clienterepo.findById(idUsuario).get().getMedicamentosNotificar())
			{
				if(m.getNombre().equals(med.getNombre()))
				{
					exists = true;
				}
			}

			if(!exists)
			{
				clienterepo.findById(idUsuario).get().getMedicamentosNotificar().addMedicamento(med);
			}
				
		}
	}
	
	@Override
	public void deleteCarrito(String idUsuario, String medNombre) throws ClassNotFoundException, IOException 
	{
		Medicamento med = BuscarMed.buscarpornombre(medNombre, clienterepo.findById(idUsuario).get().getCompra().getCarrito()).get();
		clienterepo.findById(idUsuario).get().getCompra().sacarCarrito(med);
		//Medicamento med_aux = BuscarMed.buscarpornombre(medNombre, repo.find().get().getMedicamentos()).get();
		clienterepo.guardar(clienterepo.findAll().get());
	}
	
	@Override
	public Optional<Medicamento> buscarMedCarrito(String medNombre, String idUsuario) throws ClassNotFoundException, IOException
	{
		return BuscarMed.buscarpornombre(medNombre, clienterepo.findById(idUsuario).get().getCompra().getCarrito());	
	}
	
	// Cliente

	@Override
	public Optional<Cliente> listarCliente(String idUsuario) throws ClassNotFoundException, IOException
	{
		return clienterepo.findById(idUsuario);
	}

	@Override
    public Optional<Clientes> listarClientes() throws ClassNotFoundException, IOException
    {
		return clienterepo.findAll();
    }
	
	@Override
	public void addCliente(String correo) throws ClassNotFoundException, IOException
	{
		clienterepo.findAll().get().addCliente(correo);
		clienterepo.guardar(clienterepo.findAll().get());
	}

	@Override
	public void addCliente(Cliente cliente) throws ClassNotFoundException, IOException
	{
		clienterepo.findAll().get().addCliente(cliente);
		clienterepo.guardar(clienterepo.findAll().get());
	}
	
	@Override
	public void deleteCliente(String idUsuario) throws ClassNotFoundException, IOException
	{
		Optional<Cliente> cliente = clienterepo.findById(idUsuario); 
		if(cliente.isPresent())
		{
			clienterepo.findAll().get().deleteCliente(cliente.get());
			clienterepo.guardar(clienterepo.findAll().get());
		}
	}

	// PRESCRIPCIONES

	@Override
	public JSONArray consultarTarjeta(String tarjeta)
	{
		return UsePrescripcionesAPI.prescripcionGet(tarjeta);
	}
	
	@Override
	public void addMedTarjeta(MedicamentoTarjeta medtarjeta)
	{
		UsePrescripcionesAPI.prescripcionPost(medtarjeta);
	}

	@Override
	public void dispensarMedTarjeta(String codMed, String tarjeta)
	{
		UsePrescripcionesAPI.prescripcionPatch(codMed, tarjeta);
	}

}
