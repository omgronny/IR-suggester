package requests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import similarity.*;

public class DataSetReader {

    public static void readFile(String filename) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            DataSet.insertGeneral(values[0], Integer.parseInt(values[1]));
            Dictionary.insert(values[0]);
        }

    }

}
