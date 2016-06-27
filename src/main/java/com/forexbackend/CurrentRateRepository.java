package com.forexbackend;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by worri on 6/20/2016.
 */

public interface CurrentRateRepository extends CrudRepository<CurrentRates, Integer> {
    //Get most recent price. Does this by checking the max id, as they auto increment and are not changed, thus meaning highest id= newest
    @Query("SELECT p FROM CurrentRates p WHERE p.id=("+ "SELECT max(p.id) FROM p" + ")")
    public CurrentRates findCurrent();

    //Custom query to get rates from date (starting from 00:00:00)
    @Query("SELECT p FROM CurrentRates p WHERE p.date>= :date")
    public List<CurrentRates> findHistorical(@Param("date") Timestamp date);

}

//AND MOD(minute(p.date), :granularity)=0
//, @Param("granularity") int granularity