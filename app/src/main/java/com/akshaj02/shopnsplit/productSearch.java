package com.akshaj02.shopnsplit;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
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

    //    Button mEPButton;
//    TextView mTextView;
    ProgressBar mProgressbar;
    Context context;

    //private static final String TAG = "searchApp";
    static String result = null;
    Integer responseCode = null;
    String responseMessage = "";
    String product = "";
    float WTotal = 0;
    float TTotal = 0;
    String walmartPriceString = "";
    String targetPriceString = "";
    String walmartTitles = "";
    String targetTitles = "";
    String walmartPrices = "";
    String targetPrices = "";
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
        context = getApplicationContext();

        // GUI init
//        mTextView = findViewById(R.id.textView1);
//        mEPButton = findViewById(R.id.button2);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                product= null;
            } else {
                product= extras.getString("list");
            }
        } else {
            product= (String) savedInstanceState.getSerializable("list");
        }

        //Store each item into product1, product2, product3, etc. by tokenizing the product string separated by commas
        String[] products = product.split(",");

        //Make product as product1
        //TODO: UnComment this part for the final version. This stops the google search from being called
        //for loop to iterate through the products
        for (int i = 0; i < products.length; i++) {
            product = products[i];
            runSearch(product);
        }


        //Intent intent = new Intent(productSearch.this, ExplorePrices.class);
//                intent.putExtra("wTitles", /*walmartTitles*/ "Pro1#pro2#pro3#pro4#pro5");
//                intent.putExtra("tTitles", /*targetTitles*/ "Pro1#pro2#pro3#pro4#pro5");
//                intent.putExtra("wPrices", /*walmartPrices*/ "1.99#2.99#3.99#4.99#5.99");
//                intent.putExtra("tPrices", /*targetPrices*/ "2.99#3.99#4.99#5.99#6.99");
//                intent.putExtra("WalmartTotal", /*walmartPriceString*/ "10.99");
//                intent.putExtra("TargetTotal", /*targetPriceString*/ "11.99");
//                startActivity(intent);


        prog();
    }

    public void prog(){
        mProgressbar = findViewById(R.id.progressBar);
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                counter++;
                mProgressbar.setProgress(counter);
                if(counter == 100){
                    timer.cancel();
                    Intent intent = new Intent(productSearch.this, ExplorePrices.class);
                    //TODO: Fix prices not showing up
                    intent.putExtra("wTitles",walmartTitles);
                    intent.putExtra("tTitles", targetTitles);
                    intent.putExtra("wPrices", walmartPrices);
                    intent.putExtra("tPrices", targetPrices);
                    intent.putExtra("WalmartTotal", /*walmartPriceString*/"10.99");
                    intent.putExtra("TargetTotal", /*targetPriceString*/ "12.38");
                    startActivity(intent);
                    finish();
                }
            }
        };

        timer.schedule(timerTask, 0, 150);
    }

    //Make a function to make the URL
    public void runSearch (String product) {
        String urlString = makeURL(product);

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(TAG, "ERROR converting String to URL " + e.toString());
        }
        Log.d(TAG, "Url = " + urlString);


        // start AsyncTask
        GoogleSearchAsyncTask searchTask = new GoogleSearchAsyncTask();
        searchTask.execute(url);
    }

    public static String makeURL(String product) {
        String noSpace = product.replace(" ", "+");

        String key = "AIzaSyDqDikiIyDl4LiWUk7sr2_x3F9dI7URkpg";
        String cx = "833e27497336947cd";
        String url = "https://www.googleapis.com/customsearch/v1?q=" + noSpace + "&key=" + key + "&cx=" + cx + "&alt=json";

        return url;
    }

//    public void totals(){
//        counter++;
//        if (counter == 5) {
//            mTextView.append("Walmart Total: " + WTotal + "\n");
//            mTextView.append("Target Total: " + TTotal + "\n");
//            //convert it to a string
//            walmartPriceString = String.valueOf(WTotal);
//            targetPriceString = String.valueOf(TTotal);
//        }
//    }

    private class GoogleSearchAsyncTask extends AsyncTask<URL, Integer, String> {

        protected void onPreExecute() {

            Log.d(TAG, "AsyncTask - onPreExecute");


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

                if (responseCode != null && responseCode == 200) {

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

                    WTotal = WTotal + walmartPriceFloat;
                    TTotal = TTotal + Float.parseFloat(targetPrice);


                    //Save the walmartTitle in an array separated by commas
                    walmartTitles = walmartTitles + walmartTitle + "#";
                    targetTitles = targetTitles + targetTitle + "#";
                    //Save the walmartPrice in an array separated by commas
                    walmartPrices = walmartPrices + walmartPriceString + "#";
                    targetPrices = targetPrices + targetPriceString + "#";

                    // return the links to be displayed
                    return "Walmart" + "\n" + walmartTitle + "\n" + walmartPriceFloat + "\n\n" + "Target" + "\n" + targetTitle + "\n" + targetPrice;

                } else {

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

//        protected void onProgressUpdate(Integer... progress) {
//
//            Log.d(TAG, "AsyncTask - onProgressUpdate, progress=" + progress);
//
//        }

        protected void onPostExecute(String result) {

            // Log.d(TAG, "AsyncTask - onPostExecute, result=" + result);


            //add a confirm button. run the async task when the button is clicked
            //add the answers from doInBackground to an arraylist.
            //PreExecute: display a loading message
            //PostExecute: move to another activity and display the results there



            // hide mProgressBar
            //mProgressBar.setVisibility(View.GONE);

            // make TextView scrollable
            //mTextView.setMovementMethod(new ScrollingMovementMethod());
            //display the links
//            mTextView.setText(result);

            //display the links one after the other
//            String[] links = result.split("\n\n");
//            for (String link : links) {
//                mTextView.append(link + "\n\n");
//            }
//            totals();
        }
    }

}