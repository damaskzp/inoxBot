package inoxBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
            sendPhoto.setPhoto(new FotoEquipment(textInputMsg).getFoto());
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
            sendMessage.setReplyMarkup(new ReplyKeybrd().getKeybrd());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else if (textInputMsg.equals("Столы")) { // клавиатура

            ReplyKeyboardMarkup keyboardMarkup2 = new ReplyKeyboardMarkup();
            keyboardMarkup2.setSelective(true);
            keyboardMarkup2.setResizeKeyboard(true);
            keyboardMarkup2.setOneTimeKeyboard(false);

            List<KeyboardRow> keyboard2 = new ArrayList<>();

            KeyboardRow row = new KeyboardRow();
            KeyboardButton button = new KeyboardButton("Table");
            row.add(button);
            row.add(new KeyboardButton("СПП"));
            keyboard2.add(row);

            KeyboardRow row2 = new KeyboardRow();
            row2.add("СП2П");
            row2.add("Далее");
            keyboard2.add(row2);

            keyboardMarkup2.setKeyboard(keyboard2);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Выбери!");
            sendMessage.enableMarkdown(false);
            sendMessage.setReplyMarkup(keyboardMarkup2);

            try {
                execute(sendMessage.setText(sendMessage.getText() + " 123"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (textInputMsg.equals("/ex")) {
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            List<InlineKeyboardButton> keyboardRow1 = new ArrayList<>();
            keyboardRow1.add(new InlineKeyboardButton().setText("Hello").setCallbackData("Bye"));

            List<InlineKeyboardButton> keyboardRow2 = new ArrayList<>();
            keyboardRow2.add(new InlineKeyboardButton().setText("Fuck").setCallbackData("Off"));

            List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
            rowList.add(keyboardRow1);
            rowList.add(keyboardRow2);

            inlineKeyboardMarkup.setKeyboard(rowList);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Hello!!!").setReplyMarkup(inlineKeyboardMarkup);

            try {
                execute(sendMessage);
                //.setText(update.getCallbackQuery().getData())
                //  .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            try {
                execute(new SendMessage()
                        .setText(update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
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
