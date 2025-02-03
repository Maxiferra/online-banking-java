package entidades;

public class Localidades {
	 private int id;
	    private String nombre;
	    private int idProvincia;
	    private Provincias provincia;
	    
	    public Localidades() {};
	    
		public Localidades(int id, String nombre, int idProvincia,Provincias provincia) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.idProvincia = idProvincia;
			this.provincia = provincia;
			
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public int getIdProvincia() {
			return idProvincia;
		}
		public void setIdProvincia(int idProvincia) {
			this.idProvincia = idProvincia;
		}

		public Provincias getProvincia() {
			return provincia;
		}

		public void setProvincia(Provincias provincia) {
			this.provincia = provincia;
		}			   	    
}

