package org.example;

//Package Imports
import java.io.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Main Class
public class Main {

    //Hashmap storing file data
    static HashMap<String,String> file_data_HashMap=new HashMap();

    //    Adding existing file data to file_data_HashMap for implementing CRUD operations on them.
    static void addExistingData(File file){
        try{
            Scanner read = new Scanner(file);
            String key_value_pair[] = new String[2];
            while(read.hasNextLine()){
                //Splitting data into key value pairs
                key_value_pair = read.nextLine().split(":");
                file_data_HashMap.put(key_value_pair[0],key_value_pair[1]);

            }
        }
        catch(FileNotFoundException exception){
            exception.printStackTrace();
        }
    }



    /*      Adding and updating data in file
      NOTE:
          This function will add new data as well as update prev key value to new key value if key already exists
  * */
    static void setData(File file,String key,String val){
        try{
            file_data_HashMap.put(key,val);
            FileWriter fileWriter = new FileWriter(file);

            for(Map.Entry iterator: file_data_HashMap.entrySet()){
                fileWriter.append(iterator.getKey()+":"+iterator.getValue()+"\n");
            }

            fileWriter.close();
        }
        catch(IOException exception){
            exception.printStackTrace();
        }

    }

    //Fetching data value corresponding to a specific key
    static void getData(File file,String key){
        try{
            if(file_data_HashMap.containsKey(key)){
                System.out.println(file_data_HashMap.get(key));
            }
            else{
                System.out.println("Key not found\n");
            }
        }catch (Exception exception){
            System.out.println(exception);
        }

    }



    //------------------Main Function----------------//
    public static void main(String[] args) {
        try{
            //Creating a new file
            Scanner scanner=new Scanner(System.in);
            File file=new File("sampleFile");

            if(file.createNewFile()){
                System.out.println("File Created At"+file.getCanonicalPath());
            }
            else{
                System.out.println("File Already Exists at"+file.getCanonicalPath());
            }

            //Call to fetch existing file data to HashMap
            addExistingData(file);

            System.out.println("-------------ADD DESIRED DATA TO FILE----------------");
            System.out.println("-------------Type \"exit\" to exit the add data area ----------------");

            //Enter the key and values//
            while(true){
                System.out.println("Enter Key");
                String key=scanner.nextLine();
                if(key.equalsIgnoreCase("exit")){
                    break;
                }

                System.out.println("Enter Value");
                String val=scanner.nextLine();

                setData(file,key,val);
            }



            //Call for getting the data using key
            System.out.println("-------------GET THE DESIRED VALUE----------------");
            System.out.println("-------------Enter the key for getting it's corresponding value-------------");
            System.out.println("-------------Type \"exit\" to exit the get data area ----------------\n");

            while(true){

                System.out.println("Enter Key");
                String value=scanner.nextLine();
                if(value.equalsIgnoreCase("exit")){
                    break;
                }
                getData(file,value);

            }

            System.out.println("Thank You!");
            System.out.println("    Credits:\n" +
                    "    Bhuvan Kohli\n" +
                    "    bhuvan.kohli@geminisolutions.com");


        }catch (Exception e){
            System.out.println(e);
        }
    }
}
/*Credits:
    Bhuvan Kohli
    bhuvan.kohli@geminisolutions.com
*/