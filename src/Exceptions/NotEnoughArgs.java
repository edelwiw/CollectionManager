package Exceptions;

/**
 * Exception that throws when arguments count doest not math command requirements.
 */
public class NotEnoughArgs extends Exception{
    public NotEnoughArgs(String message){
        super(message);
    }
}
