package ru.samvel.jetty;


public class Password {

    private String password;
    private String CorrectedPassword = "pass";

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        if(password.equals(CorrectedPassword)){
            return true;
        }
        return false;
    }


    public void checkPassword(String password){

    }
}
