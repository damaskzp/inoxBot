package inoxBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.io.IOException;

public class InoxBot extends TelegramLongPollingBot {

    private CsvToHashMapReader csv;

    public InoxBot() throws IOException {
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
                        "Привет!!!\n" +
                                "Я Ваш помощник для поиска цены\n" +
                                "на интересующее вас оборудование.\n" +
                                "Чтобы увидеть пример ввода\n" +
                                "запроса, отправьте сообщение\n" +
                                "с текстом: /help\n" +
                                "Удачи!!!");
                execute(sendMessage);
            } catch (Exception e) {
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
