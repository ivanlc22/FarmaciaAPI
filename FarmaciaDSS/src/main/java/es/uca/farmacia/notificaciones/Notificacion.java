package es.uca.farmacia.notificaciones;

import es.uca.farmacia.Cliente;
import es.uca.farmacia.Clientes;
import es.uca.farmacia.Medicamento;

public class Notificacion 
{
	public static void notificar(Clientes clientes, Medicamento med)
	{
		for(Cliente cliente: clientes.getClientes())
		{
			boolean encontrado = false;
            while(cliente.getMedicamentosNotificar().iterator().hasNext() && !encontrado)
            {
            	Medicamento m = cliente.getMedicamentosNotificar().iterator().next();
            	if(m == med)
            	{
                    encontrado = true;
                    cliente.getMedicamentosNotificar().removeMedicamento(med);
                    JavaMail.sendEmail(cliente.getCorreo(), "Notificacion FarmaciaDSS" , med.getNombre() + " disponible en la Farmacia!");
            	}
            } 
		}         
    }
}
