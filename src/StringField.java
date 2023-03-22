import java.util.Arrays;

public class StringField extends Field{
    private String allowedValues;
    private String value;

    public StringField(){}

    public StringField(String name, boolean mandatory, String allowedValues) {
        super(name, mandatory);
        this.allowedValues = allowedValues;
    }

    public StringField(String name, boolean mandatory, String allowedValues, String value) {
        super(name, mandatory);
        this.allowedValues = allowedValues;
        this.value = value;
    }

    public void setAllowedValues(String allowedValues) {
        this.allowedValues = allowedValues;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAllowedValues() {
        return allowedValues;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.getName() + "\n\tMandatory: " + super.isMandatory() + "\n\tAllowed Values\t" +
                allowedValues ;
    }
}
