package com.web;

import com.forexbackend.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

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
    @RequestMapping("/HistoricalRates/{date}/{rate}/{type}/{granularity}")
    public List<Rates> getRates(@PathVariable String date, @PathVariable String rate, @PathVariable String type, @PathVariable final int granularity)//Granularity is in units of time.
    {
        java.sql.Timestamp time = java.sql.Timestamp.valueOf(date+" "+"00:00:00");

        ArrayList<CurrentRates> toRet = new ArrayList(repo.findHistorical(time));
        ArrayList<CurrentRates> toRemove = new ArrayList<CurrentRates>();
        ArrayList<Rates> rates = new ArrayList<>();


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
                return rates; //If not valid input return empty list
        }

        for(int i = 0; i < toRet.size(); i++)
        {
            Calendar tmp = Calendar.getInstance();
            tmp.setTimeInMillis(toRet.get(i).getDate().getTime());
            if(tmp.get(timeFrame)%granularity != 0)
                toRemove.add(toRet.get(i));
        }

        toRet.removeAll(toRemove);

        for(CurrentRates r : toRet)
        {
            Rates tmp = new Rates();
            tmp.setName(rate);
            switch (rate)
            {
                case("EURUSD"):
                    tmp.setAsk(r.getEURUSDAsk());
                    tmp.setBid(r.getEURUSDBid());
                    break;
                case("USDJPY"):
                    tmp.setAsk(r.getUSDJPYAsk());
                    tmp.setBid(r.getUSDJPYBid());
                    break;
                case("GBPUSD"):
                tmp.setAsk(r.getGBPUSDAsk());
                tmp.setBid(r.getGBPUSDBid());
                break;
                case("USDCHF"):
                    tmp.setAsk(r.getUSDCHFAsk());
                    tmp.setBid(r.getUSDCHFBid());
                    break;
                case("EURCHF"):
                    tmp.setAsk(r.getEURCHFAsk());
                    tmp.setBid(r.getEURCHFBid());
                    break;
                case("AUDUSD"):
                    tmp.setAsk(r.getAUDUSDAsk());
                    tmp.setBid(r.getAUDUSDBid());
                    break;
                case("USDCAD"):
                    tmp.setAsk(r.getUSDCADAsk());
                    tmp.setBid(r.getUSDCADBid());
                    break;
                case("NZDUSD"):
                    tmp.setAsk(r.getNZDUSDAsk());
                    tmp.setBid(r.getNZDUSDBid());
                    break;
                case("EURJPY"):
                    tmp.setAsk(r.getEURJPYAsk());
                    tmp.setBid(r.getEURJPYBid());
                    break;
                case("GBPJPY"):
                    tmp.setAsk(r.getGBPJPYAsk());
                    tmp.setBid(r.getGBPJPYBid());
                    break;
                case("EURGBP"):
                    tmp.setAsk(r.getEURGBPAsk());
                    tmp.setBid(r.getEURGBPBid());
                    break;
            }

            tmp.setTime(new Date(r.getDate().getTime()));
            rates.add(tmp);
        }

        return rates;
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




