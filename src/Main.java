import finders.Finder;
import requests.DataSet;
import requests.Request;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        //String filename = "requests.txt";

        if (args.length != 0) {
            try {
                DataSetReader.readFile(args[0]);
            } catch (IOException e) {
                System.err.println("No such file");
                return;
            }
        } else {
            System.err.println("Укажите имя файла");
            return;
        }

//        try {
//            DataSetReader.readFile(filename);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("Чтобы выйти введите \'\\q\'");

        while (true) {

            System.out.print("Введите запрос \n> ");
            String request = in.nextLine();

            switch (request) {
                case "\\q":
                    return;
                case "\\prevs":
                    for (Request req : DataSet.getUserRequests()) {
                        System.out.println(req.getRequest());
                    }
                    break;
                default:
                    Finder.printSuggestions(request, 0);
            }

        }

    }

}