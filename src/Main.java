import accounts.*;
import accounts.serialization.*;
import com.google.inject.Guice;
import com.google.inject.Injector;
import di.BankModule;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public void main() {

    Injector injector = Guice.createInjector(new BankModule());
    App app = injector.getInstance(App.class);
    app.run();

}