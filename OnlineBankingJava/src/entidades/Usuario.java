package entidades;

public class Usuario {
	
	private int id;
    private int tipoUsuario;
    private String txtUsuario;
    private String password;

    public Usuario() {};
    
    public Usuario(int id, int tipoUsuario, String txtUsuario, String password) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
        this.txtUsuario = txtUsuario;
        this.password = password;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(String txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
