package by.konanaw.patterns.factory;

public class Rupee implements Currency {
    private String name = null;

    public Rupee() {
    }

    public Rupee(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return "Rs";
    }
}
