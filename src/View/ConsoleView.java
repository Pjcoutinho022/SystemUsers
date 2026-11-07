package View;

import Controller.UsuarioController;
import Models.Attendant;
import Models.Manager;
import Models.Salesman;
import Models.SystemUsers;

import java.util.Scanner;

public class ConsoleView {

    public void startSystem() {
        UsuarioController usuarioController = new UsuarioController();
        UserMenu menus = new UserMenu();
        Scanner scanner = new Scanner(System.in);

        boolean rodando = true;

        while (rodando) {
            menus.menuInicial();
            System.out.print("Informe a opção desejada: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1: // CADASTRO
                    boolean menuCadastro = true;
                    do {
                        menus.menuCadastro();
                        System.out.print("Informe a opção desejada: ");
                        int opcaoCadastro = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcaoCadastro) {
                            case 1: // Cadastrar Gerente
                                System.out.println("----- CADASTRO DE GERENTE -----");
                                System.out.print("Informe o Nome: ");
                                String nomeGerente = scanner.nextLine();
                                System.out.print("Informe o Email: ");
                                String emailGerente = scanner.nextLine();
                                if (!emailGerente.contains("@")) {
                                    System.out.println("Email Inválido! O e-mail deve conter '@'.");
                                    break;
                                }
                                System.out.print("Informe a Senha: ");
                                String senhaGerente = scanner.nextLine();

                                if (usuarioController.cadastrarGerente(nomeGerente, emailGerente, senhaGerente)) {
                                    System.out.println("Gerente cadastrado com sucesso!");
                                } else {
                                    System.out.println("Erro: Já existe um usuário cadastrado com esse e-mail.");
                                }
                                break;

                            case 2: // Cadastrar Vendedor
                                System.out.println("----- CADASTRO DE VENDEDOR -----");
                                System.out.print("Informe o Nome: ");
                                String nomeVendedor = scanner.nextLine();
                                System.out.print("Informe o Email: ");
                                String emailVendedor = scanner.nextLine();
                                if (!emailVendedor.contains("@")) {
                                    System.out.println("Email Inválido! O e-mail deve conter '@'.");
                                    break;
                                }
                                System.out.print("Informe a Senha: ");
                                String senhaVendedor = scanner.nextLine();

                                if (usuarioController.cadastrarVendedor(nomeVendedor, emailVendedor, senhaVendedor)) {
                                    System.out.println("Vendedor cadastrado com sucesso!");
                                } else {
                                    System.out.println("Erro: Já existe um usuário cadastrado com esse e-mail.");
                                }
                                break;

                            case 3: // Cadastrar Atendente
                                System.out.println("----- CADASTRO DE ATENDENTE -----");
                                System.out.print("Informe o Nome: ");
                                String nomeAtendente = scanner.nextLine();
                                System.out.print("Informe o Email: ");
                                String emailAtendente = scanner.nextLine();
                                if (!emailAtendente.contains("@")) {
                                    System.out.println("Email Inválido! O e-mail deve conter '@'.");
                                    break;
                                }
                                System.out.print("Informe a Senha: ");
                                String senhaAtendente = scanner.nextLine();

                                if (usuarioController.cadastrarAtendente(nomeAtendente, emailAtendente, senhaAtendente)) {
                                    System.out.println("Atendente cadastrado com sucesso!");
                                } else {
                                    System.out.println("Erro: Já existe um usuário cadastrado com esse e-mail.");
                                }
                                break;

                            case 4: // Voltar
                                menuCadastro = false;
                                break;

                            default:
                                System.out.println("Opção inválida! Tente novamente.");
                                break;
                        }

                    } while (menuCadastro);
                    break;

                case 2: // LOGIN
                    System.out.println("----- LOGIN -----");
                    System.out.print("Informe o Email: ");
                    String emailLogin = scanner.nextLine();
                    System.out.print("Informe a Senha: ");
                    String senhaLogin = scanner.nextLine();

                    if (usuarioController.realizarLogin(emailLogin, senhaLogin)) {
                        System.out.println("Login efetuado com sucesso!");
                        SystemUsers usuarioAtual = usuarioController.getUsuarioLogado();
                        boolean userLogado = true;

                        if (usuarioAtual instanceof Salesman) {
                            Salesman salesman = (Salesman) usuarioAtual;
                            System.out.println("\n--- BEM VINDO(A) " + salesman.getName().toUpperCase() + " ---");
                            do {
                                menus.menuSalesman();
                                System.out.print("Informe a opção desejada: ");
                                int opcaoSalesman = scanner.nextInt();
                                scanner.nextLine();

                                switch (opcaoSalesman) {
                                    case 1: // Realizar venda
                                        salesman.makeASale();
                                        System.out.println("Venda realizada com sucesso!");
                                        break;

                                    case 2: // Consultar vendas pessoais
                                        System.out.println("---- CONSULTAR MINHAS VENDAS ----\n");
                                        for (String linha : salesman.consultSales()) {
                                            System.out.println(linha);
                                        }
                                        break;

                                    case 3: // Alterar dados
                                        System.out.print("Insira o novo nome: ");
                                        String novoNome = scanner.nextLine();
                                        System.out.print("Insira o novo email: ");
                                        String novoEmail = scanner.nextLine();
                                        if (salesman.changeData(novoNome, novoEmail)) {
                                            System.out.println("Dados alterados com sucesso!");
                                        } else {
                                            System.out.println("Erro! Os dados informados são iguais aos atuais.");
                                        }
                                        break;

                                    case 4: // Alterar senha
                                        System.out.print("Insira a senha antiga: ");
                                        String senhaAntiga = scanner.nextLine();
                                        System.out.print("Insira a nova senha: ");
                                        String senhaNova = scanner.nextLine();
                                        if (salesman.changePassword(senhaAntiga, senhaNova)) {
                                            System.out.println("Senha alterada com sucesso!");
                                        } else {
                                            System.out.println("Erro! Senha antiga incorreta ou nova senha igual à antiga.");
                                        }
                                        break;

                                    case 5: // Logoff
                                        System.out.println("Sessão encerrada! Até breve.");
                                        usuarioController.realizarLogoff();
                                        userLogado = false;
                                        break;

                                    default:
                                        System.out.println("Opção inválida!");
                                        break;
                                }
                            } while (userLogado);

                        } else if (usuarioAtual instanceof Manager) {
                            Manager manager = (Manager) usuarioAtual;
                            System.out.println("\n--- BEM VINDO(A) " + manager.getName().toUpperCase() + " (GERENTE) ---");
                            do {
                                menus.menuManager();
                                System.out.print("Informe a opção desejada: ");
                                int opcaoManager = scanner.nextInt();
                                scanner.nextLine();

                                switch (opcaoManager) {
                                    case 1: // Gerar relatorio financeiro
                                        System.out.println("---- RELATÓRIO FINANCEIRO ----\n");
                                        for (String linha : usuarioController.gerarRelatorioFinanceiro(manager)) {
                                            System.out.println(linha);
                                        }
                                        break;

                                    case 2: // Consultar vendas gerais
                                        System.out.println("---- CONSULTA GERAL DE VENDAS ----\n");
                                        for (String linha : usuarioController.gerarConsultaVendasGerais(manager)) {
                                            System.out.println(linha);
                                        }
                                        break;

                                    case 3: // Alterar dados
                                        System.out.print("Insira o novo nome: ");
                                        String name = scanner.nextLine();
                                        System.out.print("Insira o novo email: ");
                                        String email = scanner.nextLine();
                                        if (manager.changeData(name, email)) {
                                            System.out.println("Dados alterados com sucesso!");
                                        } else {
                                            System.out.println("Erro! Os dados informados são iguais aos atuais.");
                                        }
                                        break;

                                    case 4: // Alterar senha
                                        System.out.print("Insira a senha antiga: ");
                                        String passwordAntiga = scanner.nextLine();
                                        System.out.print("Insira a nova senha: ");
                                        String passwordNova = scanner.nextLine();
                                        if (manager.changePassword(passwordAntiga, passwordNova)) {
                                            System.out.println("Senha alterada com sucesso!");
                                        } else {
                                            System.out.println("Erro! Senha antiga incorreta ou nova senha igual à antiga.");
                                        }
                                        break;

                                    case 5: // Logoff
                                        System.out.println("Sessão encerrada! Até breve.");
                                        usuarioController.realizarLogoff();
                                        userLogado = false;
                                        break;

                                    default:
                                        System.out.println("Opção inválida!");
                                        break;
                                }
                            } while (userLogado);

                        } else if (usuarioAtual instanceof Attendant) {
                            Attendant attendant = (Attendant) usuarioAtual;
                            System.out.println("\n--- BEM VINDO(A) " + attendant.getName().toUpperCase() + " ---");
                            do {
                                menus.menuAttendant();
                                System.out.print("Informe a opção desejada: ");
                                int opcaoAttendant = scanner.nextInt();
                                scanner.nextLine();

                                switch (opcaoAttendant) {
                                    case 1: // Receber pagamento
                                        System.out.print("Informe o valor do pagamento recebido: R$ ");
                                        double pagamento = scanner.nextDouble();
                                        scanner.nextLine();
                                        double saldo = attendant.receivePayments(pagamento);
                                        if (saldo > 0) {
                                            System.out.printf("Pagamento recebido! Saldo atual em caixa: R$ %.2f\n", saldo);
                                        } else {
                                            System.out.println("Valor inválido! Insira um valor maior que zero.");
                                        }
                                        break;

                                    case 2: // Fechar caixa
                                        System.out.println("---- FECHAMENTO DE CAIXA ----");
                                        System.out.printf("Saldo total fechado: R$ %.2f\n", attendant.getCashOnHand());
                                        attendant.closerRegister();
                                        System.out.println("Caixa fechado com sucesso! Saldo zerado.");
                                        break;

                                    case 3: // Alterar dados
                                        System.out.print("Insira o novo nome: ");
                                        String nomeAtt = scanner.nextLine();
                                        System.out.print("Insira o novo email: ");
                                        String emailAtt = scanner.nextLine();
                                        if (attendant.changeData(nomeAtt, emailAtt)) {
                                            System.out.println("Dados alterados com sucesso!");
                                        } else {
                                            System.out.println("Erro! Os dados informados são iguais aos atuais.");
                                        }
                                        break;

                                    case 4: // Alterar senha
                                        System.out.print("Insira a senha antiga: ");
                                        String passAntiga = scanner.nextLine();
                                        System.out.print("Insira a nova senha: ");
                                        String passNova = scanner.nextLine();
                                        if (attendant.changePassword(passAntiga, passNova)) {
                                            System.out.println("Senha alterada com sucesso!");
                                        } else {
                                            System.out.println("Erro! Senha antiga incorreta ou nova senha igual à antiga.");
                                        }
                                        break;

                                    case 5: // Logoff
                                        System.out.println("Sessão encerrada! Até breve.");
                                        usuarioController.realizarLogoff();
                                        userLogado = false;
                                        break;

                                    default:
                                        System.out.println("Opção inválida!");
                                        break;
                                }
                            } while (userLogado);
                        }

                    } else {
                        System.out.println("Email ou senha incorretos!");
                    }
                    break;

                case 3: // SAIR
                    System.out.println("Obrigado por utilizar o sistema! Até logo.");
                    rodando = false;
                    break;

                default:
                    System.out.println("Opção inválida! Escolha entre 1, 2 ou 3.");
                    break;
            }
        }
        scanner.close();
    }
}
