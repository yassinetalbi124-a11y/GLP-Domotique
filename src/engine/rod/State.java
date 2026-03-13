package engine.rod;

public interface State {
    void diminuer(int pourcentage);
    void augmenter(int pourcentage);
    boolean estCritique();
    int getPourcentage();
}