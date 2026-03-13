package engine.rod;

public interface State {
    void decrease(int pourcentage);
    void increase(int pourcentage);
    boolean isCritical();
    int getPourcentage();
}