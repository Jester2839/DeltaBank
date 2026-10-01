package accountsServices;

import java.util.Random;

public class AccountNumGeneratorService {

    //Metoda na generovaní validních českých bankovní čísel (vygenerovanáno gemini pro)

    // Váhy pro výpočet Modulo 11 (zleva doprava pro 10místné číslo účtu)
    private static final int[] WEIGHTS = {6, 3, 7, 9, 10, 5, 8, 4, 2, 1};

    // Nejčastější kódy českých bank (KB, ČSOB, Moneta, ČS, Fio, AirBank, Raiffeisenbank)
    private static final String[] BANK_CODES = {"0100", "0300", "0600", "0800", "2010", "3030", "5500"};

    /**
     * Vygeneruje náhodné, ale matematicky validní číslo českého bankovního účtu.
     * Formát: CCCCCCCCCC/KKKK
     */
    public static String accNumGen() {
        Random random = new Random();
        int[] number = new int[10];

        while (true) {
            int sum = 0;

            // Vygenerujeme prvních 9 číslic
            for (int i = 0; i < 9; i++) {
                number[i] = random.nextInt(10);

                // Aby číslo nezačínalo nulou (zajistí hezčí 10místný formát)
                if (i == 0 && number[i] == 0) {
                    number[i] = random.nextInt(9) + 1;
                }

                sum += number[i] * WEIGHTS[i];
            }

            // Výpočet poslední (kontrolní) číslice, která má váhu 1
            int remainder = sum % 11;
            int lastDigit = (11 - remainder) % 11;

            // Pokud Modulo 11 vyžaduje jako poslední číslici 10 (což nelze zapsat),
            // kombinaci zahodíme a smyčka vygeneruje novou.
            if (lastDigit < 10) {
                number[9] = lastDigit;
                break;
            }
        }

        // Sestavení čísla do řetězce
        StringBuilder accountBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            accountBuilder.append(number[i]);
        }

        // Přidání náhodného kódu banky
        String bankCode = BANK_CODES[random.nextInt(BANK_CODES.length)];

        return accountBuilder.toString() + "/" + bankCode;
    }
}
