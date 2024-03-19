package es.uca.farmacia;

import static org.junit.Assert.*;

import org.junit.Test;

public class CompraTest {

	@Test
	public void testCompra() {
		Compra carrito = new Compra();
		assertEquals(0, carrito.getNumElem());
	}

	@Test
	public void testGetNumElem() {
		Compra carrito = new Compra();
		Medicamento med = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		carrito.meterCarrito(med);
		assertEquals(1, carrito.getNumElem()); 
	}

	@Test
	public void testGetCarrito() {
		Compra carrito = new Compra();
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		Medicamento med2 = new Medicamento(2, "NOMBRE2", "composicion2", "categoria2", enumFormas.oral, 2, 4);
		Medicamento med3 = new Medicamento(3, "NOMBRE3", "composicion3", "categoria3", enumFormas.oral, 2, 4);
		carrito.meterCarrito(med1);
		carrito.meterCarrito(med2);
		carrito.meterCarrito(med3);
		assertEquals(carrito.getCarrito().getMedicamento(0), med1); 
		assertEquals(carrito.getCarrito().getMedicamento(1), med2); 
		assertEquals(carrito.getCarrito().getMedicamento(2), med3); 
	
	}

	@Test
	public void testSetCarrito() {
		Compra carrito = new Compra();
		Medicamentos medicamentos = new Medicamentos(10);
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		medicamentos.addMedicamento(med1);
		carrito.setCarrito(medicamentos);
		assertEquals(carrito.getCarrito(), medicamentos);
	}

	@Test
	public void testMeterCarrito() {
		Compra carrito = new Compra();
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		carrito.meterCarrito(med1);
		assertEquals(carrito.getCarrito().getMedicamento(0), med1);
	}

	@Test
	public void testSacarCarrito() {
		Compra carrito = new Compra();
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		Medicamento med2 = new Medicamento(2, "NOMBRE2", "composicion2", "categoria2", enumFormas.oral, 2, 4);
		carrito.meterCarrito(med1);
		carrito.meterCarrito(med2);
		assertEquals(2, carrito.getNumElem());
		carrito.sacarCarrito(med1);
		assertEquals(1, carrito.getNumElem());	
	}

	@Test
	public void testFinCompra() {
		Compra carrito = new Compra();
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		Medicamento med2 = new Medicamento(2, "NOMBRE2", "composicion2", "categoria2", enumFormas.oral, 2, 4);
		carrito.meterCarrito(med1);
		carrito.meterCarrito(med2);
		carrito.finCompra();
		assertEquals(0, carrito.getNumElem());
	}

	@Test
	public void testGetPrice() {
		Compra carrito = new Compra();
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		Medicamento med2 = new Medicamento(2, "NOMBRE2", "composicion2", "categoria2", enumFormas.oral, 2, 4);
		carrito.meterCarrito(med1);
		carrito.meterCarrito(med2);
		assertEquals(8, carrito.getPrice());
	}
}
