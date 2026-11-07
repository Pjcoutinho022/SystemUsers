package Controller;

import Models.Attendant;
import Models.Manager;
import Models.Salesman;
import Models.SystemUsers;

import java.util.ArrayList;
import java.util.List;


public class UsuarioController {

    // Lista de todos os usuarios do Sistema.
    private static List<SystemUsers> listAllUsers = new ArrayList<>();

    public static List<SystemUsers> getListAllUsers() {
        return listAllUsers;
    }

    // Variável para guardar quem está logado.
    private SystemUsers usuarioLogado = null;

    public SystemUsers getUsuarioLogado() {
        return usuarioLogado;
    }

    // Verificador de email ja existente.
    public boolean emailJaExiste(String emailParaVerificar) {
        for (SystemUsers usuarios : getListAllUsers()) {
            if (usuarios.getEmail().equalsIgnoreCase(emailParaVerificar)) {
                return true; // Encontrou! O e-mail já está em uso.
            }
        }
        return false; // E-mail livre para uso
    }

    // Funcao de cadastro de usuarios.
    // Cadastro de gerentes
    public boolean cadastrarGerente(String name, String email, String password) {
        if (emailJaExiste(email)) {
            return false;
        }
        Manager newManager = new Manager(name, email, password);
        listAllUsers.add(newManager);
        return true;
    }

    // Cadastro de vendedor
    public boolean cadastrarVendedor(String name, String email, String password) {
        if (emailJaExiste(email)) {
            return false;
        }
        Salesman newSalesman = new Salesman(name, email, password);
        listAllUsers.add(newSalesman);
        return true;
    }

    // Cadastro de atendente
    public boolean cadastrarAtendente(String name, String email, String password) {
        if (emailJaExiste(email)) {
            return false;
        }
        Attendant newAttendant = new Attendant(name, email, password);
        listAllUsers.add(newAttendant);
        return true;
    }

    // REALIZAR LOGIN
    public boolean realizarLogin(String email, String password) {
        for (SystemUsers user : getListAllUsers()) {
            if (user.logIn(email, password)) {
                this.usuarioLogado = user;
                return true;
            }
        }
        return false;
    }

    // REALIZAR LOGOFF
    public void realizarLogoff() {
        if (this.usuarioLogado != null) {
            this.usuarioLogado.logOff();
            this.usuarioLogado = null;
        }
    }

    // GERAR RELATORIO DE VENDAS GERAIS PELO GERENTE
    public List<String> gerarConsultaVendasGerais(Manager manager) {
        return manager.consultAllSales(getListAllUsers());
    }

    // GERAR RELATORIO FINANCEIRO PELO GERENTE
    public List<String> gerarRelatorioFinanceiro(Manager manager) {
        return manager.generateFinancialReport(getListAllUsers());
    }
}
