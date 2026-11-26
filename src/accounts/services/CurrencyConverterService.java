package accounts.services;

import com.google.inject.Inject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterService {

    //toto napsala AI

    private static final String CNB_URL = "https://www.cnb.cz/cs/financni-trhy/devizovy-trh/kurzy-devizoveho-trhu/kurzy-devizoveho-trhu/denni_kurz.txt";

    // Mapa uchovává hodnotu 1 jednotky dané měny v CZK (např. EUR -> 25.15)
    private final Map<String, Double> ratesToCzk = new HashMap<>();


    public CurrencyConverterService() {
        loadFallbackRates();
        fetchCnbRates();
    }

    // Záložní fixní kurzy pro případ výpadku internetu nebo API ČNB.
    private void loadFallbackRates() {
        ratesToCzk.put("CZK", 1.0);
        ratesToCzk.put("EUR", 25.20);
        ratesToCzk.put("USD", 23.40);
        ratesToCzk.put("GBP", 29.80);
        ratesToCzk.put("PLN", 5.85);
    }

    // Stáhne aktuální denní kurzovní lístek z ČNB a aktualizuje mapu kurzů.
    private void fetchCnbRates() {
        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(3))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CNB_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                parseCnbText(response.body());
            }
        } catch (Exception e) {
            System.err.println("[CurrencyConverterService] Nepodařilo se stáhnout kurzy z ČNB, používám záložní kurzy. (" + e.getMessage() + ")");
        }
    }

    // Rozparsuje textový formát ČNB (země|měna|množství|kód|kurz).
    private void parseCnbText(String body) {
        String[] lines = body.split("\\r?\\n");
        // První 2 řádky jsou hlavička (datum a názvy sloupců), proto začínáme na indexu 2
        for (int i = 2; i < lines.length; i++) {
            String[] parts = lines[i].split("\\|");
            if (parts.length == 5) {
                try {
                    int amount = Integer.parseInt(parts[2].trim());
                    String code = parts[3].trim().toUpperCase();
                    double rate = Double.parseDouble(parts[4].trim().replace(",", "."));

                    // Uložíme kurz přepočítaný na 1 jednotku měny (např. u JPY je množství 100)
                    ratesToCzk.put(code, rate / amount);
                } catch (NumberFormatException ignored) {
                    // Přeskočíme případný poškozený řádek
                }
            }
        }
    }

    //Převede zadanou částku z jedné měny do druhé přes základní měnu CZK.
    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Kód měny nesmí být null.");
        }

        String from = fromCurrency.trim().toUpperCase();
        String to = toCurrency.trim().toUpperCase();

        if (from.equals(to)) {
            return amount;
        }

        Double fromRate = ratesToCzk.get(from);
        Double toRate = ratesToCzk.get(to);

        if (fromRate == null) {
            throw new IllegalArgumentException("Nepodporovaná zdrojová měna: " + from);
        }
        if (toRate == null) {
            throw new IllegalArgumentException("Nepodporovaná cílová měna: " + to);
        }

        // 1. Převod zdrojové měny na CZK
        double amountInCzk = amount * fromRate;
        // 2. Převod z CZK na cílovou měnu
        return amountInCzk / toRate;
    }
}
