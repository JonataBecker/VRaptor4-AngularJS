package br.com.jonatabecker.db;

import java.util.List;

/**
 * Classe responsável pelo gerenciamento de entidades da base de dados
 *
 * @author Jonata Becker
 * @param <E>
 * @param <PK>
 */
public interface Repository<E extends Entity, PK extends Pk> {

    /**
     * Executa a gravação de dados
     *
     * @param entity Dados da entidade
     */
    public void write(E entity);

    /**
     * Deleta dados
     *
     * @param pk Chave
     */
    public void delete(PK pk);

    /**
     * Busca informações da entidade de dados
     *
     * @param pk Chave
     * @return E
     */
    public E search(PK pk);
    
    /**
     * Retorna lista de dados da entidade de dados
     *
     * @return {@code List<E>}
     */
    public List<E> search();

}
