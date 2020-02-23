package model;


public interface ITask {

    void setState(Class<? extends State> aClass);

    void up(String... args);

    void down(String... args);

    ITask copy();

    void setId(Integer id);


}
