package Models;

import Models.Interfaces.IUserOperations;

// Super-Class
public abstract class SystemUsers implements IUserOperations {

    // Atributos que um User tem que ter
        private String name;
        private String email;
        private String password;
        private boolean adminUser;


        // Definindo Construtor
        public SystemUsers(String name,
                           String email,
                           String password){
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("E-mail inválido! O e-mail deve conter '@'.");
            }
            this.name = name;
            this.email = email;
            this.password = password;
        };


        // Getters e Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isAdminUser() {
            return adminUser;
        }


        // IMPLEMENTAÇÕES DE INTERFACES QUE TODO USER PODE USAR
    @Override
    public boolean logIn(String email, String password) {
        // Compara o email e a senha digitados com os dados guardados do utilizador
        if(email.equalsIgnoreCase(getEmail()) && password.equals(getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public void logOff() {
    }

    @Override
    public boolean changePassword(String senhaAntiga, String novaSenha) {
        // Se a senha antiga está incorreta, já para e retorna false
        if (!getPassword().equals(senhaAntiga) || senhaAntiga.equals(novaSenha)) {
            return false;
        }

        // Se passou pelas validações, altera a senha e retorna true!
        setPassword(novaSenha);
        return true;
    }

    @Override
    public boolean changeData(String novoNome, String novoEmail) {
        if (getName().equals(novoNome) && getEmail().equals(novoEmail)) {
            return false;
        }
        setName(novoNome);
        setEmail(novoEmail);
        return true;

    }

}
