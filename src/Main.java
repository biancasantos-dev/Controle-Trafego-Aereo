import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreVoos = new ArvoreBinaria();
        Fila pista = new Fila();
        Pilha auditoria = new Pilha();
        Scanner scanner = new Scanner(System.in);

        arvoreVoos.inserir(new Voo(105, "São Paulo"));
        arvoreVoos.inserir(new Voo(102, "Rio de Janeiro"));
        arvoreVoos.inserir(new Voo(108, "Salvador"));
        arvoreVoos.inserir(new Voo(101, "Brasília"));
        arvoreVoos.inserir(new Voo(104, "Belo Horizonte"));
        auditoria.adicionar(new EventoLog("Carga inicial de voos cadastrada na ABB."));

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== CONTROLE DE TRÁFEGO AÉREO ===");
            System.out.println("1 - Cadastrar voo programado ");
            System.out.println("2 - Consultar voo programado ");
            System.out.println("3 - Cancelar voo programado ");
            System.out.println("4 - Exibir percursos");
            System.out.println("5 - Autorizar voo para a pista ");
            System.out.println("6 - Liberar pista / Decolagem");
            System.out.println("7 - Listar aeronaves na pista ");
            System.out.println("8 - Consultar aeronave na pista");
            System.out.println("9 - Listar histórico de auditoria ");
            System.out.println("10 - Consultar auditoria por termo ");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Código do voo (número): ");
                    int codigoNovo = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Destino: ");
                    String destinoNovo = scanner.nextLine().trim();

                    if (arvoreVoos.buscar(codigoNovo) != null) {
                        System.out.println("Erro: Já existe um voo cadastrado com esse código.");
                    } else {
                        arvoreVoos.inserir(new Voo(codigoNovo, destinoNovo));
                        auditoria.adicionar(new EventoLog("Voo " + codigoNovo + " programado com sucesso."));
                        System.out.println("Voo cadastrado com sucesso!");
                    }
                    break;

                case 2:
                    System.out.print("Código do voo a consultar: ");
                    int codigoBusca = Integer.parseInt(scanner.nextLine().trim());
                    Voo encontrado = arvoreVoos.buscar(codigoBusca);
                    if (encontrado != null) {
                        System.out.println("Voo encontrado: " + encontrado);
                    } else {
                        System.out.println("Voo não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Código do voo a cancelar: ");
                    int codigoCancelado = Integer.parseInt(scanner.nextLine().trim());
                    if (arvoreVoos.remover(codigoCancelado)) {
                        auditoria.adicionar(new EventoLog("Voo " + codigoCancelado + " cancelado e removido da ABB."));
                        System.out.println("Voo " + codigoCancelado + " cancelado com sucesso!");
                    } else {
                        System.out.println("Voo não encontrado para cancelamento.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Percursos da Árvore Binária de Busca ---");
                    arvoreVoos.percursos();
                    break;

                case 5:
                    System.out.print("Código do voo programado para autorizar: ");
                    int codigoPista = Integer.parseInt(scanner.nextLine().trim());
                    Voo vooAutorizado = arvoreVoos.buscar(codigoPista);
                    if (vooAutorizado != null) {
                        pista.adicionar(vooAutorizado);
                        auditoria.adicionar(new EventoLog("Voo " + codigoPista + " posicionado na fila da pista."));
                        System.out.println("Voo " + codigoPista + " encaminhado para a pista!");
                    } else {
                        System.out.println("Voo não encontrado nos cadastros da ABB.");
                    }
                    break;

                case 6:
                    Voo decolado = pista.remover();
                    if (decolado != null) {
                        auditoria.adicionar(new EventoLog("Voo " + decolado.getCodigo() + " realizou decolagem com sucesso."));
                        System.out.println("Pista liberada! Aeronave decolou: " + decolado);
                    } else {
                        System.out.println("Pista vazia. Nenhuma aeronave aguardando liberação.");
                    }
                    break;

                case 7:
                    System.out.println("\n--- Fila da Pista de Decolagem ---");
                    pista.listar();
                    break;

                case 8:
                    System.out.print("Código do voo a consultar na pista: ");
                    int codigoFila = Integer.parseInt(scanner.nextLine().trim());
                    Voo naPista = pista.buscar(codigoFila);
                    if (naPista != null) {
                        System.out.println("Aeronave localizada na pista: " + naPista);
                    } else {
                        System.out.println("Aeronave não está na fila da pista.");
                    }
                    break;

                case 9:
                    System.out.println("\n--- Pilha de Auditoria (Últimos Eventos no Topo) ---");
                    auditoria.listar();
                    break;

                case 10:
                    System.out.print("Termo para buscar no histórico: ");
                    String termo = scanner.nextLine().trim();
                    EventoLog logEncontrado = auditoria.buscar(termo);
                    if (logEncontrado != null) {
                        System.out.println("Registro localizado: " + logEncontrado);
                    } else {
                        System.out.println("Nenhum registro encontrado com o termo informado.");
                    }
                    break;

                case 0:
                    System.out.println("Encerrando o sistema de tráfego aéreo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}
