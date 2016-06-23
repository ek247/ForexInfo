package com.web;

import com.forexbackend.CurrentRateRepository;
import com.forexbackend.CurrentRates;
import com.forexbackend.Message;
import com.forexbackend.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

/**
*   RESTful controller that sends the current rate and historical forex rates to those who ask
 */
@RestController
@CrossOrigin(origins = "*")
public class Controller {
    private final CurrentRateRepository repo;
    private final MessageRepository messageRepo;

    @Autowired
    public Controller(CurrentRateRepository repository, MessageRepository repo2)
    {
        repo = repository;
        messageRepo = repo2;
    }

    //Return the most current rate found.
    @RequestMapping("/CurrentRates")
    public CurrentRates getRates()
    {

        return repo.findCurrent();
    }


    //Returns a list of historical rates from given date to current at every given amount of minutes.
    @RequestMapping("/HistoricalRates/{date}/{type}/{granularity}")
    public List<CurrentRates> getRates(@PathVariable String date, @PathVariable String type, @PathVariable final int granularity)//Granularity is in units of time.
    {
        java.sql.Timestamp time = java.sql.Timestamp.valueOf(date+" "+"00:00:00");

        ArrayList<CurrentRates> toRet = new ArrayList(repo.findHistorical(time));
        ArrayList<CurrentRates> toRemove = new ArrayList<CurrentRates>();


        int timeFrame = -1;
        switch (type)
        {
            case "minute":
                timeFrame = Calendar.MINUTE;
                break;
            case "hour":
                timeFrame = Calendar.HOUR_OF_DAY;
                break;
            case "day":
                timeFrame = Calendar.DAY_OF_MONTH;
                break;
            case "year":
                timeFrame = Calendar.YEAR;
                break;
            default:
                return toRemove; //If not valid input return empty list
        }

        for(int i = 0; i < toRet.size(); i++)
        {
            Calendar tmp = Calendar.getInstance();
            tmp.setTimeInMillis(toRet.get(i).getDate().getTime());
            if(tmp.get(timeFrame)%granularity != 0)
                toRemove.add(toRet.get(i));
        }

        toRet.removeAll(toRemove);

        return toRet;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/send", method=RequestMethod.POST)
    public void sendEmail(@RequestBody Message msg)
    {
        messageRepo.save(msg);
    }

    //If you want to send email use this
    private void sendEmailHelper(Message msg) throws MessagingException {
        Properties mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        MimeMessage generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress("email address to send to"));
        generateMailMessage.setSubject("Message from user from angular app");
        String emailBody = msg.getName() +": " + msg.getEmail() + " says: \n" + msg.getMessage();
        generateMailMessage.setContent(emailBody, "text/html");

        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("SMTP address", "email address", "password");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();

    }



}




