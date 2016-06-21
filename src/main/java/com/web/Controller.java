package com.web;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.forexbackend.CurrentRateRepository;
import com.forexbackend.CurrentRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
*   RESTful controller that sends the current rate and historical forex rates to those who ask
 */
@RestController
public class Controller {
    private final CurrentRateRepository repo;

    @Autowired
    public Controller(CurrentRateRepository repository)
    {
        repo = repository;
    }

    //Return the most current rate found.
    @ResponseBody
    @RequestMapping("/CurrentRates")
    public CurrentRates getRates()
    {

        return repo.findCurrent();
    }


    //Returns a list of historical rates from given date to current at every given amount of minutes.
    @ResponseBody
    @RequestMapping("/HistoricalRates/{date}/{granularity}")
    public List<CurrentRates> getRates(@PathVariable String date, @PathVariable final int granularity)//Granularity is in minutes
    {
        java.sql.Timestamp time = java.sql.Timestamp.valueOf(date+" "+"00:00:00");

        ArrayList<CurrentRates> toRet = new ArrayList(repo.findHistorical(time));
        ArrayList<CurrentRates> toRemove = new ArrayList<CurrentRates>();

        for(int i = 0; i < toRet.size(); i++)
        {
            Calendar tmp = Calendar.getInstance();
            tmp.setTimeInMillis(toRet.get(i).getDate().getTime());
            if(tmp.get(Calendar.MINUTE)%granularity != 0)
                toRemove.add(toRet.get(i));
        }

        toRet.removeAll(toRemove);

        return toRet;
    }

}
