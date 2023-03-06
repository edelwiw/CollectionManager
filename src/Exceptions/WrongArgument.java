package Exceptions;

/**
 * Exception that throws when command argument value does not math type.
 */
public class WrongArgument extends Exception{
    public WrongArgument(String message){
        super(message);
    }
}
