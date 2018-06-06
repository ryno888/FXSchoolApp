package fxschoolapp.person.students.modules.table;

import app.db.DB_intervention;
import core.com.ui.fx.tableview.ComUiFxTableViewModule;

public class InterventionTableModule extends ComUiFxTableViewModule{
    
    private Object int_type;
    private Object int_type_label;
    private Object int_year;
    private Object int_remark;
    
    //--------------------------------------------------------------------------
    public  InterventionTableModule(DB_intervention comDBobj) {
        super(comDBobj);
        this.int_type = comDBobj.get("int_type");
        this.int_year = comDBobj.get("int_year");
        this.int_remark = comDBobj.get("int_remark");
        this.int_type_label = DB_intervention.Type.getType((int) this.int_type).label();
    }
    //--------------------------------------------------------------------------
    public Object getInt_type() {
        return int_type;
    }

    //--------------------------------------------------------------------------
    public void setInt_type(Object int_type) {
        this.int_type = int_type;
    }

    //--------------------------------------------------------------------------
    public Object getInt_year() {
        return int_year;
    }

    //--------------------------------------------------------------------------
    public void setInt_year(Object int_year) {
        this.int_year = int_year;
    }

    //--------------------------------------------------------------------------
    public Object getInt_remark() {
        return int_remark;
    }

    //--------------------------------------------------------------------------
    public void setInt_remark(Object int_remark) {
        this.int_remark = int_remark;
    }
    
    //--------------------------------------------------------------------------
    public Object getInt_type_label() {
        return int_type_label;
    }

    //--------------------------------------------------------------------------
    public void setInt_type_label(Object int_type_label) {
        this.int_type_label = int_type_label;
    }
    
    //--------------------------------------------------------------------------
}