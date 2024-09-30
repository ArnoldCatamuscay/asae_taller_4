package co.edu.unicauca.asae.taller_4;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.unicauca.asae.taller_4.capaAccesoDatos.models.AsignaturaEntity;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.models.CursoEntity;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.models.DocenteEntity;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.models.EspacioFisicoEntity;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.models.FranjaHorarioEntity;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.models.OficinaEntity;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.repositories.AsignaturaRepository;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.repositories.CursoRepository;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.repositories.DocentesRepository;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.repositories.EspacioFisicoRepository;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.repositories.FranjaHorarioRepository;
import co.edu.unicauca.asae.taller_4.capaAccesoDatos.repositories.OficinasRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@SpringBootApplication
@Transactional
@SuppressWarnings("unused")
public class Taller4Application implements CommandLineRunner {

	@Autowired
	private DocentesRepository servicioBDdocentes;

	@Autowired
	private OficinasRepository servicioBDoficinas;

	@Autowired
	private AsignaturaRepository servicioBDasignaturas;

	@Autowired
	private CursoRepository servicioBDcursos;

	@Autowired
	private EspacioFisicoRepository servicioBDespaciosFisicos;

	@Autowired
	private FranjaHorarioRepository servicioBDfranjasHorarias;

	public static void main(String[] args) {
		SpringApplication.run(Taller4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// cargarAsignaturas();
		// cargarEspaciosFisicos();

		// crearDocente();
		// crearCurso();
		// crearFranjaHorario();
		
		// listarFranjasHorarias();
		// consultarFranjaHorariaPorDocente(1);
		
		// eliminarCurso(1);
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

	//* PRIMER MÉTODO */
	private void crearDocente() {
		System.out.println("\nLlamando a Crear Docente...\n");
		DocenteEntity docente = new DocenteEntity();
		docente.setNombre("Daniel");
		docente.setApellido("Herrera");
		docente.setCorreo("dherrera@unicauca.edu.co");

		OficinaEntity objOficina = new OficinaEntity();
		objOficina.setNombre("Oficina de Daniel");
		objOficina.setUbicacion("FIET Piso 3");
		docente.setObjOficina(objOficina);

		DocenteEntity objDocenteAlmacenado = this.servicioBDdocentes.save(docente);
		System.out.println("\nID generado para el docente: " + objDocenteAlmacenado.getIdPersona());
		System.out.println("\nNombre completo: " + objDocenteAlmacenado.getNombre() + 
			" " + objDocenteAlmacenado.getApellido());
		System.out.println("\nCorreo: " + objDocenteAlmacenado.getCorreo());
		System.out.println("\nOficina: " + objDocenteAlmacenado.getObjOficina().getNombre() + 
			" - " + objDocenteAlmacenado.getObjOficina().getUbicacion() + "\n");
	}

	//* SEGUNDO MÉTODO */
	//? Pide usar PERSIST
	public void crearCurso() {
		System.out.println("\nLlamando a Crear Curso...\n");
		Optional<AsignaturaEntity> asignatura = this.servicioBDasignaturas.findById(1);
		Optional<DocenteEntity> docente = this.servicioBDdocentes.findById(1);
		
		CursoEntity nuevoCurso = new CursoEntity();
		nuevoCurso.setNombre("Grupo A");
		nuevoCurso.setObjAsignatura(asignatura.get());

		CursoEntity cursoAlmacenado = this.servicioBDcursos.save(nuevoCurso);
		System.out.println("\nID generado para el curso: " + cursoAlmacenado.getId());
		System.out.println("\nAsignatura asociada: " + cursoAlmacenado.getObjAsignatura().getNombre());
		System.out.println("\nNombre del curso: " + cursoAlmacenado.getNombre());
		System.out.println("\nDocente asociado: " + docente.get().getNombre() + " " + docente.get().getApellido() + "\n");

		docente.get().getCursos().add(cursoAlmacenado);
	}

	//* TERCER MÉTODO */
	public void crearFranjaHorario() {
		System.out.println("\nLlamando a Crear Franja Horario...\n");
		Optional<CursoEntity> curso = this.servicioBDcursos.findById(2);
		Optional<EspacioFisicoEntity> espacioFisico = this.servicioBDespaciosFisicos.findById(3);

		FranjaHorarioEntity nuevaFranja = new FranjaHorarioEntity();
		nuevaFranja.setDia("Lunes");
		nuevaFranja.setHoraInicio(java.time.LocalTime.of(9, 0));
		nuevaFranja.setHoraFin(java.time.LocalTime.of(11, 0));
		nuevaFranja.setObjCurso(curso.get());
		nuevaFranja.setObjEspacioFisico(espacioFisico.get());

		FranjaHorarioEntity franjaAlmacenada = this.servicioBDfranjasHorarias.save(nuevaFranja);
		System.out.println("\nID generado para la franja horaria: " + franjaAlmacenada.getId());
		System.out.println("\nDía: " + franjaAlmacenada.getDia());
		System.out.println("\nDuración: " + franjaAlmacenada.getHoraInicio() +
			" - " + franjaAlmacenada.getHoraFin());
		System.out.println("\nAsignatura: " + franjaAlmacenada.getObjCurso().getObjAsignatura().getNombre());
		System.out.println("\nCurso: " + franjaAlmacenada.getObjCurso().getNombre());
		System.out.println("\nEspacio físico: " + franjaAlmacenada.getObjEspacioFisico().getNombre());
		System.out.println("\nCapacidad: " + franjaAlmacenada.getObjEspacioFisico().getCapacidad() + "\n");
	}

	//* CUARTO MÉTODO */
	public void listarFranjasHorarias() {
		System.out.println("\nLlamando a Listar Franjas Horarias...\n");
		Iterable<FranjaHorarioEntity> listaFranjas = this.servicioBDfranjasHorarias.findAll();
		System.out.println("\n");
		for (FranjaHorarioEntity franja : listaFranjas) {
			System.out.println("[ Franja Horaria ]");
			System.out.println("ID: " + franja.getId());
			System.out.println("Día: " + franja.getDia());
			System.out.println("Duración: " + franja.getHoraInicio() + " - " + franja.getHoraFin());
			System.out.println("<Curso asociado>");
			System.out.println("Asignatura: " + franja.getObjCurso().getObjAsignatura().getNombre());
			System.out.println("Curso: " + franja.getObjCurso().getNombre());
			System.out.println("<Espacio físico asociado>");
			System.out.println("Nombre: " + franja.getObjEspacioFisico().getNombre());
			System.out.println("Capacidad: " + franja.getObjEspacioFisico().getCapacidad());
			System.out.println(" ---- ---- ----");
		}
		System.out.println("\n");
	}

	//* QUINTO MÉTODO */
	public void consultarFranjaHorariaPorDocente(int idDocente) {
		System.out.println("\nLlamando a Consultar Franja Horaria Por Docente...\n");
		Optional<DocenteEntity> docente = this.servicioBDdocentes.findById(idDocente);
		var listaCursos = docente.get().getCursos();
		if(listaCursos.isEmpty()) {
			System.out.println("El docente no tiene cursos asignados.");
			return;
		}
		for (CursoEntity cursoEntity : listaCursos) {
			var listaFranjas = cursoEntity.getFranjasHorario();
			for (FranjaHorarioEntity franjaHorarioEntity : listaFranjas) {
				System.out.println("\n[ Franja Horaria ]");
				System.out.println("ID de la franja: " + franjaHorarioEntity.getId());
				System.out.println("<Docente asociado>");
				System.out.println("ID: " + docente.get().getIdPersona());
				System.out.println("Nombre completo: " + docente.get().getNombre() +
					" " + docente.get().getApellido());
				System.out.println("Correo: " + docente.get().getCorreo());
				System.out.println("<Curso asociado>");
				System.out.println("ID: " + cursoEntity.getId());
				System.out.println("Asignatura: " + cursoEntity.getObjAsignatura().getNombre());
				System.out.println("Nombre: " + cursoEntity.getNombre());
				System.out.println("<Espacio físico asociado>");
				System.out.println("ID: " + franjaHorarioEntity.getObjEspacioFisico().getId());
				System.out.println("Nombre: " + franjaHorarioEntity.getObjEspacioFisico().getNombre());
				System.out.println("Capacidad: " + franjaHorarioEntity.getObjEspacioFisico().getCapacidad());
			}
		}
		System.out.println("\n");
	}

	//* SEXTO MÉTODO */
	public void eliminarCurso(int cursoId) {
		System.out.println("\nLlamando a Eliminar Curso...\n");
		CursoEntity curso = servicioBDcursos.findById(cursoId)
			.orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
		// Eliminar las referencias en la tabla de unión
		for (DocenteEntity docente : curso.getDocentes()) {
			docente.removeCurso(curso);
		}
		// Eliminar el curso
		servicioBDcursos.delete(curso);
	}

	public void cargarAsignaturas() {
		var asignaturaUno = new AsignaturaEntity();
		asignaturaUno.setNombre("Cálculo I");
		asignaturaUno.setCodigo("CAL-101");
		var asignaturaDos = new AsignaturaEntity();
		asignaturaDos.setNombre("Física");
		asignaturaDos.setCodigo("FIS-101");
		var asignaturaTres = new AsignaturaEntity();
		asignaturaTres.setNombre("Química");
		asignaturaTres.setCodigo("QUI-101");
		var listaAsignaturas = List.of(asignaturaUno, asignaturaDos, asignaturaTres);
		this.servicioBDasignaturas.saveAll(listaAsignaturas);
	}

	public void cargarEspaciosFisicos() {
		var espacioUno = new EspacioFisicoEntity();
		espacioUno.setNombre("Salón 201");
		espacioUno.setCapacidad(30);
		var espacioDos = new EspacioFisicoEntity();
		espacioDos.setNombre("Salón 202");
		espacioDos.setCapacidad(20);
		var espacioTres = new EspacioFisicoEntity();
		espacioTres.setNombre("Salón 203");
		espacioTres.setCapacidad(20);
		var listaEspaciosFisicos = List.of(espacioUno, espacioDos, espacioTres);
		this.servicioBDespaciosFisicos.saveAll(listaEspaciosFisicos);
	}

	private void mostrarListaFranjasEnCursos() {
		System.out.println("\nLlamando a Mostrar Lista Franjas En Cursos...\n");
		var cursoUno = this.servicioBDcursos.findById(1);
		System.out.println("\nEl tamaño de la lista es: " + cursoUno.get().getFranjasHorario().size());
		for (FranjaHorarioEntity obj : cursoUno.get().getFranjasHorario()) {
			System.out.println("ID: " + obj.getId());
			System.out.println("Día: " + obj.getDia());
			System.out.println("Hora de inicio: " + obj.getHoraInicio());
			System.out.println("Hora de fin: " + obj.getHoraFin());
			System.out.println("Espacio físico: " + obj.getObjEspacioFisico().getNombre());
			System.out.println("Capacidad: " + obj.getObjEspacioFisico().getCapacidad());
		}
	}

	private void mostrarListaFranjasEnEspaciosFisicos() {
		System.out.println("\nLlamando a Mostrar Lista Franjas En Espacios Fisicos...\n");
		var espacioUno = this.servicioBDespaciosFisicos.findById(1);
		System.out.println("\nEl tamaño de la lista es: " + espacioUno.get().getFranjasHorario().size());
		for (FranjaHorarioEntity obj : espacioUno.get().getFranjasHorario()) {
			System.out.println("ID: " + obj.getId());
			System.out.println("Día: " + obj.getDia());
			System.out.println("Hora de inicio: " + obj.getHoraInicio());
			System.out.println("Hora de fin: " + obj.getHoraFin());
			System.out.println("Espacio físico: " + obj.getObjEspacioFisico().getNombre());
			System.out.println("Capacidad: " + obj.getObjEspacioFisico().getCapacidad());
		}
	}
}
