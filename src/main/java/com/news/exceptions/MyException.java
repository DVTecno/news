package com.news.exceptions;
/**
 * Excepción personalizada para manejar errores específicos del sistema de noticias.
 * Esta excepción extiende la clase base Exception.
 * @version 1.0.0 16/10/2023
 */ 
public class MyException extends Exception{
    /**
     * Constructor que acepta un mensaje de error y lo pasa al constructor de la clase base.
     * @param message Mensaje de error que se mostrará cuando se lance la excepción.
     */
    public MyException(String message) {
        super(message);
    }
}
