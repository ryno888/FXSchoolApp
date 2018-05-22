package fxschoolapp.person.students.modules;

import app.db.DB_person;
import core.com.ui.fx.tableview.ComUiFxTableViewModule;

public class PersonListTableModule extends ComUiFxTableViewModule{
    
    private Object per_firstname;
    private Object per_lastname;
    private Object per_birthday;
    
    //--------------------------------------------------------------------------
    public  PersonListTableModule(DB_person comDBobj) {
        super(comDBobj);
        this.per_firstname = comDBobj.get("per_firstname");
        this.per_lastname = comDBobj.get("per_lastname");
        this.per_birthday = comDBobj.get("per_birthday");
    }
    //--------------------------------------------------------------------------
    public Object getPer_firstname() {
        return per_firstname;
    }
    //--------------------------------------------------------------------------
    public void setPer_firstname(Object per_firstname) {
        this.per_firstname = per_firstname;
    }
    //--------------------------------------------------------------------------
    public Object getPer_lastname() {
        return per_lastname;
    }
    //--------------------------------------------------------------------------
    public void setPer_lastname(Object per_lastname) {
        this.per_lastname = per_lastname;
    }
    //--------------------------------------------------------------------------
    public Object getPer_birthday() {
        return per_birthday;
    }
    //--------------------------------------------------------------------------
    public void setPer_birthday(Object per_birthday) {
        this.per_birthday = per_birthday;
    }
    //--------------------------------------------------------------------------
}