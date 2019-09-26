package client;

import server.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TimeBot extends Client {

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Hello! I am a TimeBot!");
            sendTextMessage("I understand such commands: time, date, day, month, year, hour, minutes, seconds.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String[] messageSplit = message.split(": ");
            if (messageSplit.length == 2) {
                String messageAuthor = messageSplit[0];
                String messageText = messageSplit[1].toLowerCase();
                String dateFormat = null;
                switch (messageText) {
                    case "date" : {
                        dateFormat = "d.MM.YYYY";
                        break;
                    }
                    case "day" : {
                        dateFormat = "d";
                        break;
                    }
                    case "month" : {
                        dateFormat = "MMMM";
                        break;
                    }
                    case "year" : {
                        dateFormat = "YYYY";
                        break;
                    }
                    case "time" : {
                        dateFormat = "H:mm:ss";
                        break;
                    }
                    case "hour" : {
                        dateFormat = "H";
                        break;
                    }
                    case "minutes" : {
                        dateFormat = "m";
                        break;
                    }
                    case "seconds" : {
                        dateFormat = "s";
                        break;
                    }
                }
                if (dateFormat != null) {
                    String reply = String.format("Information for %s: %s", messageAuthor, new SimpleDateFormat(dateFormat).format(Calendar.getInstance().getTime()));
                    sendTextMessage(reply);
                }
            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        String botName = "time_bot";
        return botName;
    }

    public static void main (String[] args) {
        TimeBot botClient = new TimeBot();
        botClient.run();
    }
}


