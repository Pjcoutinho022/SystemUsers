package Models.Interfaces;

import Models.SystemUsers;
import java.util.List;

public interface IManager {

    // interface para gerar relatorio financeiro recebendo a lista de usuarios do sistema
    List<String> generateFinancialReport(List<SystemUsers> todosUsuarios);

    // interface para consultar todas as vendas da empresa recebendo a lista de usuarios
    List<String> consultAllSales(List<SystemUsers> todosUsuarios);

}
