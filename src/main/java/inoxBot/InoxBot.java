package inoxBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

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
                        "Выберите наименование группы товаров,\n" +
                                "которое вас интересует:\n" +
                                "/table - Столы\n" +
                                "/rack - Стеллажи\n" +
                                "/shelf - Полки\n" +
                                "/hood - Зонты вытяжные\n" +
                                "/sink - Ванны\n" +
                                "/list - Перечень фото оборудования");
                execute(sendMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (textInputMsg.equals("/table")) {
            try {
                SendMessage sendMessage = new SendMessage(
                        chatId,
                        "Столы производственные\n" +
                                "(пример ввода; регистр символов не важен):\n" +
                                "СП 600Х600\n" +
                                "СПП 600Х600\n" +
                                "СП2П 600Х600\n" +
                                "СП ББ 600Х600\n" +
                                "СПП ББ 600Х600\n" +
                                "СПО 600Х600\n" +
                                "СППО 600Х600\n" +
                                "СПВС 300 1000Х600\n" +
                                "СППВС 300 1000Х700");
                execute(sendMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (textInputMsg.equals("/rack")) {
            try {
                SendMessage sendMessage = new SendMessage(
                        chatId,
                        "Стеллажи производственные\n" +
                                "(пример ввода; регистр символов не важен):\n" +
                                "СТ4 700Х500\n" +
                                "СТ5 700Х500\n" +
                                "СТП4 700Х500\n" +
                                "СТП5 700Х500\n" +
                                "СТРШ4 700Х500\n" +
                                "СТРШ5 700Х600\n" +
                                "ССП 2Т2С 600\n" +
                                "ССП 3ТС 600\n" +
                                "ССП Т3С 600\n" +
                                "ССП 4С 600\n" +
                                "ССП 4Т 600");
                execute(sendMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (textInputMsg.equals("/shelf")) {
            try {
                SendMessage sendMessage = new SendMessage(
                        chatId,
                        "Полки кухонные:\n" +
                                "(пример ввода; регистр символов не важен):\n" +
                                "ПК1 500Х250\n" +
                                "ПК2 500Х250\n" +
                                "ПК3 500Х250\n" +
                                "ПСП ТС 600\n" +
                                "ПСП СС 600\n" +
                                "ПСП ТТ 600");
                execute(sendMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (textInputMsg.equals("/hood")) {
            try {
                SendMessage sendMessage = new SendMessage(
                        chatId,
                        "Зонты вытяжные\n" +
                                "(пример ввода; регистр символов не важен):\n" +
                                "ЗВВП 600Х600\n" +
                                "ЗВВО 1000Х1000");
                execute(sendMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (textInputMsg.equals("/sink")) {
            try {
                SendMessage sendMessage = new SendMessage(
                        chatId,
                        "Ванны моечные\n" +
                                "(пример ввода; регистр символов не важен):\n" +
                                "ВС1 300 600Х600\n" +
                                "ВС2 300 1000Х600\n" +
                                "ВС3 300 1500Х600\n" +
                                "ВСП1 300 600Х600\n" +
                                "ВСП2 300 1000Х600\n" +
                                "ВСП3 300 1500Х600\n" +
                                "ВС1 300 600Х600К\n" +
                                "ВС2 300 1000Х600К\n" +
                                "ВС3 300 1500Х600К\n" +
                                "ВСП1 300 600Х600К\n" +
                                "ВСП2 300 1000Х600К\n" +
                                "ВСП3 300 1500Х600К");
                execute(sendMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (textInputMsg.equals("/start")) {
            try {
                SendMessage sendMessage = new SendMessage(
                        chatId,
                        "Чтобы начать работу\n" +
                                "нажмите на /help\n");
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
