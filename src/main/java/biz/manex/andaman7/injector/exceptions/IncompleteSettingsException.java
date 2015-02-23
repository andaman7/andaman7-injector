/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manex.andaman7.injector.exceptions;

/**
 *
 * @author Pierre-Yves
 */
public class IncompleteSettingsException extends Exception {

    public IncompleteSettingsException() {
    }

    public IncompleteSettingsException(String message) {
        super(message);
    }
}
