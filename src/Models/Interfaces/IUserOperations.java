package Models.Interfaces;

public interface IUserOperations {

    // interface de login
    boolean logIn(String email, String password);

    //interface de logOff
    void logOff();


    // interface alterar senha
    boolean changePassword(String senhaAntiga, String novaSenha);

    // interface alterar dados
    boolean changeData(String novoNome, String novoEmail);

}
