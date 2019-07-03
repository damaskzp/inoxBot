package inoxBot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

class CsvToHashMapReader {
    private String text;

    String price(String text) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(
                "./src/main/resources/data.csv",
                Charset.forName("windows-1251")));
        String line;
        HashMap<String, String> myMap = new HashMap<>();
        while ((line = br.readLine()) != null) {
            String[] myEntry = line.split(",");
            for (String s : myEntry) {
                String[] arr = s.split(";");
                myMap.put(arr[0], arr[1]);
            }
        }

        if (myMap.get(text.toUpperCase()) == null) {
            return "Наименование отсутствует в базе или неверный ввод. Try again, please.";
        } else {
            return text + " - " + myMap.get(text.toUpperCase()) + "грн.";
        }
    }
}


