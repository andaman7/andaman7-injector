/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manex.andaman7.injector.views;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author Pierre-Yves
 */
public class NoEmptyInputVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        return !(input instanceof JTextField) || isValidText((JTextField) input);
    }

    protected boolean isValidText(JTextField field) {
        return field.getText() != null && !field.getText().trim().isEmpty();
    }
}
