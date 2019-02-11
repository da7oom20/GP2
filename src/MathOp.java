/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * test
 * @author da7oom
 */
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MathOp {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\da7oom\\Desktop\\MOCK_DATA2.csv";

//        System.out.println("starting write user.csv file: " + filePath);
//        writeCsv(filePath);
        System.out.println("starting read user.csv file");
        readCsv(filePath);
    }

    public static void writeCsv(String filePath) {
        List<Data> alldata = new ArrayList<Data>();

        //create demo Users
        Data data = new Data();
        data.setId(1);
        data.setName1("Jack");
        data.setPhoneNumber1("5322");
        alldata.add(data);

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);

            fileWriter.append("Id, First Name, Last Name\n");
            fileWriter.write(filePath, 0, 0);
            for (Data u : alldata) {
                fileWriter.append(String.valueOf(u.getId()));
                fileWriter.append(",");
                fileWriter.append(u.getName1());
                fileWriter.append(",");
                fileWriter.append(u.getPhoneNumber1());
                fileWriter.append("\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void readCsv(String filePath) {
        BufferedReader reader = null;
        FileWriter fileWriter = null;

        try {
            List<Data> allData = new ArrayList<Data>();
            String line = "";
            reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();

            List<String> temp = new ArrayList<String>();
            List<Double> tempInt = new ArrayList<Double>();
            List<List> theWeightList = new ArrayList<List>();
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                counter++;
                for (int i = 0; i < 10; i++) {
                    tempInt.add(0.0);
                }
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    Data data = new Data();
                    data.setId(Integer.parseInt(fields[0]));
                    data.setName1(fields[1]);
                    data.setPhoneNumber1(fields[2]);
                    data.setId2(Integer.parseInt(fields[3]));
                    data.setName2(fields[4]);
                    temp.add(fields[4]);
                    data.setPhoneNumber2(fields[5]);
                    data.setTypeOfCall(fields[6]);
                    data.setSafe(fields[7]);
                    data.setWeight(Double.parseDouble(fields[8]));
                    allData.add(data);
                    if (counter == 10) { // thats mean ok i took the frist criminal
                        for (int i = 0; i < 10; i++) {
                            String x = temp.get(i); // one by one 
                            for (int j = 0; j < 10; j++) {
                                String y = temp.get(j);
                                if (x.equals(y)) {
                                    double t = tempInt.get(i);
                                    t++;
                                    tempInt.set(i, t);
                                }
                            }
                        }
                        List<Double> temp1 = new ArrayList<>(tempInt.subList(0, 10));
                        theWeightList.add(temp1);
                        System.out.println("----");
                        System.out.println(temp);
                        counter = 0;
                        temp.clear();
                        tempInt.clear();
                    }
                }
            }

            int intCounter = 0;
            int extCounter = 0;

            for (Data d : allData) {
                // getting the data
                int id = d.getId();
                String name1 = d.getName1();
                String phone1 = d.getPhoneNumber1();
                int id2 = d.getId2();
                String name2 = d.getName2();
                String phone2 = d.getPhoneNumber2();
                String Type = d.getTypeOfCall();
                String Safe = d.getSafe();
                double weight = d.getWeight();
                
                if (intCounter == 10) {
                    intCounter = 0;
                    extCounter++;
                    if (extCounter == 10){
                    break;
                } 
                }
                
//                System.out.println("the extCounter = " + extCounter);
//                System.out.println("the intCounter = " + intCounter);
                //operations 
                weight = (Double) theWeightList.get(extCounter).get(intCounter);
                if ((Type.equals("OUTGOING") && (Safe.equals("TRUE"))) || Type.equals("OUTGOING") && (Safe.equals("FALSE"))) { // we will 
                    weight = weight/10.0;
//                    System.out.println("the weight1 = " + weight);
                    d.setWeight(weight);
                }
                
                else if((Type.equals("INCOMING") && (Safe.equals("TRUE"))) || Type.equals("INCOMING") && (Safe.equals("FALSE"))){
                    weight = ((weight*0.1)/10.0);
//                    System.out.println("the weight2 = " + weight);
                    d.setWeight(weight);
                }
                intCounter++;
                

            }

//            List<String> temp1 = theWeightList.get(2);
            System.out.println(theWeightList);

            fileWriter = new FileWriter(filePath);
            fileWriter.append("id,	Name1,	PhoneNumber1,	id2,	Name2,	PhoneNumber2,	TypeOfCall,	Safe,	Weight\n");
            fileWriter.write(filePath, 0, 0);
            for (Data u : allData) {
                System.out.println("ID : " + u.getId());
                System.out.println("Name1 : " + u.getName1());
                System.out.println("PhoneNumber1 : " + u.getPhoneNumber1());
                System.out.println("ID2 : " + u.getId2());
                System.out.println("Name2 : " + u.getName2());
                System.out.println("PhoneNumber2 : " + u.getPhoneNumber2());
                System.out.println("TypeOfCall : " + u.getTypeOfCall());
                System.out.println("Safe : " + u.getSafe());
                System.out.println("Weight : " + u.getWeight());

                int id = u.getId();
                String name1 = u.getName1();
                String phone1 = u.getPhoneNumber1();
                int id2 = u.getId2();
                String name2 = u.getName2();
                String phone2 = u.getPhoneNumber2();
                String Type = u.getTypeOfCall();
                String Safe = u.getSafe();
                double weight = u.getWeight();

                fileWriter.append(String.valueOf(id));
                fileWriter.append(",");
                fileWriter.append(name1);
                fileWriter.append(",");
                fileWriter.append(phone1);
                fileWriter.append(",");
                fileWriter.append(String.valueOf(id2));
                fileWriter.append(",");
                fileWriter.append(name2);
                fileWriter.append(",");
                fileWriter.append(phone2);
                fileWriter.append(",");
                fileWriter.append(Type);
                fileWriter.append(",");
                fileWriter.append(Safe);
                fileWriter.append(",");
                fileWriter.append(String.valueOf(weight));
                fileWriter.append("\n");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /*
public static void updateCSV() throws IOException {
    
    String fileToUpdate = "C:\\Users\\da7oom\\Desktop\\MOCK_DATA2.csv";
    String replace = "test";
    int row = 1;
    int col = 5;

File inputFile = new File(fileToUpdate);

// Read existing file 
CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
List<String[]> csvBody = reader.readAll();
// get CSV row column  and replace with by using row and column
csvBody.get(row)[col] = replace;
reader.close();

// Write to CSV file which is open
CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
writer.writeAll(csvBody);
writer.flush();
writer.close();
}
     */
}
