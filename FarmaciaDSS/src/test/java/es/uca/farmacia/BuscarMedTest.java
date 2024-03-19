package es.uca.farmacia;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class BuscarMedTest {

	@Test
	public void testBuscarporcategoria() {
		Farmacia farma = new Farmacia();
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		Medicamento med2 = new Medicamento(2, "NOMBRE2", "composicion2", "categoria2", enumFormas.oral, 2, 4);
		Medicamento med3 = new Medicamento(3, "NOMBRE3", "composicion3", "categoria3", enumFormas.oral, 2, 4);
		farma.addMed(med1);
		farma.addMed(med2);
		farma.addMed(med3);
		Optional<Medicamentos> medicamentos = Optional.empty();
		medicamentos = BuscarMed.buscarporcategoria("categoria1", farma.getMedicamentos());
		assertEquals(med1, medicamentos.get().getMedicamento(0));
	}

	@Test
	public void testBuscarporpactivo() {
		Farmacia farma = new Farmacia();
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		Medicamento med2 = new Medicamento(2, "NOMBRE2", "composicion2", "categoria2", enumFormas.oral, 2, 4);
		Medicamento med3 = new Medicamento(3, "NOMBRE3", "composicion3", "categoria3", enumFormas.oral, 2, 4);
		farma.addMed(med1);
		farma.addMed(med2);
		farma.addMed(med3);
		Optional<Medicamentos> medicamentos = Optional.empty();
		medicamentos = BuscarMed.buscarporpactivo("composicion2", farma.getMedicamentos());
		assertEquals(med2, medicamentos.get().getMedicamento(0));
	}

	@Test
	public void testBuscarpornombre() {
		Farmacia farma = new Farmacia();
		Medicamento med1 = new Medicamento(1, "NOMBRE1", "composicion1", "categoria1", enumFormas.oral, 2, 4);
		farma.addMed(med1);
		Optional<Medicamento> medicamento = Optional.empty();
		medicamento = BuscarMed.buscarpornombre("nombre1", farma.getMedicamentos());
		assertEquals(med1, medicamento.get());
	}

}
