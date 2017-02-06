package steps;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public class ScenarioContext {

    private List<Trade> trades = new ArrayList();


    public void addTrade(Trade trade){
        trades.add(trade);
    }

    public ScenarioContext(){
        trades = new ArrayList();
    }

    @PostConstruct
    public void resetTrades(){
        trades.clear();
    }

    public Trade getFirstTrade(){
        return trades.get(0);
    }
}