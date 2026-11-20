public class Pilha {
    private NoPilha topo;

    public Pilha() {
        this.topo = null;
    }

    public void adicionar(EventoLog evento) {
        NoPilha novo = new NoPilha(evento);
        novo.setProximo(this.topo);
        this.topo = novo;
    }

    public EventoLog remover() {
        if (this.topo == null) {
            return null;
        }
        EventoLog removido = this.topo.getElemento();
        this.topo = this.topo.getProximo();
        return removido;
    }

    public EventoLog localizar(String trecho) {
        if (trecho == null) {
            return null;
        }
        NoPilha atual = this.topo;
        while (atual != null) {
            if (atual.getElemento().getMensagem().toLowerCase().contains(trecho.toLowerCase())) {
                return atual.getElemento();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public void listar() {
        if (this.topo == null) {
            System.out.println("Histórico de auditoria vazio.");
            return;
        }
        NoPilha atual = this.topo;
        while (atual != null) {
            System.out.println(atual.getElemento());
            atual = atual.getProximo();
        }
    }
}
