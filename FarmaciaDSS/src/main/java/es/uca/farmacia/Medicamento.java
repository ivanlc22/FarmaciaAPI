package es.uca.farmacia;
import java.io.*;

import javax.persistence.*;
import lombok.Setter;

// COMO AUTO GENERAR CODIGOS NUMERICOS EN JAVA CON ATRIBUTOS STATIC

@Setter
@Entity
@Table(name = "MEDICAMENTOS")
public class Medicamento implements Serializable
{
	@Transient
	private static final long serialVersionUID = -5011982980554189189L;
	@Id
	private int codigo;	
	private String nombre;	
	private String composicion;
	private String categoria;
	private enumFormas forma;
	private int stock;
	private int precio;

	public Medicamento(int codigo, String nombre, String composicion, String categoria, enumFormas forma, int stock, int precio) 
	{
		this.codigo = codigo;
		this.nombre = nombre.toLowerCase();
		this.composicion = composicion.toLowerCase();
		this.categoria = categoria.toLowerCase();
		this.forma = forma;
		this.stock = stock;
		this.precio = precio;
	}
	
	public Medicamento() {
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getCodigo() 
	{
		return codigo;
	}

	public void setCodigo(int codigo) 
	{
		this.codigo = codigo;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getComposicion() 
	{
		return composicion;
	}

	public void setComposicion(String composicion) 
	{
		this.composicion = composicion;
	}

	public String getCategoria() 
	{
		return categoria;
	}

	public void setCategoria(String categoria) 
	{
		this.categoria = categoria;
	}
	
	public int getStock()
	{
		return stock;
	}
	
	public void setStock(int stock) 
	{
		this.stock = stock;
	}
	
	public enumFormas getForma() 
	{
		return forma;
	}
	

	public void setForma(enumFormas forma) 
	{
		this.forma = forma;
	}
	
	@Override
	public String toString()
	{
		String resultado;
		String linea = "-------------------------------------------------------------------";
		
		resultado = linea + "\n" + "Codigo nacional: " + codigo 
					+ "\n" + "Nombre comercial: " + nombre
					+ "\n" + "Composicion (Principio Activo): " + composicion
					+ "\n" + "Categoria: " + categoria
					+ "\n" + "Forma farmaceutica: " + forma
					+ "\n" + "Stock disponible: " + stock
					+ "\n" + "Precio: " + precio
					+ "\n" + linea + "\n";	
		
		return resultado;
	}
}
