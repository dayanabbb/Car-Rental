public class Client extends User{
    private String telephone;
    public Client(){

    }
    public Client(int id, String name, String surname, String telephone, String login, String password){
        super(id, name, surname, login, password);
        this.telephone = telephone;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    @Override
    public String getPosition(){
        return "Client";
    }
}
