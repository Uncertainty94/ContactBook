package ru.reksoft.lab.exceptions;

import java.io.PrintStream;

/**
 * Created by mishanin on 20.04.2016.
 */
public abstract class InputSpellException extends Exception{


    public InputSpellException(String message) {
        super(message);
    }
}
