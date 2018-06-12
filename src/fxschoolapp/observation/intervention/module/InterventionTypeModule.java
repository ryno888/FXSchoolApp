package fxschoolapp.observation.intervention.module;

import app.db.DB_intervention;

public class InterventionTypeModule{

    private int int_type;
    private String int_type_label;
    
    //--------------------------------------------------------------------------
    public InterventionTypeModule(DB_intervention.Type type) {
        this.int_type = type.type();
        this.int_type_label = type.label();
    }

    //--------------------------------------------------------------------------
    public int getInt_type() {
        return int_type;
    }
    //--------------------------------------------------------------------------

    public void setInt_type(int int_type) {
        this.int_type = int_type;
    }
    //--------------------------------------------------------------------------

    public String getInt_type_label() {
        return int_type_label;
    }
    //--------------------------------------------------------------------------

    public void setInt_type_label(String int_type_label) {
        this.int_type_label = int_type_label;
    }
    //--------------------------------------------------------------------------
}