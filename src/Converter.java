import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Converter {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";
    private Map<String, Double> rates;
    StringBuilder response = new StringBuilder();

    public Converter(String baseCurrency) throws Exception {
        rates = getResponse(baseCurrency);
    }

    private Map<String, Double> getResponse(String baseCurrency) throws Exception {
        String urlString = API_URL + baseCurrency;
        try{
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }

        }
        catch (Exception e){

            System.out.println(e);
        }

        Map<String, Double> ratesMap = new HashMap<>();
        String jsonResponse = response.toString();
        String[] parts = jsonResponse.split("\"rates\":\\{")[1].split("\\}")[0].split(",");

        for (String part : parts) {
            String[] keyValue = part.split(":");
            ratesMap.put(keyValue[0].replace("\"", "").trim(), Double.parseDouble(keyValue[1]));
        }

        return ratesMap;
    }

    public void convert(double amount, String fromCurrency) {
        if (!rates.containsKey(fromCurrency)) {
            System.out.println("Неверная валюта: " + fromCurrency);
            return;
        }

        double baseAmount = amount / rates.get(fromCurrency);
        System.out.println("Конвертация " + amount + " " + fromCurrency + ":");
        int count = 0;
        for (String currency : rates.keySet()) {
            if (!currency.equals(fromCurrency)  && count < 5) {
                double convertedAmount = baseAmount * rates.get(currency);
                System.out.printf("%.2f %s = %.2f %s%n", amount, fromCurrency, convertedAmount, currency);
                count++;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        try {
            Converter converter = new Converter("USD");
            System.out.println("Введите сумму и валюту (например, 100 USD):");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            double amount = Float.parseFloat(parts[0]);
            String currency = parts[1];

            converter.convert(amount, currency);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
