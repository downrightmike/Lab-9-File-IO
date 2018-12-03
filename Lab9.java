/*******************************************************************************************************************
 * Lab9.java
 * by Downrightmike
 * CIS 131
 *******************************************************************************************************************/
import java.io.*;
import java.util.*;

public class Lab9 {
  //Constants
  final static double OVER_TIME = 1.75;

  public static void main(String[] args) throws IOException {
    useBufferedReader();   
  }
  //-------------------------------------------------------------------------------------------------------------------  
  public static void useBufferedReader() throws IOException {
   

    FileWriter  fw = new FileWriter("Lab9OutputFile.txt");
    PrintWriter pw = new PrintWriter(fw);
    pw.println("------------------------------ PAYROLL REPORT ------------------------------------------");
    pw.println("Employee       Employee        Pay         Hours         Regular    Overtime      Total");
    pw.println(" Number          Name          Rate        Worked        Pay          Pay          Pay");
    pw.println("--------   ---------------    ------      --------      ---------  ----------     ------");
   
    printHeader();
try{
    java.io.File file = new java.io.File("payroll.txt");
    //Read data from file
    Scanner input = new Scanner(file);     

    
try{
    while(input.hasNext()) {
      int EmpID = input.nextInt();
      String lastName = input.next();
      String firstName = input.next();
      Double hoursWorked = input.nextDouble();
      Double hourlyRate = input.nextDouble();
      double[] pay = {1.0, 2.0, 3.0};
      calcPay(hoursWorked, hourlyRate, pay);
      //System.out.println(pay[0]);
      //System.out.println(EmpID + lastName + firstName + hoursWorked + hourlyRate + pay[0] + pay[1] + pay[2]);
      writeFile(EmpID, lastName, firstName, hoursWorked, hourlyRate, pay, pw);
    }    
  }
  catch(InputMismatchException ex)
  {
    System.out.println("There may have been a problems such as reading an int when expecting a non-numeric value. Please check your input file and try again.");
    System.out.println("Now exiting the program.");
    //close the files for exception
    //close the files
    input.close(); 
    pw.close();   
    System.exit(-1);
  }
  
    //close the files normally
    input.close(); 
    pw.close(); 
    
  }
  catch(FileNotFoundException ex){
    System.out.println("Please verify a file named payroll.txt is in the local directory before proceeding.");
    System.out.println("Now exiting the program.");
    System.exit(-1);
  }
  }
  public static double[] calcPay(double hoursWorked, double hourlyRate, double[] pay){
     double regPay = 0.0;
     double overPay = 0.0;
     double totalPay = 0.0;
    if(hoursWorked > 40.0){
      double overTime = hoursWorked - 40.0;
      regPay = 40.0 * hourlyRate;
      overPay = overTime * hourlyRate * OVER_TIME;
      totalPay = overPay + regPay;
      pay[0] = regPay;
      pay[1] = overPay;
      pay[2] = totalPay;
      return pay;
    } else {
      totalPay = (hoursWorked * hourlyRate);
      pay[0] = totalPay;
      pay[1] = 0.0;
      pay[2] = totalPay;
      return pay;
    }
  }
  public static void writeFile(int EmpID, String lastName, String firstName, double hoursWorked, double hourlyRate, double[] pay, PrintWriter pw){
    //System.out.println(EmpID + lastName + firstName + hoursWorked + hourlyRate + pay[0] + pay[1] + pay[2]); //testing
    
    //pw.println("TEST");
    pw.printf("\n%5d %20s %8.2f %12.2f %14.2f %12.2f %11.2f"+ "\n", EmpID, firstName +" "+ lastName, hourlyRate, hoursWorked, pay[0], pay[1], pay[2]);
    pw.println();
    System.out.printf("%5d %20s %8.2f %12.2f %14.2f %12.2f %11.2f \n", EmpID, firstName +" "+ lastName, hourlyRate, hoursWorked, pay[0], pay[1], pay[2]);
  }
  public static void printHeader(){
    System.out.println("------------------------------ PAYROLL REPORT ------------------------------------------");
    System.out.println("Employee       Employee        Pay         Hours         Regular    Overtime      Total");
    System.out.println(" Number          Name          Rate        Worked        Pay          Pay          Pay");
    System.out.println("--------   ---------------    ------      --------      ---------  ----------     ------");

  }

}