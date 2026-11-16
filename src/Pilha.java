public class Pilha {
    private NoPilha topo;

    public Pilha() {
        this.topo = null;
    }

    public void inserir(EventoLog evento) {
        adicionar(evento);
    }

    public void adicionar(EventoLog evento) {
        NoPilha novo = new NoPilha(evento);
        novo.setProximo(this.topo);
        this.topo = novo;
    }

    public EventoLog excluir() {
        return remover();
    }

    public EventoLog remover() {
        if (this.topo == null) {
            return null;
        }
        EventoLog removido = this.topo.getElemento();
        this.topo = this.topo.getProximo();
        return removido;
    }

    public EventoLog buscar(String trecho) {
        return localizar(trecho);
    }

    public EventoLog localizar(String trecho) {
        NoPilha atual = this.topo;
        while (atual != null) {
            if (atual.getElemento().getMensagem().contains(trecho)) {
                return atual.getElemento();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public void listar() {
        if (this.topo == null) {
            System.out.println("Pilha vazia.");
            return;
        }
        NoPilha atual = this.topo;
        while (atual != null) {
            System.out.println(atual.getElemento());
            atual = atual.getProximo();
        }
    }
}
