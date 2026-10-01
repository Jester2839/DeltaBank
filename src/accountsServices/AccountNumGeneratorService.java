package accountsServices;

import java.util.Random;

public class AccountNumGeneratorService {

    public String accNumGen() {
        String accountNumber = "";
        String kodBanky = "/0520";
        

        Random random = new Random();
        int cislo = 10_000_000 + random.nextInt(99_999_999);

        accountNumber = cislo + kodBanky;

        return  accountNumber;
    }
}
