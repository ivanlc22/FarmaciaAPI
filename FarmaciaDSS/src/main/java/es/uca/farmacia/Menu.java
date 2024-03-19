package es.uca.farmacia;
import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import es.uca.farmacia.api.FarmaciaService;

@Component
public class Menu implements CommandLineRunner
{		
	@Resource
	private FarmaciaService service;
	
	public Menu(FarmaciaService service) 
	{
		this.service = service;
	}
	
	private static void imprimirMenu(String[] opciones)
	{
		int i = 1;
		System.out.println("-------------------------------------------------------------------");
		for(String opcion : opciones)
		{
			System.out.println(i + ". " + opcion);
			i++;
		}
		
		System.out.println("-------------------------------------------------------------------");
		System.out.print("Introduzca una opcion: ");
	}

	private static void imprimirMenu(Medicamentos medicamentos)
	{
		System.out.println("-------------------------------------------------------------------");
		HashMap<Integer, String> collect = medicamentos.getMedicamentos().stream().map(Medicamento::getNombre)
									   						.collect(HashMap<Integer, String>::new,
									   						(map, streamValue) -> map.put(map.size(), streamValue),
									   						(map, map2) -> {
									   						});
		collect.forEach((i, m) -> System.out.println(i+1 + ". " + m));
		System.out.println("-------------------------------------------------------------------");
		System.out.print("Introduzca una opcion: ");
	}
	
	private static int opcionValida(Scanner scanner)
	{
		boolean flag=false;
		int option=0;
		do
		{
		    try
		    {
		        option = scanner.nextInt();
		        flag=true;
		    }
		    catch (InputMismatchException exception)
		    {
		        System.out.print("Opcion invalida. Introduzca un numero, por favor: ");
		        scanner.next();
		    }
		}
		while (!flag);
		
		return option;
	}
	
