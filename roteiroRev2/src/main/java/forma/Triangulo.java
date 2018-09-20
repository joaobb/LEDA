package forma;

public class Triangulo implements Forma {

    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        super();
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return (this.base * this.altura) / 2;
    }
}
