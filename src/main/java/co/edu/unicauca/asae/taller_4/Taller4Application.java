package co.edu.unicauca.asae.taller_4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.unicauca.asae.taller_4.capaAccesoDatos.models.DocenteEntity;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.models.OficinaEntity;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.repositories.DocentesRepository;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.repositories.OficinasRepository;
import jakarta.transaction.Transactional;

@SpringBootApplication
@Transactional
public class Taller4Application implements CommandLineRunner {

	@Autowired
	private DocentesRepository servicioBDdocentes;

	@Autowired
	private OficinasRepository servicioBDoficinas;

	public static void main(String[] args) {
		SpringApplication.run(Taller4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		almacenarDocente();
		consultarDocentes();
		// consultarOficinas();
	}

	private void consultarOficinas() {
		Iterable<OficinaEntity> listaOficinas = this.servicioBDoficinas.findAll();

		for (OficinaEntity oficina : listaOficinas) {
			System.out.println("[ Oficina ]");
			System.out.println("Nombre: " + oficina.getNombre());
			System.out.println("Ubicación: " + oficina.getUbicacion());
			System.out.println("<Docente asociado>");
			System.out.println("Nombre: " + oficina.getObjDocente().getNombre());
			System.out.println("Apellido: " + oficina.getObjDocente().getApellido());
			System.out.println("Correo: " + oficina.getObjDocente().getCorreo());
			System.out.println(" ---- ---- ----");
		}
	}

	private void consultarDocentes() {
		Iterable<DocenteEntity> listaDocentes = this.servicioBDdocentes.findAll();
		for (DocenteEntity docente : listaDocentes) {
			System.out.println("[ Docente ]");
			System.out.println("Nombre: " + docente.getNombre());
			System.out.println("Apellido: " + docente.getApellido());
			System.out.println("Correo: " + docente.getCorreo());
			System.out.println("<Oficina asociada> ");
			System.out.println("Nombre:" + docente.getObjOficina().getNombre());
			System.out.println("Ubicación:" + docente.getObjOficina().getUbicacion());
			System.out.println(" ---- ---- -----");
		}
	}

	private void almacenarDocente() {

		OficinaEntity objOficina = new OficinaEntity();
		objOficina.setNombre("Oficina de Andres");
		objOficina.setUbicacion("FIET Piso 3");

		OficinaEntity objOficinaAlmacenada = this.servicioBDoficinas.save(objOficina);
		System.out.println("ID generado para la oficina: " + objOficinaAlmacenada.getIdOficina());

		DocenteEntity docente = new DocenteEntity();
		docente.setNombre("Andres");
		docente.setApellido("Torres");
		docente.setCorreo("atorresp@unicauca.edu.co");
		docente.setObjOficina(objOficinaAlmacenada);

		DocenteEntity objDocenteAlmacenado = this.servicioBDdocentes.save(docente);
		System.out.println("ID generado para el docente: " + objDocenteAlmacenado.getIdPersona());
	}
}
