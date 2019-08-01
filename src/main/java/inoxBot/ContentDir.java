package inoxBot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ContentDir {
    String listFiles() {
        File dir = new File("d:\\Foto\\");
        List<String> list = new ArrayList<>();
        StringBuilder listFiles = new StringBuilder();
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            list.add(file.getName().substring(0, file.getName().indexOf('.')) + "\n");
        }
        for (String s : list) {
            listFiles.append(s);
        }
        return listFiles.toString();
    }
}
