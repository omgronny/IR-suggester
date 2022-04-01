import finders.Finder;
import requests.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String filename = "requests.txt";



        try {
            DataSetReader.readFile(filename);
        } catch (IOException e) {
            System.out.println("Ошибкак в файле");
            return;
        }

        System.out.println("Чтобы выйти введите \'\\q\'");

        while (true) {

            System.out.print("Введите запрос \n> ");
            String request = in.nextLine();

            if (request.equals("\\q")) {
                return;
            }

            Finder.printSuggestions(request, 0);

//        if (args.length != 0) {
//            DataSetReader.readFile(args[0]);
//        } else {
//            System.err.println("No such file");
//        }
            
        }

    }

}