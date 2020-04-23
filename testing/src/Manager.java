public class Manager {
    private int _tcNO;
    private String _username;
    private int _password;

    public Manager(int _tcNO, String _username, int _password) {
        this._tcNO = _tcNO;
        this._username = _username;
        this._password = _password;
    }

    //TODO Change employee quality
    public void employeeQuality(Employee emp){

    }
    //TODO set the status of worksheet
    public void finishWorksheet(Worksheets ws){

    }

    public int get_tcNO() {
        return _tcNO;
    }

    public void set_tcNO(int _tcNO) {
        this._tcNO = _tcNO;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public int get_password() {
        return _password;
    }

    public void set_password(int _password) {
        this._password = _password;
    }

}
