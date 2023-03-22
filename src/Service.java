import java.util.ArrayList;

public class Service {
    private ArrayList<API> APIs = new ArrayList<API>();

    public Service() {}

    public Service(ArrayList<API> APIs) {
        this.APIs = APIs;
    }

    public void setAPIs(ArrayList<API> APIs) {
        this.APIs = APIs;
    }

    public ArrayList<API> getAPIs() {
        return APIs;
    }

    public void addAPI(API api) {
        APIs.add(api);
    }

    public int numAPIs() {
        return APIs.size();
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder();
        for (API api : APIs) {
            value.append(api.toString());
            value.append("------------------------------------");
        }
        return value.toString();
    }
}
