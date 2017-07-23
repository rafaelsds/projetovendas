package utility;

public class VerificaTipo {
    
public static boolean campoNumerico(String campo){		
		return campo.matches("[0-9]{"+campo.length()+"}");
    }
    
}
