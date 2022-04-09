package numbers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public final class Number {



    public static boolean isOdd(long number){
        return number % 2 == 1;
    }
    public static boolean isEven(long number){
        return number % 2 == 0;
    }
    public static boolean isBuzz(long number){
        return (number % 10) == 7 || (number % 7) == 0;
    }

    public static boolean isDuck(long number){
        return Long.toString(number).substring(1).contains("0");
    }
    public static boolean isPalindromic(long number){
        StringBuilder reverseString = new StringBuilder(Long.toString(number));
        reverseString.reverse();

        return reverseString.compareTo(new StringBuilder(Long.toString(number))) == 0;
    }
    public static boolean isGapful(long number){
        if (!(number < 100)) {
            String numberToString = Long.toString(number);
            String firstLastDigit = numberToString.charAt(0) + String.valueOf(numberToString.charAt(numberToString.length()-1));
            long divisor = Long.parseLong(firstLastDigit);
            return number % divisor == 0;
        }
        return false;
    }
    public static boolean isSpy(long number){ // if the sum of all the digits == the product of all digits
        long sum = 0;
        long product = 1;
        while (number != 0) {
            long digit = number % 10;
            sum += digit;
            product *= digit;
            number /= 10;
        }
        return sum == product;
    }

    public static boolean isSquare(long number){
        return number == ((long) Math.sqrt(number) * Math.sqrt(number));
    }

    public static boolean isSunny(long number){
        if(isSquare(number + 1)){
            return true;
        }
        return false;
    }
    public static boolean isJumping(long number){
        String value = String.valueOf(number);
        ArrayList<String> digits = new ArrayList<>();
        for(int i = 0; i < value.length(); i++){
            digits.add(String.valueOf(value.charAt(i)));
        }
        for(int i = 0; i < digits.size() - 1; i++){
            long fDigits = Long.parseLong(digits.get(i));
            long sDigits = Long.parseLong(digits.get(i+1));
            if((long)Math.abs(fDigits - sDigits) != 1){
                return false;
            }
        }
        return true;
    }
    public static boolean isHappy(long number){
        HashSet<Long> st = new HashSet<>();
        st.add(number);

        while(true){
            if (number == 1){
                return true;
            }
            number = numSquareSum(number);

            if((st.contains(number) && number != (long)st.toArray()[st.size() - 1])){
                return false;
            }
            st.add(number);
        }
    }
    public static long numSquareSum(long number){
        long sq = 0;
        while(number > 0){
            long digit = number % 10;
            sq += digit * digit;
            number = number / 10;
        }
        return sq;
    }
    public static boolean checkHappy(long number) {

        ArrayList<Long> sequence = new ArrayList<>();
        sequence.add(number);

        if (number != 1) {
            for (int i = 0; i < sequence.size(); i++) {
                long sum = 0;
                if (sequence.get(i) == 1) { return true; }

                long check = sequence.get(i);
                while (check != 0) {
                    sum += Math.pow(check % 10, 2);
                    check /= 10;
                }

                if (sum == 145 || sum == 3 || sum == 4 || sum == 5 || sum == 6) { return false; }

                sequence.add(sum);
            }
        }
        return true;
    }
    public static boolean checkSad(long number){
        if(number == 1){
            return false;
        }else{
            if(checkHappy(number)){
                return false;
            }else{
                return true;
            }
        }
    }

    public static void oneNumberOption(long number){
        System.out.println("Properties of " + number);
        System.out.println("        buzz: " + isBuzz(number));
        System.out.println("        duck: " + isDuck(number));
        System.out.println(" palindromic: " + isPalindromic(number));
        System.out.println("      gapful: " + isGapful(number));
        System.out.println("      square: " + isSquare(number));
        System.out.println("         spy: " + isSpy(number));
        System.out.println("       sunny: " + isSunny(number));
        System.out.println("        even: " + isEven(number));
        System.out.println("         odd: " + isOdd(number));
        System.out.println("     jumping: " + isJumping(number));
        System.out.println("       happy: " + checkHappy(number));
        System.out.println("         sad: " + checkSad(number));

    }

    public static void twoNumberOption(long number, long times){
        long counts = 0;
        while(counts < times){
            List<String> propertiesNumber = numberProperties(number);
            System.out.print(number + " is ");
            for(int i = 0; i < propertiesNumber.size() ; i++){
                if(i != propertiesNumber.size() - 1){
                    System.out.print(propertiesNumber.get(i) + ", ");
                }else System.out.print(propertiesNumber.get(i));
            }
            System.out.println();
            ++number;
            ++counts;
        }
    }


    public static List<String> numberProperties(long number){
        List<String> propertiesOfNumber = new ArrayList<>();
        if(isBuzz(number)){
            propertiesOfNumber.add("buzz");
        }
        if(isDuck(number)){
            propertiesOfNumber.add("duck");
        }
        if(isPalindromic(number)){
            propertiesOfNumber.add("palindromic");
        }
        if(isGapful(number)){
            propertiesOfNumber.add("gapful");
        }
        if(isSpy(number)){
            propertiesOfNumber.add("spy");
        }
        if(isSunny(number)){
            propertiesOfNumber.add("sunny");
        }
        if(isSquare(number)){
            propertiesOfNumber.add("square");
        }
        if(isEven(number)) propertiesOfNumber.add("even");
        if(isOdd(number)) propertiesOfNumber.add("odd");
        if(isJumping(number)) propertiesOfNumber.add("jumping");
        if(checkHappy(number)) propertiesOfNumber.add("happy");
        if(checkSad(number)) propertiesOfNumber.add("sad");
        return propertiesOfNumber;
    }
    public static boolean containTheProperties(long number, ArrayList<String> listOfProperties){
        List<String> numberProperties = numberProperties(number);
        for(int i = 0; i < listOfProperties.size(); i++){
            if(!numberProperties.contains(listOfProperties.get(i).toLowerCase())){
                return false;
            }
        }
        return true;
    }
    public static boolean checkingContainPositiveOrNegativeProperties(long number, ArrayList<String> listOfProperties){
        List<String> numberProperties = numberProperties(number);
        //solving the firstthing not contains the positive properties
        for(int i = 0; i < listOfProperties.size(); i++){
            if(listOfProperties.get(i).startsWith("-")){
                String subString = listOfProperties.get(i).substring(1,listOfProperties.get(i).length());
                subString = subString.toLowerCase();
                if(numberProperties.contains(subString)){
                    // return false;
                    return false;
                }
            }else{
                if(!numberProperties.contains(listOfProperties.get(i).toLowerCase())){
                    return false;
                    // return false;
                }
            }
        }
        // return true if they don't have negative properties and have positive properties
        return true;
    }
    public static void greaterThanTwoOptions(long number, long times, ArrayList<String> properties){
        int counts = 0;
        ArrayList<Long> listOfNumber  = new ArrayList<>();
        while(counts < times){
            if(containTheProperties(number, properties)){
                listOfNumber.add(number);
                ++number;
                ++counts;
            }else{
                ++number;
            }
        }
        for(int i = 0 ; i < listOfNumber.size(); i++){
            System.out.print(listOfNumber.get(i) + " is ");
            List<String> numberProperties = numberProperties(listOfNumber.get(i));
            for(int j = 0; j < numberProperties.size(); j++){
                if(j != numberProperties.size() - 1){
                    System.out.print(numberProperties.get(j) + ", ");
                }else{
                    System.out.print(numberProperties.get(j));
                }
            }
            System.out.println();
        }
    }
    public static void greaterThanTwoOptionsAndHaveMinusProperties(long number, long times, ArrayList<String> properties){
        int counts = 0;
        ArrayList<Long> listNumber = new ArrayList<>();
        while(counts < times){
            if(checkingContainPositiveOrNegativeProperties(number, properties)){
                listNumber.add(number);
                ++counts;
                ++number;
            }else{
                ++number;
            }
        }
        for(int i = 0; i < listNumber.size(); i++){
            System.out.print(listNumber.get(i) + " is ");
            List<String> numberProperties = numberProperties(listNumber.get(i));
            for(int j = 0; j < numberProperties.size(); j++){
                if(j != numberProperties.size() - 1){
                    System.out.print(numberProperties.get(j) + ", ");
                }else{
                    System.out.print(numberProperties.get(j));
                }
            }
            System.out.println();
        }
    }

    public static void doTheNumberRequest(String[] request){
        if(request.length == 1){
            // do the option one
            long number = Long.parseLong(request[0]);
            oneNumberOption(number);
        }else if(request.length == 2){
            long number = Long.parseLong(request[0]);
            long times = Long.parseLong(request[1]);
            twoNumberOption(number,times);
        }else{
            // request > 2
            long number = Long.parseLong(request[0]);
            long times = Long.parseLong(request[1]);
            ArrayList<String> listProperties = new ArrayList<>();
            for(int i = 2; i < request.length; i++){
                listProperties.add(request[i]);
            }
            // have the request
            // if have the minus request do something about it
            if(CheckingRequest.containsMinusParameter(listProperties)){
                //make sure that does have the minus properties in the number's properties

                greaterThanTwoOptionsAndHaveMinusProperties(number,times,listProperties);
            }else{
                greaterThanTwoOptions(number,times, listProperties);
            }




        }

    }






}
