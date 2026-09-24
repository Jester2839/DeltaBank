package notifier;

public class EmailNotifierService implements NotifierService{

    @Override
    public void notify(String message){
        IO.println("EMAIL: " + message);
    }

}
