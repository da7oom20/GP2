/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * test
 *
 * @author da7oom
 */
//import com.opencsv.CSVReader;
//import com.opencsv.CSVWriter;
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
        File filePath = new File("C:\\Users\\da7oom\\Desktop\\dumb");
//        File dir = new File("path/to/files/");        
        
        for (String file : filePath.list()) {
            System.out.println("starting read user.csv file");
            readCsv(file);
        }

//        System.out.println("starting write user.csv file: " + filePath);
//        writeCsv(filePath);
//        System.out.println("starting read user.csv file");
//        readCsv(filePath);
    }

    /*
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
    }*/
    public static void readCsv(String filePath) {
        BufferedReader reader = null;
        FileWriter fileWriter = null;
        filePath = "C:\\Users\\da7oom\\Desktop\\dumb\\" + filePath;
        try {
            List<Data> allData = new ArrayList<Data>();
            String line = "";
            reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();
            System.out.println("here");
            List<String> temp = new ArrayList<String>();
            List<Double> tempInt = new ArrayList<Double>();
            List<List> theWeightList = new ArrayList<List>();
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                counter++;
                for (int i = 0; i < 10; i++) { //for weight
                    tempInt.add(0.0);
                }
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    Data data = new Data();
                    data.setName(fields[0]);
                    temp.add(fields[0]); // i do that because of i will decide the initial weight depends on how much repetations for this name , so i keep track them
                    data.setTypeOfCall(fields[1]);
                    data.setCallNumber(fields[2]);
                    data.setDate(fields[3]);
                    data.setTime(fields[4]);
                    data.setDuration(fields[5]);
//                    data.setSafe(fields[6]);
                    data.setWeight(Double.parseDouble(fields[6]));
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
                String TypeOfCall = d.getTypeOfCall();
//                String Safe = d.getSafe();
//                String duration = d.getDuration();
                double Weight = d.getWeight();

                if (intCounter == 10) {
                    intCounter = 0;
                    extCounter++;
                    if (extCounter == 10) {
                        break;
                    }
                }

//                System.out.println("the extCounter = " + extCounter);
//                System.out.println("the intCounter = " + intCounter);
                //operations 
                Weight = (Double) theWeightList.get(extCounter).get(intCounter);
                if ((TypeOfCall.equals("OUTGOING"))) { // we will 
                    Weight = Weight + 0.02;
//                    System.out.println("the weight1 = " + weight);
                    d.setWeight(Weight);
                } else if ((TypeOfCall.equals("INCOMING"))) {
                    Weight = Weight + 0.01;
//                    System.out.println("the weight2 = " + weight);
                    d.setWeight(Weight);
                }
                intCounter++;

            }

//            List<String> temp1 = theWeightList.get(2);
            System.out.println(theWeightList);

            fileWriter = new FileWriter(filePath);
            fileWriter.append("Name,TypeOfCall,CallNumber,Date,Time,Duration,Weight\n");
            fileWriter.write(filePath, 0, 0);
            for (Data u : allData) {
                System.out.println("Name : " + u.getName());
                System.out.println("TypeOfCall : " + u.getTypeOfCall());
                System.out.println("CallNumber : " + u.getCallNumber());
                System.out.println("Date : " + u.getDate());
                System.out.println("Time : " + u.getTime());
                System.out.println("Duration : " + u.getDuration());
//                System.out.println("Safe : " + u.getSafe());
                System.out.println("Weight : " + u.getWeight());

                String Name = u.getName();
                String CallNumber = u.getCallNumber();
                String Date = u.getDate();
                String Time = u.getTime();
                String Duration = u.getDuration();
                String TypeOfCall = u.getTypeOfCall();
//                String Safe = u.getSafe();
                double Weight = u.getWeight();

                fileWriter.append(Name);
                fileWriter.append(",");
                fileWriter.append(TypeOfCall);
                fileWriter.append(",");
                fileWriter.append(CallNumber);
                fileWriter.append(",");
                fileWriter.append(Date);
                fileWriter.append(",");
                fileWriter.append(Time);
                fileWriter.append(",");
                fileWriter.append(Duration);
                fileWriter.append(",");
//                fileWriter.append(Safe);
//                fileWriter.append(",");
                fileWriter.append(String.valueOf(Weight));
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
