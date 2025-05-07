public class Refresco {
    private Bebidas tipo;

    public Refresco(Bebidas tipo){
        this.tipo = tipo;
    }
    public Bebidas getTipo(){
        return tipo;
    }
    public double getPrecio(){
        return tipo.getPrecio();
    }
}
