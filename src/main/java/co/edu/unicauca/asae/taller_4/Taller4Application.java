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
		//almacenarDocente();
		//cargarAsignaturas();
		//cargarEspaciosFisicos();

		//almacenarCurso();
		//almacenarFranjaHorario();
		
		//listarFranjasHorarias();
		//consultarFranjaHorariaPorDocente(1);
		
		//eliminarCurso(1);

		//consultarDocentes();
		//consultarOficinas();
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
	private void almacenarDocente() {

		OficinaEntity objOficina = new OficinaEntity();
		objOficina.setNombre("Oficina de Andres");
		objOficina.setUbicacion("FIET Piso 3");

		// OficinaEntity objOficinaAlmacenada = this.servicioBDoficinas.save(objOficina);
		// System.out.println("ID generado para la oficina: " + objOficinaAlmacenada.getIdOficina());

		DocenteEntity docente = new DocenteEntity();
		docente.setNombre("Andres");
		docente.setApellido("Torres");
		docente.setCorreo("atorresp@unicauca.edu.co");
		docente.setObjOficina(objOficina);

		DocenteEntity objDocenteAlmacenado = this.servicioBDdocentes.save(docente);
		System.out.println("ID generado para el docente: " + objDocenteAlmacenado.getIdPersona());
	}

	//* SEGUNDO MÉTODO */
	public void almacenarCurso() {
		System.out.println("\nLlamando a Almacenar Curso...\n");
		Optional<AsignaturaEntity> asignaturaUno = this.servicioBDasignaturas.findById(1);
		Optional<DocenteEntity> docenteUno = this.servicioBDdocentes.findById(1);
		System.out.println("\nAsignatura: " + asignaturaUno.get().getNombre());
		System.out.println("Docente: " + docenteUno.get().getNombre() + "\n");
		
		CursoEntity nuevoCurso = new CursoEntity();
		nuevoCurso.setNombre("Grupo A");
		nuevoCurso.setObjAsignatura(asignaturaUno.get());

		CursoEntity cursoAlmacenado = this.servicioBDcursos.save(nuevoCurso);
		System.out.println("\nID generado para el curso: " + cursoAlmacenado.getId() + "\n");

		docenteUno.get().getCursos().add(cursoAlmacenado);
	}

	//* TERCER MÉTODO */
	public void almacenarFranjaHorario() {
		System.out.println("\nLlamando a Almacenar Franja Horario...\n");
		Optional<CursoEntity> cursoUno = this.servicioBDcursos.findById(1);
		Optional<EspacioFisicoEntity> espacioFisicoUno = this.servicioBDespaciosFisicos.findById(2);

		FranjaHorarioEntity nuevaFranja = new FranjaHorarioEntity();
		nuevaFranja.setDia("Miércoles");
		nuevaFranja.setHoraInicio(java.time.LocalTime.of(16, 0));
		nuevaFranja.setHoraFin(java.time.LocalTime.of(18, 0));
		nuevaFranja.setObjCurso(cursoUno.get());
		nuevaFranja.setObjEspacioFisico(espacioFisicoUno.get());

		FranjaHorarioEntity franjaAlmacenada = this.servicioBDfranjasHorarias.save(nuevaFranja);
		System.out.println("\nID generado para la franja horaria: " + franjaAlmacenada.getId() + "\n");
	}

	//* CUARTO MÉTODO */
	public void listarFranjasHorarias() {
		System.out.println("\nLlamando a Listar Franjas Horarias...\n");
		Iterable<FranjaHorarioEntity> listaFranjas = this.servicioBDfranjasHorarias.findAll();
		System.out.println("\n");
		for (FranjaHorarioEntity franja : listaFranjas) {
			System.out.println("[ Franja Horaria ]");
			System.out.println("Día: " + franja.getDia());
			System.out.println("Hora de inicio: " + franja.getHoraInicio());
			System.out.println("Hora de fin: " + franja.getHoraFin());
			System.out.println("<Curso asociado>");
			System.out.println("Nombre: " + franja.getObjCurso().getNombre());
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
		Optional<DocenteEntity> docenteUno = this.servicioBDdocentes.findById(idDocente);
		var listaCursos = docenteUno.get().getCursos();
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
				System.out.println("ID del docente: " + docenteUno.get().getIdPersona());
				System.out.println("Nombre del docente: " + docenteUno.get().getNombre());
				System.out.println("Apellido del docente: " + docenteUno.get().getApellido());
				System.out.println("Correo del docente: " + docenteUno.get().getCorreo());
				System.out.println("<Curso asociado>");
				System.out.println("ID del curso: " + cursoEntity.getId());
				System.out.println("Nombre del curso: " + cursoEntity.getNombre());
				System.out.println("<Espacio físico asociado>");
				System.out.println("ID del espacio físico: " + franjaHorarioEntity.getObjEspacioFisico().getId());
				System.out.println("Nombre del espacio físico: " + franjaHorarioEntity.getObjEspacioFisico().getNombre());
			}
		}
		System.out.println("\n");
	}

	//* SEXTO MÉTODO */
	public void eliminarCurso(int idCurso) {
		System.out.println("\nLlamando a Eliminar Curso[eliminando en cascada las franjas asociadas]...\n");
		Optional<DocenteEntity> docenteUno = this.servicioBDdocentes.findById(1);
		for (CursoEntity curso : docenteUno.get().getCursos()) {
			if (curso.getId() == idCurso) {
				docenteUno.get().getCursos().remove(curso);
				break;
			}
		}
		this.servicioBDcursos.deleteById(idCurso);
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
