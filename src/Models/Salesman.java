package Models;

import Models.Interfaces.IConsultSales;
import Models.Interfaces.ISalesman;
import java.util.ArrayList;
import java.util.List;

public class Salesman extends SystemUsers implements ISalesman, IConsultSales {

    @Override
    public boolean isAdminUser() {
        return false;
    }

    // Atributo para quantidade de vendas
    private int salesQuantity;

    // Construtor do Salesman
    public Salesman(String name,
                    String email,
                    String password) {
        super(name, email, password);
    }

    // Getter e Setter
    public int getSalesQuantity() {
        return salesQuantity;
    }

    // Implementando a Interface ISalesman realizar venda
    @Override
    public void makeASale() {
        this.salesQuantity++;
    }

    // Implementando interface consultar vendas
    @Override
    public List<String> consultSales() {
        List<String> minhasVendas = new ArrayList<>();
        minhasVendas.add("Minhas vendas: " + this.salesQuantity);
        return minhasVendas;
    }
}
