package inoxBot;

import java.io.File;

class FotoEquipment {
    private String textInputMsg;

        FotoEquipment(String textInputMsg) {
        this.textInputMsg = textInputMsg;
    }

    File getFoto() {
        return new File
                ("d:\\Foto\\" + textInputMsg.substring(1) + ".jpg");
    }
}

