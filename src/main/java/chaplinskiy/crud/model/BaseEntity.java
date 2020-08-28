package chaplinskiy.crud.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseEntity {
    private int id;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public BaseEntity() {
        this.id = atomicInteger.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
