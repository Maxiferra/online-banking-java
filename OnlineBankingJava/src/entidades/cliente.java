package entidades;

import java.sql.Date;

public class cliente {
	
	private int id;
	private String dni;
	private String cuil; 
	private String nombre;
	private String apellido;
	private String sexo;
	private int idNacionalidad;
	private Date fechaNacimiento;
	private String direccion;
	private int idLocalidad;
    private int idProvincia;
	private String email;
	private String telefono;
	private int idUsuario;
	private Usuario usuario;
	private boolean eliminado;
	
	private Localidades localidad;
    private Nacionalidades nacionalidad;
    private Provincias provincia;
	
	public cliente() {}
	
	public cliente(int id,String dni, String cuil, String nombre, String apellido, String sexo, int idNacionalidad,
			Date fechaNacimiento, String direccion, int idLocalidad, int idProvincia, String email, String telefono, int idUsuario,Usuario usuario,boolean eliminado,Localidades localidad, Nacionalidades nacionalidad,
            Provincias provincia) {
		super();
	
		this.id = id;
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.idNacionalidad = idNacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.idLocalidad = idLocalidad;
        this.idProvincia = idProvincia;
		this.email = email;
		this.telefono = telefono;	 
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.eliminado = eliminado;
		this.localidad = localidad;
        this.nacionalidad = nacionalidad;
        this.provincia = provincia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public int getIdNacionalidad() {
		return idNacionalidad;
	}

	public void setIdNacionalidad(int idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public Localidades getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidades localidad) {
		this.localidad = localidad;
	}

	public Nacionalidades getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Nacionalidades nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Provincias getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincias provincia) {
		this.provincia = provincia;
	}						
}
