public class ArvoreBinaria {
    private NoArvore raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(Voo voo) {
        NoArvore novo = new NoArvore(voo);
        if (this.raiz == null) {
            this.raiz = novo;
        } else {
            inserir(null, this.raiz, novo);
        }
    }

    private void inserir(NoArvore pai, NoArvore atual, NoArvore no) {
        if (atual == null) {
            if (no.getConteudo().getCodigo() < pai.getConteudo().getCodigo()) {
                pai.setEsq(no);
            } else {
                pai.setDir(no);
            }
        } else {
            if (no.getConteudo().getCodigo() < atual.getConteudo().getCodigo()) {
                inserir(atual, atual.getEsq(), no);
            } else if (no.getConteudo().getCodigo() > atual.getConteudo().getCodigo()) {
                inserir(atual, atual.getDir(), no);
            }
        }
    }

    public Voo localizar(int codigo) {
        NoArvore atual = this.raiz;
        while (atual != null) {
            if (codigo == atual.getConteudo().getCodigo()) {
                return atual.getConteudo();
            } else if (codigo < atual.getConteudo().getCodigo()) {
                atual = atual.getEsq();
            } else {
                atual = atual.getDir();
            }
        }
        return null;
    }

    public boolean remover(int codigo) {
        if (this.raiz == null) {
            return false;
        }

        NoArvore pai = null;
        NoArvore atual = this.raiz;

        while (atual != null && atual.getConteudo().getCodigo() != codigo) {
            pai = atual;
            if (codigo < atual.getConteudo().getCodigo()) {
                atual = atual.getEsq();
            } else {
                atual = atual.getDir();
            }
        }

        if (atual == null) {
            return false;
        }

        if (atual.eFolha()) {
            if (atual == this.raiz) {
                this.raiz = null;
            } else if (pai.getEsq() == atual) {
                pai.setEsq(null);
            } else {
                pai.setDir(null);
            }
            return true;
        }

        if (atual.getEsq() == null || atual.getDir() == null) {
            NoArvore filho = (atual.getEsq() != null) ? atual.getEsq() : atual.getDir();
            if (atual == this.raiz) {
                this.raiz = filho;
            } else if (pai.getEsq() == atual) {
                pai.setEsq(filho);
            } else {
                pai.setDir(filho);
            }
            return true;
        }

        NoArvore paiMaior = atual;
        NoArvore maiorEsq = atual.getEsq();

        while (maiorEsq.getDir() != null) {
            paiMaior = maiorEsq;
            maiorEsq = maiorEsq.getDir();
        }

        atual.setConteudo(maiorEsq.getConteudo());

        if (paiMaior == atual) {
            paiMaior.setEsq(maiorEsq.getEsq());
        } else {
            paiMaior.setDir(maiorEsq.getEsq());
        }

        return true;
    }

    public void percursos() {
        if (this.raiz == null) {
            System.out.println("Nenhum voo cadastrado.");
            return;
        }

        System.out.println("In-Ordem (código crescente):");
        inOrdem(this.raiz);
        System.out.println("\n\nPré-Ordem:");
        preOrdem(this.raiz);
        System.out.println("\n\nPós-Ordem:");
        posOrdem(this.raiz);
        System.out.println();
    }

    public void inOrdem(NoArvore no) {
        if (no != null) {
            inOrdem(no.getEsq());
            System.out.print(no.getConteudo() + "  ");
            inOrdem(no.getDir());
        }
    }

    public void preOrdem(NoArvore no) {
        if (no != null) {
            System.out.print(no.getConteudo() + "  ");
            preOrdem(no.getEsq());
            preOrdem(no.getDir());
        }
    }

    public void posOrdem(NoArvore no) {
        if (no != null) {
            posOrdem(no.getEsq());
            posOrdem(no.getDir());
            System.out.print(no.getConteudo() + "  ");
        }
    }
}
