package frd.utn.dds_tp3;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
@Deprecated
public class CuentasActivity extends AsyncTask<String, String, String>{
    private String url;
    private RestServ restServ = new RestServ();


    CuentasActivity(String url){
        this.url = url;
    }
    @Override
    protected String doInBackground(String... strings) {
        return restServ.makeGetRequest(url);
    }
    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            //Creates rows dinamycally bad english je
            Iterator<String> iter = jsonObject.keys();
            while (iter.hasNext()){
                String key = iter.next();
                try{
                    Object value = jsonObject.get(key);

                    }catch (JSONException e){
                    System.out.println(e.getMessage());
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
