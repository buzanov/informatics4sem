package parser;

public interface Parser<T> {
    T read(String string);

    String write(T t);
}
