   package controller;

   import java.io.Serializable;
   import java.util.regex.Matcher;
   import java.util.regex.Pattern;

   public class paymentvalidator implements Serializable{ 
  
   private final String Payment_DATEPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";       
   private final String Payment_IDPattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";        
          
              
   public paymentvalidator(){    }       

   public boolean validate(String pattern, String input){        
      Pattern regEx = Pattern.compile(pattern);        
      Matcher match = regEx.matcher(input);        
      return match.matches();  
   }        

   public boolean checkEmpty(String Payment_ID, String Payment_DATE){        
      return  Payment_ID.isEmpty() || Payment_DATE.isEmpty();    
   }
    
   public boolean validatePAYMENTDATE(String Payment_DATE){                        
      return validate(Payment_DATEPattern,Payment_DATE);    
   }
        
   public boolean validatePAYMENTID(String Payment_ID){
      return validate(Payment_IDPattern,Payment_ID); 
   }        
   

   } 