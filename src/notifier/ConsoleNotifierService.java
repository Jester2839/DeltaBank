package notifier;

public class ConsoleNotifierService implements NotifierService{

    @Override
    public void notify(String message){
        IO.println("CONSOLE: " + message);
    }

}
