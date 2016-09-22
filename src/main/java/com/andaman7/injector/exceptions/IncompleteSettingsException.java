package com.andaman7.injector.exceptions;

/**
 * The exception that is raised when a form is posted and when the settings were incomplete.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://www.a7-software.com)<br/>
 *         Date : 07/03/2015.
 */
public class IncompleteSettingsException extends AndamanException {

    public IncompleteSettingsException() {
    }

    public IncompleteSettingsException(String message) {
        super(message);
    }
}
