package inoxBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class InoxBot extends TelegramLongPollingBot {

    private CsvToHashMapReader csv;

    InoxBot() throws IOException {
        csv = new CsvToHashMapReader();
    }

    public void onUpdateReceived(Update update) {

        String textInputMsg = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        if (textInputMsg.equals("/help")) {
            try {
                SendMessage sendMessage = new SendMessage(
                        chatId,
                        "Пример ввода запроса:\n" +
                                "СПП(СП, СП2П) 1000х600\n" +
                                "СПП(СП, СП2П) ББ 1000х600\n" +
                                "ПК1(2, 3) 1000х600\n" +
                                "СТ4(5) 1000х500\n" +
                                "Регистр знаков не важен.");
                execute(sendMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (textInputMsg.equals("/start")) {
            try {
                SendMessage sendMessage = new SendMessage(
                        chatId,
                        "Чтобы увидеть пример ввода\n" +
                                "запроса, отправьте сообщение\n" +
                                "с текстом: /help\n" +
                                "Удачи!!!");
                execute(sendMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (textInputMsg.substring(0, 1).toUpperCase().equals("Ф")) {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setPhoto(new File
                    ("d:\\Foto\\" + textInputMsg.substring(1) + ".jpg"));
            try {
                execute(sendPhoto);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (textInputMsg.equals("/list")) {
            ContentDir contentDir = new ContentDir();
            try {
                SendMessage sendMessage = new SendMessage(
                        chatId, contentDir.listFiles());
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else if (textInputMsg.equals("/key")) { //клавиатура
            SendMessage sendMessage = new SendMessage(
                    chatId, "Hello!!!");
            sendMessage.enableMarkdown(false);

            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            keyboardMarkup.setSelective(true);
            keyboardMarkup.setResizeKeyboard(true);
            keyboardMarkup.setOneTimeKeyboard(false);

            List<KeyboardRow> keyboard = new ArrayList<>();

            KeyboardRow row = new KeyboardRow();
            row.add("СПП 1000х600");
            row.add("Ванны");
            keyboard.add(row);

            KeyboardRow row2 = new KeyboardRow();
            row2.add("Столы2");
            row2.add("Ванны2");
            keyboard.add(row2);

            keyboardMarkup.setKeyboard(keyboard);
            sendMessage.setReplyMarkup(keyboardMarkup);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            try {
                SendMessage sendMessage = new SendMessage(
                        chatId, csv.price(textInputMsg));
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "Inox_bot";
    }

    public String getBotToken() {
        return "897152664:AAGQsSsH1yx0U1IZaE_FFIcW03oNvQwuaII";
    }
}
