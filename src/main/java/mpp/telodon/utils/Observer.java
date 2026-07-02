package mpp.telodon.utils;

public interface Observer<E extends Event> {
    void update(E e);

}
