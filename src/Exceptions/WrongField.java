package Exceptions;

/**
 * Exception that throws when value does not math to requirements
 */
public class WrongField extends Error{
    public WrongField(String message){
        super(message);
    }
}

