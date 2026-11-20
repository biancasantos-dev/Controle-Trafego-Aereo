public class NoFila {
    private Voo elemento;
    private NoFila proximo;

    public NoFila(Voo elemento) {
        this.elemento = elemento;
        this.proximo = null;
    }

    public Voo getElemento() {
        return elemento;
    }

    public NoFila getProximo() {
        return proximo;
    }

    public void setProximo(NoFila proximo) {
        this.proximo = proximo;
    }
}
