package ar.edu.unlam.tallerweb1.modelo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

// Clase que modela el concepto de Usuario, la anotacion @Entity le avisa a hibernate que esta clase es persistible
// el paquete ar.edu.unlam.tallerweb1.modelo esta indicado en el archivo hibernateCOntext.xml para que hibernate
// busque entities en el
@Entity
@Embeddable
public class Usuario {

	// La anotacion id indica que este atributo es el utilizado como clave primaria de la entity, se indica que el valor es autogenerado.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// para el resto de los atributo no se usan anotaciones entonces se usa el default de hibernate: la columna se llama igual que
	// el atributo, la misma admite nulos, y el tipo de dato se deduce del tipo de dato de java.
	private String email;
	private String password;
	private String rol;
	private Boolean activo = false;
	private String nombre;
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
	@OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Mascota> mascotas;
	@Transient
	private long edad;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public boolean activo() {
		return activo;
    }

    public void activar() {
		activo = true;
    }

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public Usuario setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
		return this;
	}

	public String getNombre() {
		return nombre;
	}

	public Usuario setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public Usuario setEdad(long edad) {
		this.edad = edad;
		return this;
	}

	public List<Mascota> getMascotas() {
		return mascotas;
	}

	public Usuario setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
		return this;
	}

	public long getEdad() {
		YearMonth from = YearMonth.from(
				getFechaNacimiento()
						.toInstant()
						.atZone(ZoneId.systemDefault())
						.toLocalDate()
		);

		YearMonth to = YearMonth.from
				(new Date()
						.toInstant()
						.atZone(ZoneId.systemDefault())
						.toLocalDate()
				);

		edad = ChronoUnit.YEARS.between(from, to);
		return edad;
	}
}
