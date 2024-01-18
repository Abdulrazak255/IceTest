package be.intecbrussel.exception;

public class NoMoreException extends RuntimeException{

    public NoMoreException(String message) {super(message);}

    public NoMoreException(String message, Throwable cause) {super(message, cause);}

}
