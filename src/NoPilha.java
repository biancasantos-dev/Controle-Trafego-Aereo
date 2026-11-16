public class NoPilha {
    private EventoLog elemento;
    private NoPilha proximo;

    public NoPilha(EventoLog elemento) {
        this.elemento = elemento;
        this.proximo = null;
    }

    public EventoLog getElemento() {
        return elemento;
    }

    public void setElemento(EventoLog elemento) {
        this.elemento = elemento;
    }

    public NoPilha getProximo() {
        return proximo;
    }

    public void setProximo(NoPilha proximo) {
        this.proximo = proximo;
    }
}
