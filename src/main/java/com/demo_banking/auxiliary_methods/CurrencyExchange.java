package com.demo_banking.auxiliary_methods;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyExchange {

    private static BigDecimal getExchangeRate(String currencyCode) {
        try {
            URL url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (currencyCode.equals(jsonObject.getString("cc"))) {
                    return new BigDecimal(jsonObject.getDouble("rate"));
                }
            }

            throw new RuntimeException("Unable to retrieve exchange rate for " + currencyCode + " from NBU API");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BigDecimal getUsdToUahExchangeRate() {
        return getExchangeRate("USD");
    }

    public static BigDecimal getUahToUsdExchangeRate() {
        return BigDecimal.ONE.divide(getUsdToUahExchangeRate(), 6, BigDecimal.ROUND_HALF_UP);
    }
}
