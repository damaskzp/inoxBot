package inoxBot;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

class CsvToHashMapReader {
    private final HashMap<String, String> myMap;

    public CsvToHashMapReader() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/data1.csv"),
                Charset.forName("windows-1251")));
        String line;
        myMap = new HashMap<>();
        while ((line = br.readLine()) != null) {
            String[] myEntry = line.split(",");
            for (String s : myEntry) {
                String[] arr = s.split(";");
                myMap.put(arr[0], arr[1]);
            }
        }
    }

    String price(String textInputMsg) {
        if (myMap.get(textInputMsg.toUpperCase()) == null) {
            return "Наименование отсутствует\n" +
                    "в базе или неверный ввод.\n" +
                    "Try again, please. /help";
        } else {
            return textInputMsg + " - " + myMap.get(textInputMsg.toUpperCase()) + "грн.";
        }
    }


}


