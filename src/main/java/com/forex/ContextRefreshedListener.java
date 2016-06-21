package com.forex;

        import com.forexbackend.CurrentRateRepository;
        import com.forexbackend.CurrentRateThread;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.ApplicationListener;
        import org.springframework.context.event.ContextRefreshedEvent;
        import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent>{

    private CurrentRateRepository repo;
    private boolean started = false;

    @Autowired
    public void setCurrentRateRepository(CurrentRateRepository repository) {
        this.repo = repository;
    }

    //On start start getting rates
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //Only start the thread once, don't need to get the rates biminutely between 2 threads or more
        if(!started) {
            Thread t = new Thread(new CurrentRateThread(repo));
            t.start();
            started = true;
        }
    }
}