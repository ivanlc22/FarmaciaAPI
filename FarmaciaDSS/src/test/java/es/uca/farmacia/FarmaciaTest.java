package es.uca.farmacia;

import static org.junit.Assert.*;

import org.junit.Test;

public class FarmaciaTest {
	
	@Test
	public void testFarmacia() {
		Farmacia farma = new Farmacia();
		assertEquals(0, farma.getNumMed());
		assertEquals(0, farma.getNumMed());
	}

	@Test
	public void testGetMedicamentos() {
		Farmacia farma = new Farmacia();
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		Medicamento med2 = new Medicamento(2, "NOMBRE2", "composicion2", "categoria2", enumFormas.oral, 2, 4);
		Medicamento med3 = new Medicamento(3, "NOMBRE3", "composicion3", "categoria3", enumFormas.oral, 2, 4);
		farma.addMed(med1);
		farma.addMed(med2);
		farma.addMed(med3);
		assertEquals(farma.getMedicamentos().getMedicamento(0), med1); 
		assertEquals(farma.getMedicamentos().getMedicamento(1), med2); 
		assertEquals(farma.getMedicamentos().getMedicamento(2), med3); 
	}

	@Test
	public void testSetMedicamentos() {
		Farmacia farma = new Farmacia();
		Medicamentos medicamentos = new Medicamentos(10);
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		medicamentos.addMedicamento(med1);
		farma.setMedicamentos(medicamentos);
		assertEquals(farma.getMedicamentos(), medicamentos); 
	}

	@Test
	public void testGetNumMed() {
		Farmacia farma = new Farmacia();
		assertEquals(0, farma.getNumMed());
	}

	@Test
	public void testAddMed() {
		Farmacia farma = new Farmacia();
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		farma.addMed(med1);
		assertEquals(farma.getMedicamentos().getMedicamento(0), med1);
		assertEquals(1, farma.getNumMed());
	}

	@Test
	public void testDeleteMed() {
		Farmacia farma = new Farmacia();
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		Medicamento med2 = new Medicamento(2, "NOMBRE2", "composicion2", "categoria2", enumFormas.oral, 2, 4);
		Medicamento med3 = new Medicamento(3, "NOMBRE3", "composicion3", "categoria3", enumFormas.oral, 2, 4);
		farma.addMed(med1);
		farma.addMed(med2);
		farma.addMed(med3);		
		assertEquals(3, farma.getNumMed());
		farma.deleteMed(med2);
		assertEquals(2, farma.getNumMed());
		assertEquals(farma.getMedicamentos().getMedicamento(0), med1); 
		assertEquals(farma.getMedicamentos().getMedicamento(1), med3); 	
	}

	@Test
	public void testIterator() {
		Farmacia farma = new Farmacia();
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		farma.addMed(med1);
		assertEquals(farma.iterator().next(), med1);
	}
}
