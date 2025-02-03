package excepciones;

import java.io.IOException;

public class UsuarioRepetidoException extends IOException{

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "El nombre de usuario ya se encuentra en la BD,Por favor elige otro";
	}

}
