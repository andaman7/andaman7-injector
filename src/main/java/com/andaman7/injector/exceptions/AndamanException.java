package com.andaman7.injector.exceptions;

/**
 * The top level exception of the application.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://www.a7-software.com)<br/>
 *         Date : 07/03/2015.
 */
public class AndamanException extends Exception {

    public AndamanException() {
    }

    public AndamanException(String message) {
        super(message);
    }
    
}
