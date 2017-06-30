package Allgemein;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Umfragebot extends TelegramLongPollingBot {
	
   @Override
   public void onUpdateReceived(Update update) {
       // We check if the update has a message and the message has text
       if (update.hasMessage() && update.getMessage().hasText()) {
           // Set variables
           String message_text = update.getMessage().getText();
           long chat_id = update.getMessage().getChatId();

           SendMessage message = new SendMessage().setChatId(chat_id).setText(message_text);
           try {
               sendMessage(message); // Sending our message object to user
           } catch (TelegramApiException e) {
               e.printStackTrace();
           }
       }
   }

   @Override
   public String getBotUsername() {
       // Return bot username
       // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
       return Config.getProperty(Config.Property.BotUser);
   }

   @Override
   public String getBotToken() {
       // Return bot token from BotFather
       return Config.getProperty(Config.Property.BotToken);
   }
   
}
