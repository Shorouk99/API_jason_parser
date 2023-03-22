import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class XLSXParser {
    private XLSXParser(){}

    public static Service XLSXtoService(String path) {
        Service service = new Service();
        int APIcounter = 0;

        try {
            File file = new File(path);   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file

            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if ((cell.getStringCellValue()).contains("API_NAME")) {
                        service.addAPI(new API());
                        API api = service.getAPIs().get(APIcounter);
                        api.setName(cell.getStringCellValue().replace("(API_NAME)", ""));

                        String allowedValues = "";
                        ArrayList<ObjectField> reqAPI = new ArrayList<>();
                        ArrayList<ObjectField> resAPI = new ArrayList<>();
                        HashMap<String, ArrayList<Field>> objMap = new HashMap<>();

                        for (char i = 0; i < 4; i++) row = itr.next(); //skip the next 5 rows
                        cellIterator = row.cellIterator();
                        cell = cellIterator.next(); //I/O column

                        while (!cell.getStringCellValue().contains("API_NAME") && !cell.getStringCellValue().equals("")) {
                            boolean isRequest = cell.getStringCellValue().equals("I");
                            //System.out.println(isRequest);
                            cell = cellIterator.next(); //Field Name column
                            String cellValue = cell.getStringCellValue();
                            String fieldName = cellValue.substring(cellValue.lastIndexOf("/")+1);
                            String[] hierarchy = cellValue.split("/");
                            //System.out.println(cellValue);

                            cell = cellIterator.next(); //Type column
                            boolean isStringField = cell.getStringCellValue().equals("string");

                            //System.out.println(isStringField);

                            cell = cellIterator.next(); //Allowed values column
                            if (!cell.getStringCellValue().isEmpty()) {
                                allowedValues = cell.getStringCellValue();
                            }

                            cell = cellIterator.next(); //Mandatory column
                            boolean isMandatory = cell.getStringCellValue().equals("Y");

                            if (isStringField) {
                                StringField stringField = new StringField(fieldName, isMandatory, allowedValues);
                                String parent = hierarchy[hierarchy.length-2];
                                if (objMap.containsKey(parent)) {
                                   objMap.get(parent).add(stringField);
                                }

                                else {
                                    ArrayList<Field> children = new ArrayList<>();
                                    children.add(stringField);
                                    objMap.put(parent,children);
                                }
                            }

                            else {
                                ObjectField objectField= new ObjectField(fieldName, isMandatory);
                                if (hierarchy.length > 2) {
                                    String parent = hierarchy[hierarchy.length-2];
                                    if (objMap.containsKey(parent)) {
                                        objMap.get(parent).add(objectField);
                                    }
                                    else {
                                        ArrayList<Field> children = new ArrayList<>();
                                        children.add(objectField);
                                        objMap.put(parent,children);
                                    }
                                }
                                else {
                                    ArrayList<Field> children = new ArrayList<>();
                                    objMap.put(fieldName, children);
                                    if (isRequest) {
                                        reqAPI.add(objectField);
                                    }
                                    else resAPI.add(objectField);
                                }
                            }

                            allowedValues = "";

                            if (itr.hasNext()) {
                                row = itr.next();
                                cellIterator = row.cellIterator();
                                cell = cellIterator.next();
                            }
                        }

                        for (ObjectField obj : reqAPI) {
                            ArrayList<Field> children = objMap.get(obj.getName());
                            for (int i = children.size()-1; i>=0; i--) {
                                Field field = children.get(i);
                                if (field instanceof ObjectField) {
                                    ((ObjectField)field).setChildren(objMap.get(field.getName()));
                                }
                            }
                            obj.setChildren(children);
                        }

                        for (ObjectField obj : resAPI) {
                            ArrayList<Field> children = objMap.get(obj.getName());
                            for (int i = children.size()-1; i>=0; i--) {
                                Field field = children.get(i);

                                if (field instanceof ObjectField) {
                                    ((ObjectField)field).setChildren(objMap.get(field.getName()));
                                }
                            }
                            obj.setChildren(children);
                        }
                        api.setRequest(reqAPI);
                        api.setResponse(resAPI);
                        APIcounter++;
                        //System.out.println("------------------------------------");
                    }
                }
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return service;
    }
}
