//package inoxBot;
//
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class ReplyKeybrd {
//    ReplyKeyboardMarkup getKeybrd() {
//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//        keyboardMarkup.setSelective(true);
//        keyboardMarkup.setResizeKeyboard(true);
//        keyboardMarkup.setOneTimeKeyboard(false);
//
//        List<KeyboardRow> keyboard = new ArrayList<>();
//
//        KeyboardRow row = new KeyboardRow();
//        row.add("Столы");
//        row.add("Ванны");
//        keyboard.add(row);
//
//        KeyboardRow row2 = new KeyboardRow();
//        row2.add("Столы2");
//        row2.add("Зонт");
//        keyboard.add(row2);
//
//        keyboardMarkup.setKeyboard(keyboard);
//        return keyboardMarkup;
//    }
//}
//
