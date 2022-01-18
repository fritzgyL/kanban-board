package repository;

import java.io.Serializable;

public interface RefreshingDao<T, PK extends Serializable> {
	void refresh(T t);

}
