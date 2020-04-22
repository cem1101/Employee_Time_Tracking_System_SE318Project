public class Employee {
    private String _name;
    private String _surname;
    private String _username;
    private int _password;
    private int _age;
    private String _email;
    private String _lastLogin;

    public Employee(String name, String surname, String username, int password, int age, String email, String lastlogin){
        _name = name;
        _surname = surname;
        _username = username;
        _password = password;
        _age = age;
        _email = email;
        _lastLogin = lastlogin;
    }

    public String get_name() { return _name;}
    public String get_surname() { return _surname; }
    public String get_username() { return _username;}
    public int get_password() { return _password; }
    public int get_age() { return _age; }
    public String get_email() { return _email; }
    public String get_lastLogin() { return _lastLogin; }



    public void set_name(String _name) { this._name = _name;}
    public void set_surname(String _surname) { this._surname = _surname; }
    public void set_username(String _username) { this._username = _username; }
    public void set_password(int _password) { this._password = _password; }
    public void set_age(int _age) {this._age = _age; }
    public void set_email(String _email) {this._email = _email; }
    public void set_lastLogin(String _lastLogin) { this._lastLogin = _lastLogin; }
}
