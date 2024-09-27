package co.edu.unicauca.asae.taller_4.capaAccesoDatos.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EspaciosFisicos")
public class EspacioFisicoEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(unique = true, length = 255)
    String nombre;
    Integer capacidad;

    //* Relación */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "objEspacioFisico")
	private List<FranjaHorarioEntity> franjasHorario;
}
