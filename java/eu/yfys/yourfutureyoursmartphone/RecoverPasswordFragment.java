package eu.yfys.yourfutureyoursmartphone;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Locale;

import cz.msebera.android.httpclient.Header;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by yfys on 02/12/2018.
 */

public class RecoverPasswordFragment extends Fragment {

    private AsyncHttpClient clientEmail;
    private String SERVER_URL_NEW_PASSWORD = "";
    private Gson gsonPassword;
    private ResponseNewEmail emailResponse;
    private boolean login;
    int validadoUsuario, validadoInstitucion;
    String email, password;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    View view;
    EditText etEmail, etPassword;
    Button boton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recover_password, container, false);



        etEmail = (EditText) view.findViewById(R.id.etLoginUsuariorecupera);


        boton = (Button) view.findViewById(R.id.buttonforgotpasswordrecupera);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = etEmail.getText().toString();
                InputMethodManager imm =
                        (InputMethodManager) getActivity().getBaseContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


                if(email !=null || email.length()>0 ||  email.length()>0){

                    if(isEmailValid(email)){


                        buscarEmail(email);

                    }else{
                        Toast.makeText(getActivity().getBaseContext(), getString(R.string.validacionemail), Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getActivity().getBaseContext(), getString(R.string.validacioncampos), Toast.LENGTH_SHORT).show();
                }


            }
        });

        return view;
    }

    public void buscarEmail(String email){

        clientEmail = new AsyncHttpClient();
        RequestParams rpPersona = new RequestParams();
        rpPersona.put("email", email);
        String codLenguaje;
        codLenguaje = Locale.getDefault().getLanguage();
        rpPersona.put("LANG",codLenguaje);

        clientEmail.get(getActivity().getBaseContext(), SERVER_URL_NEW_PASSWORD, rpPersona, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String newresponse = new String(responseBody);

                gsonPassword = new Gson();

                emailResponse = gsonPassword.fromJson(newresponse,ResponseNewEmail.class);

                if(emailResponse.getSuccess() == 1){



                    AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext());

                    dialogo.setTitle(getString(R.string.tituloPasswordok));
                    dialogo.setMessage(getString(R.string.textodialogoPasswordok));
                    dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
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


                     }else{

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext());

                    dialogo.setTitle(getString(R.string.tituloPasswordfail));
                    dialogo.setMessage(getString(R.string.textodialogoPasswordfail));
                    dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
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


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {


                AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext());

                dialogo.setTitle(getString(R.string.tituloPasswordfail));
                dialogo.setMessage(getString(R.string.textodialogoPasswordfail));
                dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });
                dialogo.show();


                }


        });


    }

    public static boolean isEmailValid(String email) {
        return !(email == null || TextUtils.isEmpty(email)) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
