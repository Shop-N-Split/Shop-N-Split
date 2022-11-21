package com.akshaj02.shopnsplit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


import android.app.Activity;
import android.content.Context;

import android.os.AsyncTask;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class productSearch extends Activity {

    EditText mEditText;
    Button mButton;
    TextView mTextView;
    ProgressBar mProgressBar;
    Context context;

    private static final String TAG = "searchApp";
    static String result = null;
    Integer responseCode = null;
    String responseMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
        context = getApplicationContext();

        // GUI init
        mEditText = findViewById(R.id.edittext);
        mButton = findViewById(R.id.button);
        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.pb_loading_indicator);

        // button onClick
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final String searchString = mEditText.getText().toString();

                // hide keyboard
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                // remove spaces
                String searchStringNoSpaces = searchString.replace(" ", "+");

                // Your Google API key
                // TODO replace with your value
                String key="AIzaSyDqDikiIyDl4LiWUk7sr2_x3F9dI7URkpg";

                // Your Google Search Engine ID
                // TODO replace with your value
                String cx = "833e27497336947cd";

                String urlString = "https://www.googleapis.com/customsearch/v1?q=" + searchStringNoSpaces + "&key=" + key + "&cx=" + cx + "&alt=json";
                URL url = null;
                try {
                    url = new URL(urlString);
                } catch (MalformedURLException e) {
                    Log.e(TAG, "ERROR converting String to URL " + e.toString());
                }
                Log.d(TAG, "Url = "+  urlString);


                // start AsyncTask
                GoogleSearchAsyncTask searchTask = new GoogleSearchAsyncTask();
                searchTask.execute(url);

            }
        });

    }


    private class GoogleSearchAsyncTask extends AsyncTask<URL, Integer, String> {

        protected void onPreExecute(){

            Log.d(TAG, "AsyncTask - onPreExecute");
            // show mProgressBar
            mProgressBar.setVisibility(View.VISIBLE);

        }


        @Override
        protected String doInBackground(URL... urls) {

            URL url = urls[0];
            Log.d(TAG, "AsyncTask - doInBackground, url=" + url);

            // Http connection
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                Log.e(TAG, "Http connection ERROR " + e.toString());
            }


            try {
//                assert conn != null;
                responseCode = conn.getResponseCode();
                responseMessage = conn.getResponseMessage();
            } catch (IOException e) {
                Log.e(TAG, "Http getting response code ERROR " + e.toString());

            }

            Log.d(TAG, "Http response code =" + responseCode + " message=" + responseMessage);

            try {

                if(responseCode != null && responseCode == 200) {

                    // response OK
                    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();

                    //make json from response
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
                    //now we have the titles and links for each item
                    //check if link has "walmart" in it
                    //if it does, then we have a walmart link


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

                    //same for kroger
                    String krogerLink = "";
                    for (String link : links) {
                        if (link.contains("kroger")) {
                            krogerLink = link;
                            break;
                        }
                    }

                    conn.disconnect();

                    //now we have the links for each store
                    //we need to parse the html for each link to get the price and title
                    //then we need to display the results

                    //walmart
                    Document doc = Jsoup.connect(walmartLink).get();
                    //do the work only if connection is successful
                    String walmartTitle = "";
                    String walmartPrice = "0";
                    float walmartPriceFloat = 0;
                    if (doc != null)
                    {
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
                    if (doc != null)
                    {
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

//                    //kroger
//                    Document doc3 = Jsoup.connect(krogerLink).get();
//                    String krogerTitle = doc3.select("h1.ProductDetails-header.font-bold").text();
//                    String krogerPrice = doc3.select("mark.kds-Price-promotional.kds-Price-promotional--plain.kds-Price-promotional--decorated").text();
//                    //select 1st
//                    krogerPrice = krogerPrice.substring(0, krogerPrice.indexOf(" "));
//                    krogerPrice = krogerPrice.replaceAll("[^\\d.]", "");






































                    // return the links to be displayed
                    return  "Walmart" + "\n" + walmartTitle + "\n" + walmartPriceFloat + "\n\n" + "Target" + "\n" + targetTitle + "\n" + targetPrice
                            /*"Kroger" + "\n\n" + krogerTitle + "\n" + krogerPrice + "\n\n\n" + krogerLink*/;
                    //

                }

                else{

                    // response problem

                    String errorMsg = "Http ERROR response " + responseMessage;
                    Log.e(TAG, errorMsg);
                    result = errorMsg;
                    return result;

                }
            } catch (IOException e) {
                Log.e(TAG, "Http Response ERROR " + e.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onProgressUpdate(Integer... progress) {

            Log.d(TAG, "AsyncTask - onProgressUpdate, progress=" + progress);

        }

        protected void onPostExecute(String result) {

            Log.d(TAG, "AsyncTask - onPostExecute, result=" + result);

            // hide mProgressBar
            mProgressBar.setVisibility(View.GONE);

            // make TextView scrollable
            mTextView.setMovementMethod(new ScrollingMovementMethod());
            //display the links
            mTextView.setText(result);
        }


    }

}