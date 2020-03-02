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
                                "СП (600-2000)Х(500-700)\n" +
                                "СПП (600-2000)Х(500-700)\n" +
                                "СП2П (600-2000)Х(500-700)\n" +
                                "СП ББ (600-2000)Х(500-700)\n" +
                                "СПП ББ (600-2000)Х(500-700)\n" +
                                "СПО (600-2000)Х(500-700)\n" +
                                "СППО (600-2000)Х(500-700)\n" +
                                "СПВС (250-350) (1000-1800)Х(500-700)\n" +
                                "СППВС (250-350) (1000-1800)Х(500-700)");
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
                                "СТ(4-5) (400-2000)Х(400-600)\n" +
                                "СТП(4-5) (400-2000)Х(400-600)\n" +
                                "СТРШ(4-5) (400-2000)Х(400-600)\n" +
                                "ССП 2Т2С (400-1200)\n" +
                                "ССП 3ТС (400-1200)\n" +
                                "ССП Т3С (400-1200)\n" +
                                "ССП 4С (400-1200)\n" +
                                "ССП 4Т (400-1200)");
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
                                "ПК1 (500-2000)Х(250-350)\n" +
                                "ПК2 (500-2000)Х(250-350)\n" +
                                "ПК3 (500-2000)Х(250-350)\n" +
                                "ПСП ТС (400-1200)\n" +
                                "ПСП СС (400-1200)\n" +
                                "ПСП ТТ (400-1200)");
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
                                "ЗВВП (600-2000)Х(600-1200)\n" +
                                "ЗВВО (1000-2000)Х(1000-2000)");
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
                                "ВС1 (250-400) (600-1000)Х(500-700)\n" +
                                "ВС2 (250-400) (1000-1500)Х(500-700)\n" +
                                "ВС3 (250-400) (1500-1800)Х(500-700)\n" +
                                "ВСП1 (250-350) (600-1000)Х(500-700)\n" +
                                "ВСП2 (250-350) (1000-1500)Х(500-700)\n" +
                                "ВСП3 (250-350) (1500-1800)Х(500-700)\n" +
                                "ВС1 (250-400) (600-1000)Х(500-700)К\n" +
                                "ВС2 (250-400) (1000-1500)Х(500-700)К\n" +
                                "ВС3 (250-400) (1500-1800)Х(500-700)К\n" +
                                "ВСП1 (250-350) (600-1000)Х(500-700)К\n" +
                                "ВСП2 (250-350) (1000-1500)Х(500-700)К\n" +
                                "ВСП3 (250-350) (1500-1800)Х(500-700)К");
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
