package com.company.entities;

public class Client extends User{
    private String telephone;
    public Client(){

    }
    public Client(int id, String name, String surname, String username, String password, String telephone){
        super(id, name, surname, username, password);
        this.telephone = telephone;
    }
    public Client(String name, String surname,String username, String password, String telephone){
        super(name, surname, username, password);
        this.telephone = telephone;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
