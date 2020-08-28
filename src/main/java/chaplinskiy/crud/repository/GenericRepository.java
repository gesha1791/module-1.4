package chaplinskiy.crud.repository;

public interface GenericRepository<T, ID> {
    T getById(ID id);
}
