public enum Bebidas {
    COCA_COLA(1.50), PEPSI(1.40), FANTA(1.25), SPRITE(1.30), AQUARIUS(1.15), NESTEA(1.10);
    private final double precio;

    Bebidas(double precio){
        this.precio = precio;
    }
    public double getPrecio(){
        return precio;
    }
}