	private static boolean preguntarParecido(String nombreOrig, String nombreSim, Scanner scanner)
	{
		String aux;
		System.out.print("¿Quizas quisiste decir '" + nombreOrig + "'? (s/n): ");
		aux = scanner.next();
		aux = aux.toLowerCase();
		System.out.println();
		if(aux.equals("s"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private static boolean preguntarClienteRegistrado(Scanner scanner)
	{
		String aux; 
		boolean fin = false;
		
		do
		{
			System.out.print("¿El cliente esta registrado? (s/n): ");
			aux = scanner.next();
			aux = aux.toLowerCase();
			System.out.println();
			if(aux.equals("s"))
			{
				return true;
			}
			else if(aux.equals("n"))
			{
				return false;
			}
		} while(!fin);
		return false;
	}

	private static void seleccionarMedicamento(String criterio, String valorCriterio, Scanner scanner, Optional<Medicamentos> medicamentos)
	{
		int option;
		System.out.println("\nMedicamentos con " + criterio + " '" + valorCriterio + "'.");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("¿Que medicamento desea imprimir?");
		imprimirMenu(medicamentos.get());
		option = scanner.nextInt();
		System.out.println(medicamentos.get().getMedicamento(option-1).toString());
	}

	private static String solicitarDatoMedicamento(String tipo, Scanner scanner)
	{
		String aux;
		System.out.print("Introduce un " + tipo + " de medicamento: ");
		aux = scanner.next();
		aux = aux.toLowerCase();
		return aux; 
	}
	
	private static void pantallaBuscarMed(Scanner scanner, String[] pantalla2, Optional<Medicamentos> medicamentos, FarmaciaService service) throws ClassNotFoundException, IOException
	{
		Optional<Medicamento> medNombre;
		String aux;
		int option = 0;
		boolean salida = false;
		
		do
		{
			System.out.println("FarmaciaDSS: Consultar medicamento");
			imprimirMenu(pantalla2);
			
			option = opcionValida(scanner);
			
			System.out.println();
			
			switch(option)
			{
				case 1:
					aux = solicitarDatoMedicamento("nombre", scanner);
					medNombre = service.listarporNombre(aux);
					
					if(medNombre.isEmpty())
					{
						System.out.println("No se ha encontrado un medicamento con nombre " + aux + ".");
						System.out.println();
					}
					else if(!medNombre.get().getNombre().equals(aux))
					{
						if(preguntarParecido(medNombre.get().getNombre(), aux, scanner))
						{
							System.out.println(medNombre.get().toString());
						}
					}
					else if(medNombre.get().getNombre().equals(aux))
					{
						System.out.println(medNombre.get().toString());
					}

					break;
					
				case 2:
					aux = solicitarDatoMedicamento("categoria", scanner);
					medicamentos = service.listarporCategoria(aux);			
					if(medicamentos.isEmpty())
					{
						System.out.println("No existen medicamentos con categoria " + aux + ".");
						System.out.println();
					}
					else if(medicamentos.get().getNumMeds() == 1)
					{
						if(!medicamentos.get().getMedicamento(0).getCategoria().equals(aux))
						{
							boolean pregunta = preguntarParecido(medicamentos.get().iterator().next().getCategoria(), aux, scanner);
							if(pregunta)
							{
								System.out.println(medicamentos.get().iterator().next().toString());
							}
						}
						else
						{
							System.out.println(medicamentos.get().getMedicamento(0).toString());
						}
					}
					else
					{
						seleccionarMedicamento("categoria", aux, scanner, medicamentos);
					}
					
					break;
					
				case 3:
					aux = solicitarDatoMedicamento("principio activo", scanner);
					medicamentos = service.listarporPA(aux);
					
					if(medicamentos.isEmpty())
					{
						System.out.println("No existen medicamentos con p.Activo " + aux + ".");
						System.out.println();
					}
					else if(medicamentos.get().getNumMeds() == 1)
					{	
						if(!medicamentos.get().getMedicamento(0).getComposicion().equals(aux))
						{
							boolean pregunta = preguntarParecido(medicamentos.get().iterator().next().getComposicion(), aux, scanner);
							if(pregunta)
							{
								System.out.println(medicamentos.get().iterator().next().toString());
							}
						}
						else	// Medicamento encontrado
						{
							System.out.println(medicamentos.get().getMedicamento(0).toString());
						}
					}
					else
					{
						seleccionarMedicamento("pActivo", aux, scanner, medicamentos);
					}	
					break;
				case 4:
					salida = true;
					break;
				default:
					System.out.println("Opcion invalida");
					break;
			} //SWITCH_END
		} while(!salida);
	}
	
	private static void pantallaCompra(Scanner scanner, String[] pantalla3, Optional<Medicamentos> medicamentos, FarmaciaService service) throws ClassNotFoundException, IOException
	{
		boolean salida = false;
		int option = 0;
		String aux;
		Optional<Medicamento> medNombre;
		Cliente cliente;
		
		// PREGUNTAR POR CLIENTE
		boolean preguntaRegistrado = preguntarClienteRegistrado(scanner);

		if(preguntaRegistrado)
		{	
			// SI YA ESTA REGISTRADO
			System.out.print("Introduce el id: ");
			aux = scanner.next();
			
			if(service.listarCliente(aux).isPresent())
			{
				cliente = service.listarCliente(aux).get();
			}
			else
			{
				System.out.println("No existe cliente registrado con ese id.");
				return;
			}
		}
		else
		{
			// SI NO ESTA REGISTRADO
			System.out.print("Introduce el correo para registrar un nuevo cliente: ");
			aux = scanner.next();
			cliente = new Cliente(aux);
			service.addCliente(cliente);
			cliente = service.listarCliente(cliente.getIdUsuario()).get();
		}

		do 
		{
			salida = false;
			System.out.println("FarmaciaDSS: Menu compra");
			imprimirMenu(pantalla3);

			option = opcionValida(scanner);
			System.out.println();

			switch (option) 
			{
			case 1:
				// INTRODUCIR MEDICAMENTO AL CARRITO.
				aux = solicitarDatoMedicamento("nombre", scanner);
				medNombre = service.listarporNombre(aux);

				if (medNombre.isPresent()) 
				{
					// EL MEDICAMENTO ESTA EN LA FARMACIA.
					service.addCarrito(cliente.getIdUsuario(), aux);
				} 
				else 
				{
					System.out.println("No se ha encontrado un medicamento con nombre " + aux + ".\n");
				}

				break;
			case 2:
				// ELIMINAR MEDICAMENTO DEL CARRITO
				if(service.listarCliente(cliente.getIdUsuario()).get().getCompra().getNumElem() == 0)  
				{
					System.out.println("El carrito esta vacio\n");
				} 
				else 
				{
					aux = solicitarDatoMedicamento("nombre", scanner);

					medNombre = service.buscarMedCarrito(aux, cliente.getIdUsuario());

					if (medNombre.isPresent())
					{
						medNombre = service.listarporNombre(aux);
						service.deleteCarrito(cliente.getIdUsuario(), medNombre.get().getNombre());
					} 
					else 
					{
						System.out.println("No se ha encontrado un medicamento con nombre " + aux + ".\n");
					}
				}
				break;

			case 3:
				// IMPRIMIR CARRITO
				if(service.listarCliente(cliente.getIdUsuario()).get().getCompra().getNumElem() == 0)
				{
					System.out.println("El carrito esta vacio.\n");
				} 
				else 
				{
					System.out.println("Carrito actual");
					System.out.println("-------------------------------------------------------------------");

					//System.out.println(cliente.getCompra().toString());
					System.out.println(service.listarCliente(cliente.getIdUsuario()).get().getCompra().toString());
				}
				break;
			case 4:
				service.finalizarCompra(cliente.getIdUsuario());	
				break;
			case 5:
				salida = true;
				break;
			default:
				System.out.println("Opcion Invalida");
				break;
			} // END_SWITCH
		} while (!salida);
	}
	
	private static void pantallaPrescripciones(Scanner scanner, String[] pantalla4, FarmaciaService service)
	{
		boolean salida = false;
		int option = 0;
		String tarjeta;
		String medicamento;
		int udportoma;
		int hportoma;
		
		System.out.print("Introduce tu tarjeta sanitaria: ");
		tarjeta = scanner.next();
		System.out.println();

		do
		{
			salida = false;
			System.out.println("FarmaciaDSS: Tarjeta sanitaria");
			imprimirMenu(pantalla4);

			option = opcionValida(scanner);

			switch(option)
			{
			case 1:
				// CONSULTAR MEDICAMENTOS DE LA TARJETA SANITARIA.
				JSONArray dataObject = service.consultarTarjeta(tarjeta);
				int i = 0;
				 while(i != dataObject.size())
	                {
	                    System.out.println(dataObject.get(i));           
	                    i++;
	                }
				System.out.println();
				break;
			case 2:		
				// INTRODUCIR UN MEDICAMENTO EN LA TARJETA SANITARIA.
				System.out.print("Introduce el codigo del medicamento: ");
				medicamento = scanner.next();
				System.out.print("Introduce las unidades por toma: ");
				udportoma = scanner.nextInt();
				System.out.print("Introduce las horas por toma: ");
				hportoma = scanner.nextInt();
				MedicamentoTarjeta medTarjeta = new MedicamentoTarjeta(medicamento, tarjeta, udportoma, hportoma);
				service.addMedTarjeta(medTarjeta);
				System.out.println();
				break;
			case 3:
				// DISPENSAR UN MEDICAMENTO DE LA TARJETA SANITARIA.
				System.out.println("Introduce el código del medicamento que deseas dispensar: ");
				medicamento = scanner.next();
				service.dispensarMedTarjeta(medicamento, tarjeta);
				break;
			case 4:
				salida = true;
				break;
			default:
				System.out.println("Opcion Invalida");
				break;
			}	// SWITCH_END
		} while (!salida);
	}
	
	private static void pantallaAumentarStock(Scanner scanner, String[] pantalla5, FarmaciaService service)
	{
		boolean salida = false;
		String medicamento;
		int stock;
		int option = 0;
		
		do
		{
			System.out.println("FarmaciaDSS: Stock");
			imprimirMenu(pantalla5);

			option = opcionValida(scanner);

			switch(option)
			{
			case 1:
				System.out.print("Elige el medicamento al que vas a aumentar el Stock: ");
				medicamento = scanner.next();

				if(service.listarporNombre(medicamento).isPresent())
				{
					System.out.print("Cuantas unidades deseas incluir: ");
					stock = scanner.nextInt();
					service.addStock(medicamento, stock);
					System.out.print("\nStock actualizado.\n\n");
				}
				else System.out.println("\nEl medicamento " + medicamento + " no existe.\n");
				break;
			case 2:
				salida = true;
				break;
			default:
				System.out.println("Opcion Invalida");
				break;
			}
		} while (!salida);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		int option = 0;
		Optional<Medicamentos> medicamentos = Optional.of(new Medicamentos(1000));
		boolean salida = false;
		
		String[] pantalla1 = {"Consultar medicamento",
							"Comprar",
							"Gestionar tarjeta sanitaria",
							"Anadir stock a un medicamento",
							"Salir",
		};

		String[] pantalla2 = {"Consultar medicamento por nombre",
							"Consultar medicamento por categoria",
							"Consultar medicamento por principio activo",
							"Volver a la pantalla anterior",
		};

		String[] pantalla3 = {"Anadir medicamento al carrito",
							"Quitar medicamento del carrito",
							"Mostrar carrito",
							"Finalizar compra",
							"Volver a la pantalla anterior",
		};

		String[] pantalla4 = {"Consultar medicamentos en tarjeta sanitaria",
							"Registrar medicamentos en tarjeta sanitaria",
							"Dispensar medicamentos de la tarjeta sanitaria",
							"Volver a la pantalla anterior",
		};
		
		String[] pantalla5 = {"Añadir stock del medicamento",
							"Volver a la pantalla anterior",	
		};
		
		salida = false; 
		while (!salida) 
		{
			System.out.println("\nFarmaciaDSS: Menu principal");
			imprimirMenu(pantalla1);
			option = opcionValida(scanner);
			System.out.println();

			// PANTALLA 1. MENU PRINCIPAL.
			switch (option) 
			{
			case 1:
				// BÚSQUEDA DE MEDICAMENTOS EN FARMACIA
				pantallaBuscarMed(scanner, pantalla2, medicamentos, service);
				break;
			case 2:
				// GESTIONAR LAS COMPRAS DE UN CLIENTE
				pantallaCompra(scanner, pantalla3, medicamentos, service);
				break;	
			case 3:
				// USAR LA API DE PRESCRIPCIONES
				pantallaPrescripciones(scanner, pantalla4, service);
				break;
			case 4:
				// AUMENTAR STOCK A UN MEDICAMENTO
				pantallaAumentarStock(scanner, pantalla5, service);
				break;
			case 5:
				System.out.println("Gracias por usar el sistema. Programa terminado.");
				salida = true;
				break;
			default:
				System.out.println("Opcion Invalida");
				break;
			}
		}
		scanner.close();
		System.exit(0);
		
	}

}
		