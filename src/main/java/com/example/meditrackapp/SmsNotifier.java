package com.example.meditrackapp;

public class SmsNotifier implements Notifier {
    public void send(String report, String recipient) {
        System.out.println("SMS '" + report + "' to " + recipient);
    }
}
