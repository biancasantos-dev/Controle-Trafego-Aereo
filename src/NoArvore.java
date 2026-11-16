public class NoArvore {
    private Voo conteudo;
    private NoArvore esq;
    private NoArvore dir;

    public NoArvore(Voo conteudo) {
        this.conteudo = conteudo;
        this.esq = null;
        this.dir = null;
    }

    public boolean eFolha() {
        return (this.esq == null && this.dir == null);
    }

    public Voo getConteudo() {
        return conteudo;
    }

    public void setConteudo(Voo conteudo) {
        this.conteudo = conteudo;
    }

    public NoArvore getEsq() {
        return esq;
    }

    public void setEsq(NoArvore esq) {
        this.esq = esq;
    }

    public NoArvore getDir() {
        return dir;
    }

    public void setDir(NoArvore dir) {
        this.dir = dir;
    }
}
