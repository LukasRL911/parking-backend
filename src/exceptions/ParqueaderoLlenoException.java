package exceptions;

public class ParqueaderoLlenoException extends Exception {
    
    public ParqueaderoLlenoException(String mensaje) {
        super(mensaje);
    }
}
