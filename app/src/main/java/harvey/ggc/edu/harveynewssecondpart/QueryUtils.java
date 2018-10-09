package harvey.ggc.edu.harveynewssecondpart;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();
    private static final String SAMPLE_JSON_RESPONSE = "https://content.guardianapis.com/search?api-key=8790b10f-4581-4c81-9927-38d186e5e689&show-tags=contributor";

    private QueryUtils() {
    }

    public static List<News> fetchNewsData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request", e);
        }
        List<News> news = extractFeatureFromJson(jsonResponse);
        return news;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {

        }
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        // Log.i(LOG_TAG, "incoming url is " + "https://content.guardianapis.com/search?api-key=8790b10f-4581-4c81-9927-38d186e5e689");
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code:" + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON result!");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<News> extractFeatureFromJson(String jsonResponse) {


        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }
        List<News> news = new ArrayList<>();
        try {

            JSONObject baseJSONResponse = new JSONObject(jsonResponse);

            JSONObject baseJSONResponseResult = baseJSONResponse.getJSONObject("response");

            JSONArray currentNews = baseJSONResponseResult.getJSONArray("results");

            for (int i = 0; i < currentNews.length(); i++) {
                String author = "No Author!";
                JSONObject localNews = currentNews.getJSONObject(i);

                String name = localNews.getString("webTitle");
                String url = localNews.getString("webUrl");
                String date = localNews.getString("webPublicationDate");
                //String section = (String) localNews.get("sectionName");

                JSONArray authorResults = localNews.getJSONArray("tags");
                if (authorResults == null) {
                    author = " ";
                } else {
                    for (int s = 0; s < authorResults.length(); s++) {
                        JSONObject currentInfo = authorResults.getJSONObject(s);

                        author = currentInfo.getString("webTitle");

                        News news1 = new News(name, author, date, url);
                        news.add(news1);
                    }
                }
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }
        return news;
    }

}
