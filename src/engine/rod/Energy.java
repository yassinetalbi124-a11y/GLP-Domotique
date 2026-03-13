package engine.rod;

public class Energy implements State {
    private int pourcentage;
    private int valeurMax;

    public Energy(int pourcentage) {
        valeurMax = 100;
        this.pourcentage = pourcentage;
        verifierBornes();
    }

    public void decrease(int pourcentage) {
        this.pourcentage = this.pourcentage - pourcentage;
        verifierBornes();
    }

    public void increase(int pourcentage) {
        this.pourcentage = this.pourcentage + pourcentage;
        verifierBornes();
    }

    public boolean isCritical() {
        return pourcentage <= 20;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    private void verifierBornes() {
        if (pourcentage < 0) {
            pourcentage = 0;
        }

        if (pourcentage > valeurMax) {
            pourcentage = valeurMax;
        }
    }
}