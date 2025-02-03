package excepciones;

public class Validaciones {

	public Validaciones() {
		// TODO Auto-generated constructor stub
	}
	
    public boolean isNumeroPositivoI(String string) {
    	if(string == null || string.trim().isEmpty()|| !string.matches("[0-9,.]+")){
    		return false;
    	}
    	else {
    		if(Integer.parseInt(string)<0) {
    			return false;
    		}
    		return true;
    	}
    }
    
	
    public boolean isNumeroPositivoD(String string) {
    	if(string == null || string.trim().isEmpty()|| !string.matches("[0-9,.]+")){
    		return false;
    	}
    	else {
    		if(Double.parseDouble(string)<0) {
    			return false;
    		}
    		return true;
    	}
    }
    
    
    public boolean CBUFormatOK(String string) {
    	if(string == null || string.trim().isEmpty()|| !string.matches("[0-9]+")){
    		return false;
    	}
    	return true;
    }

}
