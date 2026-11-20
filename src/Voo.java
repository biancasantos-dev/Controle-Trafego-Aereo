public class Voo {
    private int codigo;
    private String destino;

    public Voo(int codigo, String destino) {
        this.codigo = codigo;
        this.destino = destino;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDestino() {
        return destino;
    }

    @Override
    public String toString() {
        return "Voo " + codigo + " (" + destino + ")";
    }
}
