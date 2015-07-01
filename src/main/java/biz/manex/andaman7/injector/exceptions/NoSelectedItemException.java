package biz.manex.andaman7.injector.exceptions;

/**
 * The exception that is raised when an action is performed while an item was supposed to be selected.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://www.a7-software.com)<br/>
 *         Date : 07/03/2015.
 */
public class NoSelectedItemException extends AndamanException {

    public NoSelectedItemException() {
    }

    public NoSelectedItemException(String message) {
        super(message);
    }
}
