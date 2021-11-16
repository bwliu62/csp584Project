
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApiUtilities {
    private static String urlPre = "https://maps.googleapis.com/maps/api/geocode/json?&address=";
    private static String APIKEY = "&key=AIzaSyAHnfZFSq_y9Rf14NyCxvjgBLSQS3sN-0Q";

    public static ArrayList<News> getHealthNews() {

        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(
                    "https://newsapi.org/v2/top-headlines?sources=medical-news-today&language=en&apiKey=8a20eb2141dd44ca9ddb26f244c82287");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader((new InputStreamReader(is)));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return parseJSON(sb.toString());
    }

    private static ArrayList<News> parseJSON(String s) {
        ArrayList<News> newsList = new ArrayList<>();
        try {
            JSONObject jObjMain = new JSONObject(s);
            JSONArray articles = jObjMain.getJSONArray("articles");
            for (int i = 0; i < articles.length(); i++) {
                JSONObject newsObj = (JSONObject) articles.get(i);
                News news = new News();
                news.setContent(newsObj.getString("content"));
                news.setAuthor(newsObj.getString("author"));
                news.setDescription(newsObj.getString("description"));

                String formatDate;
                try {
                    Date dat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(newsObj.getString("publishedAt"));
                    String pattern = "MMM dd, yyyy HH:mm";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    formatDate = simpleDateFormat.format(dat);
                    news.setPublishedAt(formatDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                news.setTitle(newsObj.getString("title"));
                news.setUrlToImage(newsObj.getString("urlToImage"));
                news.setUrl(newsObj.getString("url"));
                newsList.add(news);
            }
            System.out.println("newsList.size(): " + newsList.size());
            return newsList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> getLatLongPositions(String address)  {
        String add = address.replaceAll("\\s", "/");
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlPre + add + APIKEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader((new InputStreamReader(is)));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return parseLocationJSON(sb.toString());

    }

    private static ArrayList<String> parseLocationJSON(String s) {
        ArrayList<String> result = new ArrayList();
        try {
            JSONObject jObjMain = new JSONObject(s);
            JSONArray results = jObjMain.getJSONArray("results");
            JSONObject newsObj = (JSONObject) results.get(0);
            int i = 0;

            JSONObject geometry = newsObj.getJSONObject("geometry");
            JSONObject res = (JSONObject) geometry.getJSONObject("location");

            if (i == 0) {
                result.add(res.getDouble("lat")+"");
                result.add(res.getDouble("lng")+"");
            }



            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}