   package controller;

   import java.io.Serializable;
   import java.util.regex.Matcher;
   import java.util.regex.Pattern;

   /**
   *
   * @author Max
   */
   
   public class OrderValidator implements Serializable{ 

   private String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";      
   private String namePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";       
   private String passwordPattern = "[a-z0-9]{4,}";  
   private String AddressPattern = "\\d+[ ](?:[A-Za-z0-9.-]+[ ]?)+(?:Avenue|Lane|Road|Boulevard|Drive|Street|Ave|Dr|Rd|Blvd|Ln|St)\\.?";
   private String ProductQuanityPattern = "[1-5]";
   private String TotalPricePattern = "[0.01-9999.99]";
   
   public OrderValidator(){    }       


   public boolean validate(String pattern, String input){       
      Pattern regEx = Pattern.compile(pattern);       
      Matcher match = regEx.matcher(input);       

      return match.matches(); 

   }       

   public boolean checkEmpty(String DOO, String orderId){       

      return  DOO.isEmpty() || orderId.isEmpty();   

   }

   
   public boolean validateProductQuanity(String productQuanity){                       

      return validate(ProductQuanityPattern,productQuanity);   

   }

       
   public boolean validateTotalPrice(String totalPrice){

      return validate(TotalPricePattern,totalPrice); 

   }       
            
}
