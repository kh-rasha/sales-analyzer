package org.example;



/**  Kastas när en order är tom och därför ogiltig för vissa operationer. */
public class EmptyOrderException extends RuntimeException {
    public EmptyOrderException(String message) { super(message); }
}
