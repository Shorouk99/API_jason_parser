import java.util.ArrayList;

public class API {
    private ArrayList<ObjectField> request = new ArrayList<ObjectField>();
    private ArrayList<ObjectField> response = new ArrayList<ObjectField>();
    private String name;

    public API(){}

    public API(String name, ArrayList<ObjectField> request, ArrayList<ObjectField> response) {
        this.name = name;
        this.request = request;
        this.response = response;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRequest(ArrayList<ObjectField> request) {
        this.request = request;
    }

    public void setResponse(ArrayList<ObjectField> response) {
        this.response = response;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ObjectField> getRequest() {
        return request;
    }

    public ArrayList<ObjectField> getResponse() {
        return response;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder();
        value.append(name).append("\n");
        if (!request.isEmpty()) {
            value.append("Request Objects:\n");
            for (ObjectField req : request) {
                value.append(req.toString());
            }
        }

        if (!response.isEmpty()) {
            value.append("Response Objects:\n");
            for (ObjectField res : response ) {
                value.append(res.toString());
            }
        }
        return value.toString();
    }

    //requestJSON
    //responseJSON

}
