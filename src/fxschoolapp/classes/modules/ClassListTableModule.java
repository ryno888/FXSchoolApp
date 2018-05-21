package fxschoolapp.classes.modules;

import app.db.DB_classes;

public class ClassListTableModule {
    
    private Object cla_name;
    private Object cla_date;
    private Object total_students;
    
    //--------------------------------------------------------------------------
    public  ClassListTableModule(DB_classes comDBobj) {
        
        this.cla_name = comDBobj.get("cla_name");
        this.cla_date = comDBobj.get("cla_date");
        this.total_students = Math.random();
    }
    //--------------------------------------------------------------------------
    public Object getCla_name() {
        return cla_name;
    }
    //--------------------------------------------------------------------------
    public void setCla_name(Object cla_name) {
        this.cla_name = cla_name;
    }
    //--------------------------------------------------------------------------
    public Object getCla_date() {
        return cla_date;
    }
    //--------------------------------------------------------------------------
    public void setCla_date(Object cla_date) {
        this.cla_date = cla_date;
    }
    //--------------------------------------------------------------------------
    public Object getTotal_students() {
        return total_students;
    }
    //--------------------------------------------------------------------------
    public void setTotal_students(Object total_students) {
        this.total_students = total_students;
    }
    //--------------------------------------------------------------------------
}