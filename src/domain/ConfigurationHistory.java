package domain;

import java.util.Stack;

public class ConfigurationHistory {
    private Stack<EcuConfiguration> history = new Stack<>();

    public void save(EcuConfiguration configuration){
        history.push(configuration);
    }

    public EcuConfiguration undo(){
        if(!history.isEmpty()){
            return history.pop();
        }
        return null;
    }

}
