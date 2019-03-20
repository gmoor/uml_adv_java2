package edu.garyMooradian.advancedJava.exceptions;

/**
 *  Used to signal invalid XML or other JAXB related issues.
 *  
 *  GM- This exception already exists. But we create our own (overload)
 *  so that we can provide our own constructor, which will have more detail
 */
public class InvalidXMLException extends Exception{

    /**
     * Constructs a new exception with the specified detail message,
     * cause, suppression enabled or disabled, and writable stack
     * trace enabled or disabled.
     *
     * @param message            the detail message.
     * @param cause              the cause.  (A {@code null} value is permitted,
     *                           and indicates that the cause is nonexistent or unknown.)
     */
	
    public InvalidXMLException(String message, Throwable cause) {
        super(message, cause);
    }
}
