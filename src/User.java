public abstract class User {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    public User() {

    }
    public User(int id, String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getSurname() {
        return surname;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public String getPosition(){
        return null;
    }
}
