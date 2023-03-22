import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args) {
        Service service = XLSXParser.XLSXtoService("test.xlsx");
        System.out.println(service.getAPIs());
    }
}
