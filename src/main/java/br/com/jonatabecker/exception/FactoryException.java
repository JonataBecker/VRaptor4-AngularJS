package br.com.jonatabecker.exception;

/**
 * Exception lançada em falhas de construção de objetos
 *
 * @author Jonata Becker
 */
public class FactoryException extends Exception {

    /**
     * Construtor da exception lançada em falhas de construtor da objetos
     *
     * @param cause
     */
    public FactoryException(Throwable cause) {
        super("Não foi possível criar objeto!", cause);
    }
}
