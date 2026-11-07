package Models;

import Models.Interfaces.IManager;

import java.util.ArrayList;
import java.util.List;

public class Manager extends SystemUsers implements IManager {

    // Regra para informar True para o Administrador
    @Override
    public boolean isAdminUser() {
        return true;
    }

    // Constructor Manager
    public Manager(String name,
                   String email,
                   String password) {
        super(name, email, password);
    }

    // Implementacoes de Interfaces
    @Override
    public List<String> consultAllSales(List<SystemUsers> todosUsuarios) {
        List<String> consult = new ArrayList<>();
        int id = 1;

        for (SystemUsers user : todosUsuarios) {
            if (user instanceof Salesman) {
                Salesman salesman = (Salesman) user;
                String linha = "Id: %d | Vendedor: %s | Quantidade de vendas: %d\n"
                        .formatted(id, salesman.getName().toUpperCase(), salesman.getSalesQuantity());
                consult.add(linha);
                id++;
            }
        }
        return consult;
    }

    @Override
    public List<String> generateFinancialReport(List<SystemUsers> todosUsuarios) {
        List<String> report = new ArrayList<>();
        int idReport = 1;
        double fullPayment = 0.0;

        for (SystemUsers user : todosUsuarios) {
            if (user instanceof Attendant) {
                Attendant attendant = (Attendant) user;
                for (Double payment : attendant.getListOfPayments()) {
                    String linha = "Id: %d  | Pagamento recebido: RS%.2f  | Atendente: %s\n"
                            .formatted(idReport, payment, attendant.getName()).toUpperCase();
                    report.add(linha);
                    idReport++;
                    fullPayment += payment;
                }
            }
        }
        String linha = "Pagamento total: R$%.2f".formatted(fullPayment);
        report.add(linha);
        return report;
    }

}
