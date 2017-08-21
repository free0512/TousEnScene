package ex.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestFormaterDate4 {

  public static void main(String[] args) {
    Date date = null ;
    SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy") ;
    String date1 = "08/12/1966" ;
    String date2 = "21/12/1967" ;
    String date3 = "16/01/1997" ;
    String date4 = "24/01/2006" ;
    try {
    	System.out.println("Said : " + sdf.parse(date1));
    	System.out.println("Touria : " + sdf.parse(date2));
    	System.out.println("Maroua : " + sdf.parse(date3));
    	System.out.println("Amine : " + sdf.parse(date4));
    } catch (ParseException e) {e.printStackTrace();}
  }

}