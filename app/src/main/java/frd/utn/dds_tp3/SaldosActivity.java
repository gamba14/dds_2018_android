package frd.utn.dds_tp3;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONObject;
@Deprecated
public class SaldosActivity extends AsyncTask<String, String, String> {
    private String url;
    RestServ restServ = new RestServ();

    public SaldosActivity(String url) {
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
            String saldo = jsonObject.getString("saldo");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
