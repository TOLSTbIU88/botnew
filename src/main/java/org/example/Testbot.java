package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


public class Testbot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);

    if(update.hasMessage()) {
        if (update.getMessage().getText().equals("/start")) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId());
            message.setText("ПРИВЕТ УСЕМ");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if (update.getMessage().getText().equals("keyboards")) {
            try {
                execute(SendMessage.builder().chatId(update.getMessage().getChatId())
                        .text("МАК ФОРЕВЕР")
                        .replyMarkup(InlineKeyboardMarkup.builder()
                                .keyboardRow(List.of(InlineKeyboardButton.builder()
                                        .text("mac грустишка")
                                        .callbackData("DATA1")
                                        .build())).build()).build());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }else if (update.hasCallbackQuery()) {
        if (update.getCallbackQuery().getData().equals("DATA1")) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getCallbackQuery().getMessage().getChatId());
            message.setText("ПОШЛИ БУХАТЬ");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

        }
    }
    }

    @Override
    public String getBotUsername() {
        return "NEWTESTBOT";
    }

    @Override
    public String getBotToken() {
        return "5596084999:AAFkzr1QObRjnvZ1qVwUM9SO-tJxh91IC2g";
    }
}
