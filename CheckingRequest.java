package numbers;


import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class CheckingRequest {
     final static String[] propertiesForNumber = { "EVEN","ODD","BUZZ","DUCK","PALINDROMIC","GAPFUL","SPY","SQUARE","SUNNY", "JUMPING","HAPPY","SAD"};



    public static boolean validNumber(String number){
        try{
            Long.parseLong(number);

        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public static boolean firstValidNumber(long fNumber){
        return fNumber >= 0;
    }
    public static boolean secondValidNumber(long sNumber){
        return sNumber > 0;
    }
    public static boolean notHaveError(String[] request){
        if(request.length == 1) {
            boolean validNumber = validNumber(request[0]);
            if(!validNumber || !firstValidNumber(Long.parseLong(request[0]))){
                // there is error
                printTheFirstParameterWrong();
                return false;
            }
        }else if (request.length == 2){
            boolean validFirstNumber = validNumber(request[0]);
            boolean validSecondNumber = validNumber(request[1]);
            if(!validFirstNumber || !firstValidNumber(Long.parseLong(request[0]))){
                // there is error for 1st para
                printTheFirstParameterWrong();
                return false;
            }
            if(!validSecondNumber || !secondValidNumber(Long.parseLong(request[1]))){
                // there is error for 2nd para
                printTheSecondParameterWrong();
                return false;
            }


        }else{
            // > 2 parameter
            boolean validFirstNumber = validNumber(request[0]);
            boolean validSecondNumber = validNumber(request[1]);
            if(!validFirstNumber || !firstValidNumber(Long.parseLong(request[0]))){
                // there is error for 1st para
                printTheFirstParameterWrong();
                return false;
            }
            if(!validSecondNumber || !secondValidNumber(Long.parseLong(request[1]))){
                // there is error for 2nd para
                printTheSecondParameterWrong();
                return false;
            }
            // check the parameter false
            ArrayList<String> listOfParameter = new ArrayList<>();
            for(int i = 2; i < request.length; i++){
                listOfParameter.add(request[i]);
            }
            if(containsMinusParameter(listOfParameter)){

                // solving the minus character for checking the falseParameter
                ArrayList<String> preSolvingTheMinusList = presolvingTheMinusParameter(listOfParameter);
                //Checking for the wrong parameter
                ArrayList<Integer> listOfFalseParameter = indexOfTheFalseParameter(preSolvingTheMinusList);
                if(!listOfFalseParameter.isEmpty()){
                    if(listOfFalseParameter.size() > 1){
                        System.out.print("The properties [");
                        for(int i = 0; i < listOfFalseParameter.size(); i++){
                            if(i!= listOfFalseParameter.size() - 1){
                                System.out.print(listOfParameter.get(i) + ", ");
                            }else{
                                System.out.print(listOfParameter.get(i));
                            }
                        }
                        System.out.print("] are wrong.");
                        printTheProperties();
                        return false;
                    }else{
                        System.out.print("The property [");
                        System.out.print(listOfParameter.get(listOfFalseParameter.get(0)));
                        System.out.print("] is wrong.");
                        printTheProperties();
                        return false;
                    }
                }
                // done of check the false parameter
                // checking the positive parameter
                if(isMutualProperties2(listOfParameter)){
                    return false;
                }
                // doing the minus

                if(minusTheMutualParameter(listOfParameter)){
                    return false;
                }




            }else{
                // not contain ....
                ArrayList<String> listOfFalseParameter = theFalseParameter(listOfParameter);
                if(!listOfFalseParameter.isEmpty()){
                    if(listOfFalseParameter.size() > 1){
                        System.out.print("The properties [");
                        for(int i = 0 ; i < listOfFalseParameter.size() ; i++){
                            if(i != listOfFalseParameter.size() - 1){
                                System.out.print(listOfFalseParameter.get(i) + ", ");
                            }else{
                                System.out.print(listOfFalseParameter.get(i));
                            }
                        }
                        System.out.print("] are wrong.");
                        printTheProperties();
                        return false;
                    }else{
                        System.out.print("The property [");
                        for(int i = 0; i < listOfFalseParameter.size(); i++){
                            System.out.print(listOfFalseParameter.get(i));
                        }
                        System.out.print("] is wrong.");
                        printTheProperties();
                        return false;
                    }
                }
                // check for mutually  exclusive properties
                if(isMutualProperties(listOfParameter)){
                    return false;
                }
            }

        }

        // there is no error for parameter
        return true;



    }
    public static boolean isMutualProperties2(ArrayList<String> listOfProperties){
        for(int i = 0; i < listOfProperties.size() - 1; i++){
            if(!listOfProperties.get(i).startsWith("-")){
                for(int j = i + 1; j < listOfProperties.size(); j++){
                    if(!listOfProperties.get(j).startsWith("-")){
                        if(mutualProperties(listOfProperties.get(i), listOfProperties.get(j)) ||
                                mutualProperties(listOfProperties.get(j),listOfProperties.get(i))){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean isMutualProperties(ArrayList<String> listOfProperties){

        for(int i = 0; i < listOfProperties.size() -1 ; i++){
            for(int j = i+1; j < listOfProperties.size(); j++){
                if(mutualProperties(listOfProperties.get(i), listOfProperties.get(j)) ||
                mutualProperties(listOfProperties.get(j), listOfProperties.get(i))){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean mutualProperties(String request1, String request2){
        if (request1.compareToIgnoreCase(request2) != 0) {
            if (request1.compareTo("EVEN") == 0 && request2.compareTo("ODD") == 0) {
                System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]\n" +
                        "There are no numbers with these properties.");
                return true;
            }
            if (request1.compareTo("DUCK") == 0 && request2.compareTo("SPY") == 0 ) {
                System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]\n" +
                        "There are no numbers with these properties.");
                return true;
            }
            if (request1.compareTo("SUNNY") == 0 && request2.compareTo("SQUARE") == 0) {
                System.out.println("The request contains mutually exclusive properties: [SUNNY, SQUARE]\n" +
                        "There are no numbers with these properties.");
                return true;
            }
            if(request1.compareTo("HAPPY") == 0 && request2.compareTo("SAD") == 0 ){
                System.out.println("The request contains mutually exclusive properties: [HAPPY, SAD]\n" +
                        "There are no numbers with these properties.");
                return true;
            }
        }
        return false;
    }

    public static boolean isContains(String properties){
        return Arrays.asList(propertiesForNumber).contains(properties);
    }

    public static ArrayList<String> theFalseParameter(ArrayList<String> queryParameter){
        ArrayList<String> listOfFalseParameter = new ArrayList<>();
        for(int i =0; i < queryParameter.size(); i++){
            if(!isContains(queryParameter.get(i))){
                listOfFalseParameter.add(queryParameter.get(i));
            }
        }
        return listOfFalseParameter;
    }
    public static ArrayList<Integer> indexOfTheFalseParameter(ArrayList<String> queryParameter){
        ArrayList<Integer> listOfFalseParameter = new ArrayList<>();
        for(int i = 0 ; i < queryParameter.size(); i++){
            if(!isContains(queryParameter.get(i))){
                listOfFalseParameter.add(i);
            }
        }
        return listOfFalseParameter;
    }


    public static void printTheFirstParameterWrong(){
        System.out.println("The first parameter should be a natural number or zero.");
    }

    public static void printTheSecondParameterWrong(){
        System.out.println("The second parameter should be a natural number or zero.");
    }

    public static void printTheProperties(){
        System.out.println("\nAvailable properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD," +
                " HAPPY, SAD]");
    }

    public static boolean containsMinusParameter(ArrayList<String> listOfProperties){
        for(int i = 0; i< listOfProperties.size(); i++){
            if(listOfProperties.get(i).startsWith("-")){
                return true;
            }
        }
        return false;

    }

    public static ArrayList<String> presolvingTheMinusParameter(ArrayList<String> listOfParameter){
        ArrayList<String> returnList = new ArrayList<>(listOfParameter);
        for(int i = 0; i < returnList.size(); i++){
            if(returnList.get(i).startsWith("-")){
                String newProperty = returnList.get(i).substring(1,returnList.get(i).length());
                returnList.set(i,newProperty);
            }
        }
        return returnList;
    }
    public static boolean areMinusOrOfMutualParameter(String request1, String request2){
        if(request1.compareTo("EVEN") == 0 && request2.compareTo("-EVEN") == 0){
            return true;
        }
        if(request1.compareTo("ODD") == 0 && request2.compareTo("-ODD") == 0){
            return true;
        }
        if(request1.compareTo("DUCK") == 0 && request2.compareTo("-DUCK") == 0){
            return true;
        }
        if(request1.compareTo("SPY") == 0 && request2.compareTo("-SPY") == 0){
            return true;
        }
        if(request1.compareTo("SUNNY") == 0 && request2.compareTo("-SUNNY") == 0){
            return true;
        }
        if(request1.compareTo("SQUARE") == 0 && request2.compareTo("-SQUARE") == 0){
            return true;
        }
        if(request1.compareTo("SAD") == 0 && request2.compareTo("-SAD") == 0){
            return true;
        }
        if(request1.compareTo("HAPPY") == 0 && request2.compareTo("-HAPPY") == 0){
            return true;
        }
        if(request1.compareTo("-EVEN") == 0 && request2.compareTo("-ODD") == 0){
            return true;
        }
        if(request1.compareTo("-DUCK") == 0 && request2.compareTo("-SPY") == 0){
            return true;
        }
        if(request1.compareTo("-SAD") == 0 && request2.compareTo("-HAPPY") == 0){
            return true;
        }
        return false;



    }

    public static boolean minusTheMutualParameter(ArrayList<String> listOfParameter){
        for(int i = 0; i < listOfParameter.size() - 1; i++){
            for(int j = i + 1 ; j < listOfParameter.size(); j++){
                if(areMinusOrOfMutualParameter(listOfParameter.get(i), listOfParameter.get(j)) ||
                areMinusOrOfMutualParameter(listOfParameter.get(j), listOfParameter.get(i))){
                    String prop1 = listOfParameter.get(i);
                    String prop2 = listOfParameter.get(j);
                    System.out.println("The request contains mutually exclusive properties: [" + prop1 + "," + prop2 +
                            "]\n" +
                            "There are no numbers with these properties.");
                    return true;
                }
            }
        }
        return false;
    }










}
