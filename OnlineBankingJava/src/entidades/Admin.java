package entidades;

public class Admin {
	
	private String dni;
	private String cuil; 
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private int Estado;
	private int idUsuario;
	private Usuario usuario;
	
	public Admin() {}
	
	public Admin(String dni, String cuil, String nombre, String apellido,String email,String password,
				 String telefono,int Estado, int idUsuario,Usuario usuario){
		super();
	
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;	
		this.Estado = Estado;
		this.idUsuario = idUsuario;
		this.usuario = usuario;
	}

	
	public String getDni() {
		return dni;
	}
	public void setDni(String string) {
		this.dni = string;
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
	public int getEstado() {
		return Estado;
	}
	public void setEstado(int Estado) {
		this.Estado = Estado;
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
	
}
