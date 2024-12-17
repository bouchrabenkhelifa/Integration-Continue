package com.example.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class EmailService {

    private static final String API_KEY = "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";
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.err.println("Usage: EmailService <toEmail> <subject> <body>");
            return;
        }
        String toEmail = args[0];
        String subject = args[1];
        String body = args[2];

        sendEmail(toEmail, subject, body);
    }

    public static void sendEmail(String toEmail, String subject, String body) throws IOException {
        Email from = new Email("lb_benkhelifa@esi.dz");
        Email to = new Email(toEmail);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Body: " + response.getBody());
            System.out.println("Headers: " + response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
