package eu.yfys.yourfutureyoursmartphone;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by yfys on 02/12/2018.
 */

public class ActivityBusquedaResultado extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private String SERVER_URL="";
    private ListView lista;
    private BusquedaResponse responseObj;
    private AdaptadorLista ada;

    private Gson gson;
    private AsyncHttpClient client;
    String pais, tipoProyecto, area, tipoInstitucion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        lista = (ListView) findViewById(R.id.listaListar);



        Intent intent = getIntent();

        pais = intent.getStringExtra("pais");
        tipoInstitucion = intent.getStringExtra("tipoInstitucion");
        area = intent.getStringExtra("area");
        tipoProyecto = intent.getStringExtra("tipoProyecto");


        client = new AsyncHttpClient();

        RequestParams requestParams = new RequestParams();
        requestParams.put("COUNTRY", pais);
        requestParams.put("KTYPE", tipoProyecto);
        requestParams.put("TYPE", tipoInstitucion);
        requestParams.put("AREA", area);




        client.get(getApplicationContext(), SERVER_URL, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String newresponse = new String(responseBody);

                gson = new Gson();

                responseObj = gson.fromJson(newresponse,BusquedaResponse.class);

                //preguntar si hay datos con los filtros

                int sucess = responseObj.getSuccess();

                if (sucess==1) {

                    ada = new AdaptadorLista(getApplicationContext(), responseObj.getYf_RESULTADO());
                    lista.setAdapter(ada);
                }
                else
                {
                    //NO HAY DATOS


                    AlertDialog.Builder dialogo = new AlertDialog.Builder(ActivityBusquedaResultado.this);

                    dialogo.setTitle(getString(R.string.titulodialogosindatos));
                    dialogo.setMessage(getString(R.string.textodialogosinddatos));
                    dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialogo.show();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                String idusuario;
                String nombreCentro;

                String tipoProyecto;
                String area;
                String tipoCentro;
                String pais;
                String nombrePersona;

                BusquedaResponse.YfRESULTADOBean item = (BusquedaResponse.YfRESULTADOBean) lista.getItemAtPosition(posicion);

                idusuario = item.getIDPERSON().toString();
                nombreCentro = item.getNAMECENTER();
                tipoProyecto = item.getKTYPE();
                area = item.getAREA().toString();
                tipoCentro = item.getTYPE();
                pais= item.getCOUNTRY();
                nombrePersona = item.getNOMBRE();





                Intent i = new Intent(getApplication(), ActivityDetalleResultado.class);
                i.putExtra("idusuario", idusuario);
                i.putExtra("nombreCentro", nombreCentro);
                i.putExtra("tipoProyecto", tipoProyecto);
                i.putExtra("area", area);
                i.putExtra("tipoCentro", tipoCentro);
                i.putExtra("pais", pais);
                i.putExtra("nombre",nombrePersona);


                startActivity(i);


            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
