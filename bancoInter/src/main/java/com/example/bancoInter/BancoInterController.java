package com.example.bancoInter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.sql.SQLException;

public class BancoInterController {

    @FXML
    Label registroErro, loginErro, dinheiroSaldo, pixErro,
    cartoesTitular, cartoesNum, cartaoTitular, saldoPagar, valorErro, pagarPara;

    @FXML
    TextField registroNome, registroCPF, registroTelefone,
    registroEmail, loginCPF, pixChave, valorPagar;

    @FXML
    PasswordField registroSenha, registroConfirma, loginSenha;

    @FXML
    Button RegisterButton, LoginButton, AnotherButton,
    RegisterBackButton, PixButton, PixBackButton,
    CardsButton, CardsBackButton, CardButton, CardBackButton,
    HideButton, PixPayButton, ValueBackButton, ValuePayButton;

    @FXML
    Rectangle censuraValor;

    @FXML
    protected void onRegisterButtonClick() {
        String nome = registroNome.getText();
        String cpf = registroCPF.getText();
        String telefone = registroTelefone.getText();
        String email = registroEmail.getText();
        String senha = registroSenha.getText();
        String confirma = registroConfirma.getText();
        try {
            if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()
                    || email.isEmpty() || senha.isEmpty() || confirma.isEmpty()) {
                registroErro.setText("Preencha todos os campos.");
            } else if(!isNumeric(telefone) || !isNumeric(cpf)) {
                registroErro.setText("Telefone ou CPF inválidos.");
            } else if(!senha.equals(confirma)) {
                registroErro.setText("Senhas não coincidem.");
            } else {
                String[] values = {nome, cpf, telefone, email, senha};
                DatabaseManager.insertAll("Clientes", values);
                String[] account = {cpf, "Corrente", "100.00", "77"};
                DatabaseManager.insertAll("Contas", account);
                changeScreen(RegisterButton, "/TelaLogin.fxml");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onLoginButtonClick(){
        String cpf = loginCPF.getText();
        String senha = loginSenha.getText();
        try {
            if (cpf.isEmpty() || senha.isEmpty()){
                loginErro.setText("Preencha todos os campos.");
            }else if (DatabaseManager.checkLogin(cpf, senha)){
                changeScreen(LoginButton, "/TelaPrincipal.fxml");
                DatabaseManager.cpfLogged = cpf;
                /*
                }else if (DatabaseManager.checkLogin(cpf, senha)){
                changeScreen(LoginButton, "/TelaPrincipal.fxml");
                String where = "ClienteCPF = '" + DatabaseManager.select
                        ("Logged", "CPF", "CPF = '" + cpf + "'") + "'";
                String saldo = DatabaseManager.select("Contas", "Saldo", where);
                dinheiroSaldo.setText("R$ " + saldo);
                }
                */
            } else {
                loginErro.setText("Dados inválidos. Tente novamente.");
            }
        }catch (SQLException e){
            loginErro.setText("Dados inválidos. Tente novamente.");
        }
    }

    @FXML
    protected void onHideButtonClick() {
        if (dinheiroSaldo.getText().equals("R$ ***.**")) {
            try {
                String where = "ClienteCPF = '" + DatabaseManager.cpfLogged + "'";
                String saldo = DatabaseManager.select("Contas", "Saldo", where);
                dinheiroSaldo.setText("R$ " + saldo);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            dinheiroSaldo.setText("R$ ***.**");
    }
    }

    @FXML
    protected void onAnotherButtonClick() {
        changeScreen(AnotherButton, "/TelaRegistro.fxml");
    }

    @FXML
    protected void onRegisterBackButtonClick() {
        changeScreen(RegisterBackButton, "/TelaLogin.fxml");
    }

    @FXML
    protected void onPixButtonClick() {
        changeScreen(PixButton, "/TelaPix.fxml");
    }

    @FXML
    protected void onPixBackButtonClick() {
        changeScreen(PixBackButton, "/TelaPrincipal.fxml");
    }

    @FXML
    protected void onPixPayButtonClick() {
        String receiver = pixChave.getText();
        if(!receiver.equals(DatabaseManager.cpfLogged)) {
            try {
                String where = "CPF = '" + receiver + "'";
                DatabaseManager.cpfToPay = DatabaseManager.select("Clientes", "CPF", where);
                changeScreen(PixPayButton, "/TelaValorPagar.fxml");
            } catch (SQLException e) {
                pixErro.setText("Insira uma chave válida");
            }
        } else {
            pixErro.setText("Não insira chave própria");
        }

    }

    @FXML
    protected void onCardsButtonClick() {
        changeScreen(CardsButton, "/TelaCartoes.fxml");
    }

    @FXML
    protected void onCardsBackButtonClick() {
        changeScreen(CardsBackButton, "/TelaPrincipal.fxml");
    }

    @FXML
    protected void onCardsEntered() {
        try{
            String where = "CPF = '" + DatabaseManager.cpfLogged + "'";
            String titular = DatabaseManager.select("Clientes", "Nome", where);
            cartoesTitular.setText(titular.toUpperCase());
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onCardButtonClick() {
        changeScreen(CardButton, "/TelaCartao.fxml");
    }

    @FXML
    protected void onCardBackButtonClick() {
        changeScreen(CardBackButton, "/TelaCartoes.fxml");
    }

    @FXML
    protected void onCardEntered() {
        try{
            String where = "CPF = '" + DatabaseManager.cpfLogged + "'";
            String titular = DatabaseManager.select("Clientes", "Nome", where);
            cartaoTitular.setText(titular.toUpperCase());
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onHideValueButtonClick() {
        if(censuraValor.isVisible()) {
            try{
                String where = "ClienteCPF = '" + DatabaseManager.cpfLogged + "'";
                Double saldo = Double.parseDouble(
                        DatabaseManager.select("Contas", "Saldo", where));
                saldoPagar.setText(saldo.toString());
            } catch(SQLException e) {
                e.printStackTrace();
            }
            censuraValor.setVisible(false);
        }
        else {
            censuraValor.setVisible(true);
        }
    }

    @FXML
    protected void onValueBackButtonClick() {
        changeScreen(ValueBackButton, "/TelaPix.fxml");
    }

    @FXML
    protected void onValuePayButtonClick() {
        if(isNumeric(valorPagar.getText())) {
            Double pagar = Double.parseDouble(valorPagar.getText());
            if(pagar <= Double.parseDouble(saldoPagar.getText()) && pagar > 0) {
                try{
                    String where = "ClienteCPF = '" + DatabaseManager.cpfLogged + "'";
                    Double saldo = Double.parseDouble(
                    DatabaseManager.select("Contas", "Saldo", where));
                    saldo -= pagar;
                    DatabaseManager.update("Contas", "Saldo", saldo.toString(), where);
                    where = "ClienteCPF = '" + DatabaseManager.cpfToPay + "'";
                    saldo = Double.parseDouble(
                    DatabaseManager.select("Contas", "Saldo", where));
                    saldo += pagar;
                    DatabaseManager.update("Contas", "Saldo", saldo.toString(), where);
                    changeScreen(ValuePayButton, "/TelaPrincipal.fxml");
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            else if(pagar <= 0) {
                valorErro.setText("Valor inválido");
            }
            else {
                valorErro.setText("Saldo insuficiente");
            }
        } else {
            valorErro.setText("Valor inválido");
        }
    }

    @FXML
    protected void pagarParaEntered() {
        try{
            String where = "CPF = '" + DatabaseManager.cpfToPay + "'";
            String recebedor = DatabaseManager.select("Clientes", "Nome", where);
            pagarPara.setText(recebedor);
            where = "ClienteCPF = '" + DatabaseManager.cpfLogged + "'";
            Double saldo = Double.parseDouble(
                    DatabaseManager.select("Contas", "Saldo", where));
            saldoPagar.setText(saldo.toString());
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void valorTyped() {
        valorPagar.setFont(Font.font("System", FontPosture.REGULAR, 28));
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @FXML
    protected void changeScreen(Button currentButton, String screen){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(screen));
            AnchorPane root = loader.load();
            Scene scene = currentButton.getScene();
            scene.setRoot(root);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
