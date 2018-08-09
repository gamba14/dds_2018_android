package frd.utn.dds_tp3;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    RestServ restServ = new RestServ();
    //private final String restCuentas = "http://10.0.2.2:8080/borbotones/rest/cliente/1";
    private final String restCuentas = "http://192.168.1.64:8080/dds_2018/rest/cliente/1/cuentas";
    //String restSaldos = "http://10.0.2.2:8080/borbotones/rest/cliente/1/cuentas/1/saldo";
    private final String restSaldos = "http://192.168.1.64:8080/dds_2018/rest/cliente/1/cuentas/1/saldo";
    //private final String restClientes = "http://10.0.2.2:8080/borbotones/rest/cliente";
    private final String restClientes = "http://192.168.1.64:8080/dds_2018/rest/cliente";
    private final String restMovimientos = "http://192.168.1.64:8080/dds_2018/rest/cliente/1/cuentas/1/movimientos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btnEjecutar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CuentasActivity().execute();
            }
        });

        findViewById(R.id.btnSaldo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SaldosActivity().execute();
            }
        });

        findViewById(R.id.btnMovimientos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MovimientosActivity().execute();
            }
        });
    }

    private class CuentasActivity extends  AsyncTask <String, String, String>{
        @Override
        protected String doInBackground(String... strings){
            return restServ.makeGetRequest(restCuentas);
        }
        @Override
        protected  void onPostExecute(String result){
            try {
                TableLayout t1 = (TableLayout) findViewById(R.id.tableDestiny);
                TextView tv1 = (TextView) findViewById(R.id.txtDestino);
                tv1.setText("Cuentas");
                TextView [] textArray = new TextView[result.length()];
                TableRow [] tr_head = new TableRow[result.length()];
                JSONArray response = new JSONArray(result);
                System.out.println(response.length());

                for(int i=0;i<response.length();i++){

                    JSONObject o = response.getJSONObject(i);
                    //JSONObject apertura = o.getJSONObject("apertura");
                    String aperturaString = o.getString("numero");
                    tr_head[i] = new TableRow(getApplicationContext());
                    tr_head[i].setId(i+1);
                    tr_head[i].setBackgroundColor(Color.GRAY);
                    tr_head[i].setLayoutParams(new TableLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    textArray[i] = new TextView(getApplicationContext());
                    textArray[i].setId(i+111);
                    textArray[i].setText("Cuenta Numero: "+aperturaString);
                    textArray[i].setTextColor(Color.WHITE);
                    textArray[i].setPadding(5,5,5,5);
                    tr_head[i].addView(textArray[i]);
                    t1.addView(tr_head[i], new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

    private class SaldosActivity extends  AsyncTask <String, String, String>{
        @Override
        protected  String doInBackground(String... strings){
            return restServ.makeGetRequest(restCuentas);
        }
        @Override
        protected void onPostExecute(String resutl){

        }
    }

    private class MovimientosActivity extends AsyncTask <String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            return restServ.makeGetRequest(restMovimientos);
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }

}
