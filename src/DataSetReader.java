import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import requests.DataSet;
import similarity.*;

public class DataSetReader {

    public static void readFile(String filename) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line;
        while ((line = br.readLine()) != null) {

            String[] values = line.split(",");
            if (values.length > 1) {
                DataSet.insertGeneral(values[0], Double.parseDouble(values[1]));
            } else {
                DataSet.insertGeneral(values[0], 0d);
            }

            for (String s : values[0].split(" ")) {
                Dictionary.insert(s);
            }

        }

    }

}
