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
        auditoria.adicionar(new EventoLog("Carga inicial de voos cadastrada com sucesso."));

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== GESTÃO DE TRÁFEGO AÉREO ===");
            System.out.println("1 - Cadastrar voo programado");
            System.out.println("2 - Consultar voo programado");
            System.out.println("3 - Cancelar voo programado");
            System.out.println("4 - Relatório de voos programados");
            System.out.println("5 - Autorizar voo para a pista");
            System.out.println("6 - Liberar decolagem na pista");
            System.out.println("7 - Listar aeronaves na pista");
            System.out.println("8 - Consultar aeronave na pista");
            System.out.println("9 - Exibir histórico de auditoria");
            System.out.println("10 - Consultar auditoria por termo");
            System.out.println("11 - Descartar último registro de auditoria");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());

                switch (opcao) {
                    case 1:
                        System.out.print("Código do voo (número): ");
                        int codigoNovo = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Destino: ");
                        String destinoNovo = scanner.nextLine().trim();

                        if (arvoreVoos.localizar(codigoNovo) != null) {
                            System.out.println("Erro: Já existe um voo cadastrado com esse código.");
                        } else {
                            arvoreVoos.inserir(new Voo(codigoNovo, destinoNovo));
                            auditoria.adicionar(new EventoLog("Voo " + codigoNovo + " (" + destinoNovo + ") programado com sucesso."));
                            System.out.println("Voo cadastrado com sucesso!");
                        }
                        break;

                    case 2:
                        System.out.print("Código do voo a consultar: ");
                        int codigoBusca = Integer.parseInt(scanner.nextLine().trim());
                        Voo encontrado = arvoreVoos.localizar(codigoBusca);
                        if (encontrado != null) {
                            System.out.println("Voo encontrado: " + encontrado);
                        } else {
                            System.out.println("Voo não encontrado.");
                        }
                        break;

                    case 3:
                        System.out.print("Código do voo a cancelar: ");
                        int codigoCancelado = Integer.parseInt(scanner.nextLine().trim());
                        Voo vooCancelado = arvoreVoos.localizar(codigoCancelado);
                        if (arvoreVoos.remover(codigoCancelado)) {
                            String info = (vooCancelado != null) ? " (" + vooCancelado.getDestino() + ")" : "";
                            auditoria.adicionar(new EventoLog("Voo " + codigoCancelado + info + " cancelado no sistema."));
                            System.out.println("Voo " + codigoCancelado + " cancelado com sucesso!");
                        } else {
                            System.out.println("Voo não encontrado para cancelamento.");
                        }
                        break;

                    case 4:
                        System.out.println("\n--- Relatório de Voos Programados ---");
                        arvoreVoos.percursos();
                        break;

                    case 5:
                        System.out.print("Código do voo programado para autorizar: ");
                        int codigoPista = Integer.parseInt(scanner.nextLine().trim());
                        Voo vooAutorizado = arvoreVoos.localizar(codigoPista);
                        if (vooAutorizado != null) {
                            if (pista.localizar(codigoPista) != null) {
                                System.out.println("Aeronave já se encontra na fila da pista.");
                            } else {
                                pista.adicionar(vooAutorizado);
                                auditoria.adicionar(new EventoLog("Voo " + codigoPista + " (" + vooAutorizado.getDestino() + ") posicionado na fila da pista."));
                                System.out.println("Voo " + codigoPista + " encaminhado para a pista!");
                            }
                        } else {
                            System.out.println("Voo não encontrado no cadastro de voos programados.");
                        }
                        break;

                    case 6:
                        Voo decolado = pista.remover();
                        if (decolado != null) {
                            auditoria.adicionar(new EventoLog("Voo " + decolado.getCodigo() + " (" + decolado.getDestino() + ") realizou decolagem com sucesso."));
                            System.out.println("Pista liberada! Aeronave decolou: " + decolado);
                        } else {
                            System.out.println("Pista vazia. Nenhuma aeronave aguardando liberação.");
                        }
                        break;

                    case 7:
                        System.out.println("\n--- Aeronaves na Pista de Decolagem ---");
                        pista.listar();
                        break;

                    case 8:
                        System.out.print("Código do voo a consultar na pista: ");
                        int codigoFila = Integer.parseInt(scanner.nextLine().trim());
                        Voo naPista = pista.localizar(codigoFila);
                        if (naPista != null) {
                            System.out.println("Aeronave localizada na pista: " + naPista);
                        } else {
                            System.out.println("Aeronave não está na fila da pista.");
                        }
                        break;

                    case 9:
                        System.out.println("\n--- Histórico de Auditoria ---");
                        auditoria.listar();
                        break;

                    case 10:
                        System.out.print("Termo para buscar no histórico: ");
                        String termo = scanner.nextLine().trim();
                        EventoLog logEncontrado = auditoria.localizar(termo);
                        if (logEncontrado != null) {
                            System.out.println("Registro localizado: " + logEncontrado);
                        } else {
                            System.out.println("Nenhum registro encontrado com o termo informado.");
                        }
                        break;

                    case 11:
                        EventoLog logRemovido = auditoria.remover();
                        if (logRemovido != null) {
                            System.out.println("Registro descartado da auditoria: " + logRemovido);
                        } else {
                            System.out.println("Histórico de auditoria vazio. Nenhum registro para descartar.");
                        }
                        break;

                    case 0:
                        System.out.println("Encerrando o sistema de tráfego aéreo...");
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
            }
        }

        scanner.close();
    }
}
