package br.com.jonatabecker.db;

import java.util.List;

public interface Repository<E extends Entity, PK extends Pk> {

    public void write(E entity);

    public E search(PK pk);

    public List<E> search();

}
