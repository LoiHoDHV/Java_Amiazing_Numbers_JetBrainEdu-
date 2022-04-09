package numbers;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        menu();
        boolean stop = false;
        while (!stop) {
            String[] request = requestInput();
            // check to exit program or not
            boolean validInput = CheckingRequest.notHaveError(request);
            if(!validInput){
                continue;
            }else{
                if(request.length == 1 && CheckingRequest.validNumber(request[0]) && Long.parseLong(request[0]) == 0){
                    break;
                }else{
                    // do the thing in here
                    Number.doTheNumberRequest(request);
                }
            }




        }
        System.out.println("Good bye!");
    }

        public static void menu () {
            System.out.println("Welcome to Amazing Numbers!");
            System.out.println("\nSupported requests:");
            System.out.println("- enter a natural number to know it's properties;");
            System.out.println("- enter two natural numbers to obtain the properties of the list:");
            System.out.println("  * the first parameter represents a starting number;");
            System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
            System.out.println("- two natural numbers and properties to search for;");
            System.out.println("- a property preceded by minus must not be present in numbers;");
            System.out.println("- separate parameters with one space;");
            System.out.println("- enter 0 to exit.");
        }

        public static String[] requestInput () {

            System.out.print("\nEnter a request: ");
            String[] request = scanner.nextLine().split(" ");
            ArrayList<String> renewRequest = new ArrayList<>();
            for (int i = 0; i < request.length; i++) {
                if (request[i].compareToIgnoreCase("") != 0) {
                    renewRequest.add(request[i]);
                }
            }
            String[] finalReturn = new String[renewRequest.size()];
            for (int i = 0; i < finalReturn.length; i++) {
                finalReturn[i] = renewRequest.get(i);
            }
            for (int i = 0; i < finalReturn.length; i++) {
                finalReturn[i] = finalReturn[i].toUpperCase(Locale.ROOT);
            }
            System.out.println();

            return finalReturn;

        }


}

