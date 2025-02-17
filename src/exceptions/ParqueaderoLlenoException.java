package exceptions;

public class ParqueaderoLlenoException extends Exception {
    public ParqueaderoLlenoException(String message) {
        super(message);
    }
}