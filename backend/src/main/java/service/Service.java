package service;

public interface Service<T> {

	public Iterable<T> getAll();

	public T getById(long id);

	public T save(T t);

	public void delete(long id);

	public T update(T t);

}
