package com.andaman7.injector.views;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * An {@link javax.swing.InputVerifier} to check if a form component is not empty.
 *
 * @author Pierre-Yves (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://a7-software.com/)<br/>
 *         Date : 06/02/2015.
 */
public class NoEmptyInputVerifier extends InputVerifier {

    /**
     * Verify if the input component is not empty.
     * @param input the input component
     * @return {@code true} if the input component contains some data, {@code false} otherwise
     */
    @Override
    public boolean verify(JComponent input) {
        return !(input instanceof JTextField) || isValidText((JTextField) input);
    }

    /**
     * Verify if a text field is not empty.
     * @param field the text field
     * @return {@code true} if the text field contains some data, {@code false} otherwise
     */
    protected boolean isValidText(JTextField field) {
        return (field.getText() != null) && !field.getText().trim().isEmpty();
    }
}
