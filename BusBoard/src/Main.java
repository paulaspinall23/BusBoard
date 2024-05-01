import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main {
    public static void main(String[] args) {
        try {
            String stopPointId = "490008660N";
            URL url = new URL("https://api.tfl.gov.uk/StopPoint/" + stopPointId + "/Arrivals");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("Response: " + response.toString());
            } else {
                System.out.println("Error: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) throws URISyntaxException {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder().uri
//                (new URI("https://api.tfl.gov.uk/StopPoint/490000129R/Arrivals/")).build();
//
//
//
//
//        System.out.println(client.getClass());
//    }
}