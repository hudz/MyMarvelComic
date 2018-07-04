package my.edu.inti.asg202.mymarvelcomic;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchDataComic extends AsyncTask {

    String data = "";
    String dataParsed = "";
    String singleParsed ="";
    // Showing progress dialog



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //MainActivity.progressBarCyclic.s

    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            String urlText = "https://api.myjson.com/bins/j5f6b";
            String urlTextMarvel = "https://gateway.marvel.com/v1/public/comics?apikey=8501f10e5952ab5cffdb31001c2c291c&ts=2&hash=4d03fb0db88703eae9ed7546622e4bc5";

            URL url = new URL(urlTextMarvel);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";


            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            //data = data + "]";

            /*
            JSONObject jObj = new JSONObject(result);
            JSONObject response = jObj.getJSONObject("response");
            //JSONObject jb = new JSONObject(response);
            JSONArray jr = response.getJSONArray("Message");
            for(int i=0;i<jr.length();i++)
            {
                JSONObject jb1 = jr.getJSONObject(i);
                String question = jb1.getString("question");
                Log.i(".......",question);
            }*/

            //JSONArray JA = new JSONArray(data);
            JSONObject JO = new JSONObject(data);
            //for (int i = 0; i < jObj.length(); i++) {
                //JSONObject JO = (JSONObject) jObj.get(i);
                /*singleParsed = "name:" + JO.get("name") + "\n" +
                        "password:" + JO.get("password") + "\n" +
                        "contact:" + JO.get("contact") + "\n" +
                        "country:" + JO.get("country") + "\n";*/

                String infoComic = "code:" + JO.getString("code") + "\n" +
                        "status:" + JO.getString("status") + "\n" +
                        "copyright:" + JO.get("copyright") + "\n" +
                        "etag:" + JO.getString("etag") + "\n";

                dataParsed = infoComic;

                 JSONObject JdataMarvel = JO.getJSONObject("data");
                 JSONArray JA = JdataMarvel.getJSONArray("results");

                 for( int i = 0; i < JA.length(); i++){

                     JSONObject jArrResult = JA.getJSONObject(i);
                     singleParsed = "idComic:" + jArrResult.getString("id") + "\n" +
                             "title:" + jArrResult.getString("title") + "\n" +
                             "description:" + jArrResult.getString("description") + "\n" +
                             "issueNumber:" + jArrResult.getString("issueNumber") + "\n";


                     dataParsed = dataParsed + singleParsed + "\n";
                 }






            //}



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        System.out.println("======================================dataParsed==========="+this.dataParsed);

        /*AlphaAnimation fadeOutAnimation = new AlphaAnimation(1.0f, 0.0f);//fade from 1 to 0 alpha
        fadeOutAnimation.setDuration(218);
        fadeOutAnimation.setFillAfter(true);//to keep it at 0 when animation ends
        MainActivity.progressBarCyclic.startAnimation(fadeOutAnimation);*/
        //MainActivity.progressBarCyclic.setVisibility(View.INVISIBLE);

        MainActivity.comicDataTextView.setText(this.dataParsed);
    }
}
