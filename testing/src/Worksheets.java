public class Worksheets {
    private int _worksheetID;
    private String _responsible;
    private String _approvalStatus;
    private String _approvedDisapprovedByAdmin;
    private String _responsibleManager;
    private String _qualityOfWork;
    private int _responsibleTC;


    public Worksheets(int _worksheetID, String _responsible, String _approvalStatus, String _approvedDisapprovedByAdmin, String _responsibleManager, String _qualityOfWork, int _responsibleTC) {
        this._worksheetID = _worksheetID;
        this._responsible = _responsible;
        this._approvalStatus = _approvalStatus;
        this._approvedDisapprovedByAdmin = _approvedDisapprovedByAdmin;
        this._responsibleManager = _responsibleManager;
        this._qualityOfWork = _qualityOfWork;
        this._responsibleTC = _responsibleTC;
    }

    public int get_worksheetID() {
        return _worksheetID;
    }

    public void set_worksheetID(int _worksheetID) {
        this._worksheetID = _worksheetID;
    }

    public String get_responsible() {
        return _responsible;
    }

    public void set_responsible(String _responsible) {
        this._responsible = _responsible;
    }

    public String get_approvalStatus() {
        return _approvalStatus;
    }

    public void set_approvalStatus(String _approvalStatus) {
        this._approvalStatus = _approvalStatus;
    }

    public String get_approvedDisapprovedByAdmin() {
        return _approvedDisapprovedByAdmin;
    }

    public void set_approvedDisapprovedByAdmin(String _approvedDisapprovedByAdmin) {
        this._approvedDisapprovedByAdmin = _approvedDisapprovedByAdmin;
    }

    public String get_responsibleManager() {
        return _responsibleManager;
    }

    public void set_responsibleManager(String _responsibleManager) {
        this._responsibleManager = _responsibleManager;
    }

    public String get_qualityOfWork() {
        return _qualityOfWork;
    }

    public void set_qualityOfWork(String _qualityOfWork) {
        this._qualityOfWork = _qualityOfWork;
    }

    public int get_responsibleTC() {
        return _responsibleTC;
    }

    public void set_responsibleTC(int _responsibleTC) {
        this._responsibleTC = _responsibleTC;
    }




}
