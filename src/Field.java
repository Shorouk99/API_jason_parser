import java.util.ArrayList;

public abstract class Field {
    private String name;
    private boolean mandatory;
    //private String type;
    //private ArrayList<Field> children = new ArrayList<Field>();
    //private String[] allowedValues;


    public Field(){}

    public Field(String name, boolean mandatory) {
        this.name = name;
        this.mandatory = mandatory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }


    public String getName() {
        return name;
    }

    public boolean isMandatory() {
        return mandatory;
    }

}
