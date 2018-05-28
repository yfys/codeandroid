package eu.yfys.yourfutureyoursmartphone;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class FragmentDetalleResultado extends Fragment {
    private String nombreInstitucion, emailContacto, area, tipoCentro, tipoProyecto, pais, mensaje, idpersona, emailremitente, nombrePersona;

    private String SERVER_URL_MAIL = "";
    private AsyncHttpClient clientMail;
    private EmailResponse emailResponse;
    private Gson gsonEmail;
    Fragment newFragment;
    FragmentTransaction transaction;


    TextView tvPais, tvNombreCentro, tvArea, tvTipoProyecto, tvTipoInstitucion, tvNombre;
    EditText etMensaje;
    CheckBox compartiremail;

    Button boton;
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_resultado);

        Intent intent = getIntent();
        idpersona = intent.getStringExtra("idusuario");
        nombreInstitucion = intent.getStringExtra("nombreCentro");
        tipoProyecto=intent.getStringExtra("tipoProyecto");
        area = intent.getStringExtra("area");
        tipoCentro=intent.getStringExtra("tipoCentro");
        pais=intent.getStringExtra("pais");
        nombrePersona =intent.getStringExtra("nombre");

        tvPais = (TextView) findViewById(R.id.tvDetallesPais);
        tvPais.setText(pais);

        tvNombreCentro = (TextView) findViewById(R.id.tvDetallesNombreCentro);
        tvNombreCentro.setText(nombreInstitucion);

        tvArea = (TextView) findViewById(R.id.tvDetallesArea);
        tvArea.setText(area);

        tvTipoInstitucion = (TextView) findViewById(R.id.tvDetallesTipoCentro);
        tvTipoInstitucion.setText(tipoCentro);

        tvTipoProyecto = (TextView) findViewById(R.id.tvDetallesTipoProyecto);
        tvTipoProyecto.setText(tipoProyecto);

        tvNombre = (TextView) findViewById(R.id.tvDetallesNombrePersona);
        tvNombre.setText(nombrePersona);

        etMensaje = (EditText) findViewById(R.id.etMensajeContacto);

        boton = (Button) findViewById(R.id.botonEnviarMailContacto);
        compartiremail = (CheckBox) findViewById(R.id.compartiremail);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(compartiremail.isChecked())
                {

                    AdaptadorBD  db = new AdaptadorBD (ActivityDetalleResultado.this);
                    db.open();
                    Cursor cursor=db.getTodosRegistros();

                    cursor.moveToFirst();


                    emailremitente = cursor.getString(0);


                    cursor.close();
                    db.close();
                }
                else
                    emailremitente ="";

                sendMailContacto();

            }

        });



    }




    private void sendMailContacto(){


        mensaje = etMensaje.getText().toString();
        mensaje = mensaje.replaceAll("\n", "<br/>");


        Log.d("EMAIL", ""+idpersona);
        Log.d("MENSAJE", mensaje);
        Log.d("REMITENTE", emailremitente);

        clientMail = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("EMAIL",""+idpersona);
        rp.put("MENSAJE", mensaje);
        rp.put("REMITENTE", emailremitente);


        clientMail.post(SERVER_URL_MAIL,rp, new JsonHttpResponseHandler()
        {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                String respuesta = new String(response.toString());

                gsonEmail = new Gson();

                emailResponse = gsonEmail.fromJson(respuesta, EmailResponse.class);

                String mensaje = emailResponse.getMessage();
                int res = emailResponse.getSuccess();

                if (res==1){



                    AlertDialog.Builder dialogo = new AlertDialog.Builder(ActivityDetalleResultado.this);

                    dialogo.setTitle(getString(R.string.tituloeemaildok));
                    dialogo.setMessage(getString(R.string.textodialogoemailok));
                    dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent = new Intent(ActivityDetalleResultado.this, MainActivity.class);

                            startActivity(intent);





                        }
                    });
                    dialogo.show();




                }else{


                    Toast.makeText(ActivityDetalleResultado.this,getString(R.string.errorinserccion), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                Toast.makeText(ActivityDetalleResultado.this,getString(R.string.errorinserccion), Toast.LENGTH_SHORT).show();

            }
        });



    }
    */
}
