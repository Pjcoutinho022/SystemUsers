package View;

public class UserMenu {

    public void menuInicial(){
        System.out.println("""
        \nINFORME UMA DAS OPCÕES ABAIXO:
        
        1 - Cadastrar-se.
        2 - Realizar login.
        3 - Sair.
        """);
    }

    public void menuCadastro(){
        System.out.println("""
        \nINFORME UMA DAS OPCÕES ABAIXO:
        
        1 - Cadastrar-se como gerente.
        2 - Cadastrar-se como vendedor.
        3 - Cadastrar-se como atendente.
        4 - Sair.
        """);
    }


    // menus das classes agr

    //MENU DO MANAGER
    public void menuManager(){
        System.out.println("""
        \nINFORME UMA DAS OPCÕES ABAIXO:
        
        1 - Gerar relátorio financeiro.
        2 - Consultar vendas.
        3 - Alterar dados.
        4 - Alterar senha.
        5 - Realizar logoff.
        """);
    }


    //MENU DO SALESMAN
    public void menuSalesman(){
        System.out.println("""
        \nINFORME UMA DAS OPCÕES ABAIXO:
        
        1 - Realizar venda.
        2 - Consultar vendas.
        3 - Alterar dados.
        4 - Alterar senha.
        5 - Realizar logoff.
        """);
    }


    //MENU DO ATTENDANT
    public void menuAttendant() {
        System.out.println("""
        \nINFORME UMA DAS OPCÕES ABAIXO:
        
        1 - Receber pagamentos.
        2 - Fechar o caixa.
        3 - Alterar dados.
        4 - Alterar senha.
        5 - Realizar logoff.
        """);
    }

}
