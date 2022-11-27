package com.akshaj02.shopnsplit;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.akshaj02.shopnsplit.Adapter.ToDoAdapter;
import com.akshaj02.shopnsplit.Model.ToDoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class myProductSearcher extends AppCompatActivity {



    EditText mEditText;
    Button mButton;
    Button mEPButton;
    static TextView mTextView;
    ProgressBar mProgressBar;
    Context context;

    private static final String TAG = "searchApp";
    static String result = null;
    //arraylist to store the products
    //static ArrayList<String> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
        context = getApplicationContext();

        // GUI init
        mTextView = findViewById(R.id.textView1);
        mEPButton = findViewById(R.id.button2);

        String searchString = "apples";

        //call make URL
        String url = makeURL(searchString);
        URL urlString = null;
        try {
            urlString = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //mTextView.setText(url);

        //call the async task
        GoogleSearchAsyncTask task = new GoogleSearchAsyncTask();
        task.execute(urlString);


//        if (result != null) {
//            mTextView.setText(result);
//        }
//        else {
//            mTextView.setText("No results");
//        }
        //mTextView.setText(result);

    }
        public static String makeURL (String product){
            String noSpace = product.replace(" ", "+");

            String key = "AIzaSyDqDikiIyDl4LiWUk7sr2_x3F9dI7URkpg";
            String cx = "833e27497336947cd";
            String url = "https://www.googleapis.com/customsearch/v1?q=" + noSpace + "&key=" + key + "&cx=" + cx + "&alt=json";

            return url;
        }


        public static class GoogleSearchAsyncTask extends AsyncTask<URL, Void, String> {
            public ArrayList<String> products;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //change the text to loading
                mTextView.setText(makeURL("apples"));
            }

            @Override
            protected String doInBackground(URL... urls) {
                String url = makeURL("apples");

                //http connection
                HttpURLConnection conn = null;
                try {
                    URL urlObj = new URL(url);
                    conn = (HttpURLConnection) urlObj.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Integer responseCode = null;
                try {
                    responseCode = conn.getResponseCode();

                    if (responseCode == 200) {
                        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();

                        //make json from the response
                        String line = "";
                        while ((line = rd.readLine()) != null) {
                            sb.append(line);
                        }
                        rd.close();

                        //parse json to get titles and names for each item
                        String json = sb.toString();
                        JSONObject obj = new JSONObject(json);
                        JSONArray items = obj.getJSONArray("items");

                        String[] links = new String[items.length()];
                        for (int i = 0; i < items.length(); i++) {
                            JSONObject item = items.getJSONObject(i);

                            links[i] = item.getString("link");
                        }


                        String walmartLink = "";
                        for (String value : links) {
                            if (value.contains("walmart")) {
                                walmartLink = value;

                                break;
                            }
                        }

                        //same for target
                        String targetLink = "";
                        for (String s : links) {
                            if (s.contains("target")) {
                                targetLink = s;
                                break;
                            }
                        }

                        conn.disconnect();

                        //walmart
                        Document doc = Jsoup.connect(walmartLink).get();
                        //do the work only if connection is successful
                        String walmartTitle = "";
                        String walmartPrice = "0";
                        float walmartPriceFloat = 0;
                        if (doc != null) {
                            //select html with custom css selector
                            walmartTitle = doc.select("h1.f3.b.lh-copy.dark-gray.mt1.mb2").text();
                            walmartPrice = doc.select("span.inline-flex.flex-column").text();
                            //select only 1 of the 2 html elements with the same css selector
                            walmartPrice = walmartPrice.substring(0, walmartPrice.indexOf(" "));
                            //remove any non-numeric characters
                            walmartPrice = walmartPrice.replaceAll("[^\\d.]", "");
                            //convert to float
                            walmartPriceFloat = Float.parseFloat(walmartPrice);
                        }

                        //target
                        Document doc2 = Jsoup.connect(targetLink).get();
                        String targetTitle = "";
                        String targetPrice = "0";
                        if (doc != null) {
                            targetTitle = doc2.select("h1.Heading__StyledHeading-sc-1ihrzmk-0.jmSnUp.h-text-bold.h-margin-b-tight").text();
                            URL url2 = new URL(targetLink);
                            URLConnection connection = url2.openConnection();
                            connection.connect();

                            InputStream in = connection.getInputStream();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                            String line2;
                            StringBuilder html = new StringBuilder();
                            while ((line2 = reader.readLine()) != null) {
                                html.append(line2);
                            }
                            reader.close();
                            //Find the price
                            int start = html.indexOf("formatted_current_price");
                            int end = html.indexOf("formatted_current_price");
                            while (html.charAt(end) != ',') {
                                end++;
                            }
                            String priceTarget = html.substring(start, end);
                            targetPrice = priceTarget.replaceAll("[^\\d.]", "");
                        }


                        //return the results
                        if (walmartTitle == "" || targetTitle == "" || walmartPrice == "" || targetPrice == "") {
                            result = "No results found";
                        } else {
                            result = walmartTitle + " " + walmartPrice + " " + targetTitle + " " + targetPrice;
                        }
                    }
                    else {
                        result = "No results found";
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //use the return from the
                return result;
            }


            //add the results to the arraylist
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            products.add(s);
//        }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if (result != null) {
                    mTextView.setText(result);
                }
                else {
                    mTextView.setText("No results");
                }
                //mTextView.setText(result);
            }

        }


}
