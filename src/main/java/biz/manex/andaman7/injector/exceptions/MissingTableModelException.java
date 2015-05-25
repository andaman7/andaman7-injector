package biz.manex.andaman7.injector.exceptions;

/**
 * The exception that is raised when a table model is missing when we try to manipulate the data of the table.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://www.a7-software.com)<br/>
 *         Date : 07/03/2015.
 */
public class MissingTableModelException extends InjectorException {

    public MissingTableModelException() {
    }

    public MissingTableModelException(String message) {
        super(message);
    }
}
