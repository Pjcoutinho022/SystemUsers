package Models;


import Models.Interfaces.IAttendant;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Attendant extends SystemUsers implements IAttendant {

    @Override
    public boolean isAdminUser() {
        return false;
    }

    // Criando Lista para receber os pagamentos.
    List<Double> listOfPayments = new ArrayList<>();

        // Atributo Valor em caixa
    private double cashOnHand = 0.0;

    public double getCashOnHand() {
        return cashOnHand;
    }

    public Attendant(String name,
                     String email,
                     String password){
        super(name,email,password);
        this.isAdminUser();
    }

    @Override
    public double receivePayments(double payment) {
        if (payment > 0.0) {
            listOfPayments.add(payment);
            cashOnHand += payment;
            return cashOnHand;
        }
        return 0;
    }

    @Override
    public void closerRegister() {
        cashOnHand = 0.0;
    }

    // Getter da lista de pagamentos

    public List<Double> getListOfPayments() {
        return listOfPayments;
    }
}
