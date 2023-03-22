import java.util.ArrayList;

public class ObjectField extends Field{
    private ArrayList<Field> children = new ArrayList<Field>();

    public ObjectField(){}

    public ObjectField(String name, boolean mandatory) {
        super(name,mandatory);
    }

    public ObjectField(String name, boolean mandatory, ArrayList<Field> children) {
        super(name,mandatory);
        this.children = children;
    }

    public void setChildren(ArrayList<Field> children) {
        this.children = children;
    }

    public ArrayList<Field> getChildren() {
        return children;
    }

    public void addChildren (Field child) {
        this.children.add(child);
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder();
        value.append(super.getName()).append(":");
        if (!children.isEmpty()) {
            value.append("\n{");
            for (Field child: children) {
                if (child instanceof StringField) {
                    value.append("\t").append(child.toString());
                }

                else if (child instanceof ObjectField) {
                    value.append("\t").append(child.toString());
                    /*for (Field grandChild : ((ObjectField) child).children) {
                        value.append(grandChild.toString());
                    }*/
                }
                value.append("\n");
            }
            value.append("}\n");
        }
        return value.toString();
    }
}
