package alebolo.rabdomante.core;

public class Defect extends RuntimeException {
    public Defect(String s) { super(s); }
    public Defect(String s, Throwable t) { super(s, t); }
}
