package forma;

public class Circulo implements Forma {

    private double raio;

    public Circulo(double raio) {
        super();
        this.raio = raio;
    }

    @Override
    public double calcularArea() {
        return Math.pow(this.raio, 2) * Math.PI;
    }
}
