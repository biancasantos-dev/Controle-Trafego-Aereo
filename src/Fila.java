public class Fila {
    private NoFila inicio;
    private NoFila fim;

    public Fila() {
        this.inicio = null;
        this.fim = null;
    }

    public void inserir(Voo voo) {
        adicionar(voo);
    }

    public void adicionar(Voo voo) {
        NoFila novo = new NoFila(voo);
        if (this.inicio == null) {
            this.inicio = novo;
            this.fim = novo;
        } else {
            this.fim.setProximo(novo);
            this.fim = novo;
        }
    }

    public Voo excluir() {
        return remover();
    }

    public Voo remover() {
        if (this.inicio == null) {
            return null;
        }
        Voo atendido = this.inicio.getElemento();
        this.inicio = this.inicio.getProximo();
        if (this.inicio == null) {
            this.fim = null;
        }
        return atendido;
    }

    public Voo buscar(int codigo) {
        return localizar(codigo);
    }

    public Voo localizar(int codigo) {
        NoFila atual = this.inicio;
        while (atual != null) {
            if (atual.getElemento().getCodigo() == codigo) {
                return atual.getElemento();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public void listar() {
        if (this.inicio == null) {
            System.out.println("Fila vazia.");
            return;
        }
        NoFila atual = this.inicio;
        while (atual != null) {
            System.out.println(atual.getElemento());
            atual = atual.getProximo();
        }
    }
}
