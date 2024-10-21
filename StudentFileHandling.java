package org.example.newq;

import java.io.*;
import java.util.Scanner;

public class StudentFileHandling {
    public static void createdFile(){
        try {
            File file=new File("Login.txt");

            if(file.createNewFile()){
                System.out.println("File Created: "+file.getName());
            }else {
                System.out.println("File already exist");
            }
        }catch (IOException e){
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public static void writeInFile(String name,String password){
        String ans=name+password;
        try {
            FileWriter fileWriter=new FileWriter("Login.txt");
            fileWriter.write(ans+'\n');
            fileWriter.close();
            System.out.println("Writting complete");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Boolean readFile(String name,String pass){
        String st=name+pass;
        try {
            File file=new File("studentEmailPassword.txt");
            Scanner sc=new Scanner(file);

            while (sc.hasNextLine()){
                String ans=sc.nextLine();
                if(ans.equals(st)){
                    String phoneN=sc.nextLine();
                    String genderN=sc.nextLine();
                    String dateOfBirthN=sc.nextLine();
                    //set info function
                    StudentInfo.sss(name,pass,phoneN,genderN,dateOfBirthN);
                    sc.close();
                    return  true;
                }
            }

            sc.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean readVerify(String id){
        try {
            File file=new File("studentVerify.txt");
            Scanner sc=new Scanner(file);

            while (sc.hasNextLine()){
                String ans=sc.nextLine();
                if(ans.equals(id)){
                    sc.close();
                    return  true;
                }
            }

            sc.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void appenedFile(String fileName,String name,String password,String phoneNumber,String gender,String dateOfBirth){
        String ans=name+password;
        try{
            BufferedWriter out=new BufferedWriter(new FileWriter(fileName,true));

            out.write(ans+'\n'+phoneNumber+'\n'+gender+'\n'+dateOfBirth+'\n');
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void appenedResult(String fileName,String score,String time){
        try{
            BufferedWriter out=new BufferedWriter(new FileWriter(fileName,true));

            out.write(score+"               "+time+'\n');
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
