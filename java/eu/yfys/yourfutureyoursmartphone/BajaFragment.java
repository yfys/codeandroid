package eu.yfys.yourfutureyoursmartphone;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Locale;

import cz.msebera.android.httpclient.Header;


public class BajaFragment extends Fragment{

View view;
Button botonbaja;
    private String SERVER_URL_BAJA = "";
    private Gson gson;
    private AsyncHttpClient clienteBaja;
    BajaResponse responseObj;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view =   inflater.inflate(R.layout.layout_baja, container, false);
       botonbaja = (Button) view.findViewById(R.id.botonbaja);

       botonbaja.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.M)
           @Override
           public void onClick(View view) {



               AlertDialog.Builder dialogo;
               dialogo = new AlertDialog.Builder(getActivity());

               dialogo.setTitle(getString(R.string.tituloBaja));
               dialogo.setMessage(getString(R.string.textodialogoBaja));
               dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                       // LOGOUT y BAJA POR EMAIL

                       AdaptadorBD  db = new AdaptadorBD (getActivity().getBaseContext());
                       db.open();
                       Cursor cursor=db.getTodosRegistros();

                       cursor.moveToFirst();


                       String emailremitente = cursor.getString(0);
                       int valor =cursor.getInt(2);



                       cursor.close();
                       db.close();

                       Bajaemail(emailremitente,valor);

                       AdaptadorBD  dbnew = new AdaptadorBD(getActivity().getBaseContext());
                       dbnew.open();
                       dbnew.borrarTodosRegistros();
                       dbnew.close();


                   }
               });

               dialogo.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {


                       AcercaFragment newFragment = new AcercaFragment();
                       FragmentTransaction transaction = getFragmentManager().beginTransaction();
                       transaction.replace(R.id.fragment_container, newFragment);
                       transaction.attach(newFragment);
                       transaction.commit();


                   }
               });
               dialogo.show();
           }
       });

        return view;

    }

    public void  Bajaemail(String email, int valor){

        clienteBaja = new AsyncHttpClient();

        String codLenguaje= Locale.getDefault().getLanguage();

        RequestParams requestParams = new RequestParams();
        requestParams.put("EMAIL", email);
        requestParams.put("ID", valor);
        requestParams.put("LANG",codLenguaje);





        clienteBaja.get(getActivity().getApplicationContext(), SERVER_URL_BAJA, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String newresponse = new String(responseBody);

                gson = new Gson();

                responseObj = gson.fromJson(newresponse,BajaResponse.class);

                //preguntar si hay datos con los filtros

                int sucess = responseObj.getSuccess();

                if (sucess==1) {


                    InformaBajaFragment newFragment = new InformaBajaFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.attach(newFragment);
                    transaction.commit();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });



    }

}
