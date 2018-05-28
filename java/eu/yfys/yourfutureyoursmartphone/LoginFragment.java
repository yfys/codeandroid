package eu.yfys.yourfutureyoursmartphone;

import android.app.Fragment;
import android.app.FragmentTransaction;
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

import cz.msebera.android.httpclient.Header;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * Created by yfys on 02/12/2018.
 */

public class LoginFragment extends Fragment {

    private AsyncHttpClient clientPersona;
    private String SERVER_URL_BUSCAR_PERSONA = "";
    private Gson gsonPersona;
    private PersonContactResponse personaResponse;
    private PersonContactResponse.YfCONTACTPERSONBean usuario;
    private boolean login;
    int validadoUsuario, validadoInstitucion;
    String email, password;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    View view;
    EditText etEmail, etPassword;
    private Button boton, botonpassword;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_login, container, false);

        pref = getActivity().getBaseContext().getSharedPreferences("cuenta", 0);



        etEmail = (EditText) view.findViewById(R.id.etLoginUsuario);
        etPassword = (EditText) view.findViewById(R.id.etLoginPass);

        boton = (Button) view.findViewById(R.id.buttonLogin);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login=false;

                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                InputMethodManager imm =
                        (InputMethodManager) getActivity().getBaseContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if(email !=null || email.length()>0 || password!=null || email.length()>0){

                    if(isEmailValid(email)){

                        buscarUsuario(email, password);

                    }else{
                        Toast.makeText(getActivity().getBaseContext(), "El email introducido no cumple con el formato correcto", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getActivity().getBaseContext(), "Por favor, introduzca todos los campos", Toast.LENGTH_SHORT).show();
                }



            }
        });

        botonpassword = (Button) view.findViewById(R.id.buttonforgotpassword);
        botonpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm =
                        (InputMethodManager) getActivity().getBaseContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                RecoverPasswordFragment newFragment = new RecoverPasswordFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.attach(newFragment);
                transaction.commit();


            }
        });

        return view;
    }

    public void buscarUsuario(final String email, String password){

        clientPersona = new AsyncHttpClient();
        RequestParams rpPersona = new RequestParams();
        rpPersona.put("email", email);
        rpPersona.put("password", password);
        clientPersona.get(getActivity().getBaseContext(), SERVER_URL_BUSCAR_PERSONA, rpPersona, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String newresponse = new String(responseBody);

                gsonPersona = new Gson();

                personaResponse = gsonPersona.fromJson(newresponse,PersonContactResponse.class);

                if(personaResponse.getSuccess() == 1){

                    usuario = personaResponse.getYf_CONTACTPERSON().get(0);
                    validadoUsuario=usuario.getCONFIRMEMAIL();
                    validadoInstitucion=usuario.getCONFIRMEMAILINST();
                    if(validadoInstitucion==1){

                        if(validadoUsuario==1){


                            AdaptadorBD  db = new AdaptadorBD (getActivity().getBaseContext());
                            db.open();
                            db.borrarTodosRegistros();
                            db.insertarRegistro(usuario.getNAME(),email,1);
                            db.close();

                            Toast.makeText(getActivity().getBaseContext(), ""+ getString(R.string.identificacion)+usuario.getNAME()+ "  "+ usuario.getLASTNAME()+ pref.getString("email", ""), Toast.LENGTH_SHORT).show();
                            login =true;

                            BuscadorFragment newFragment = new BuscadorFragment();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, newFragment);
                            transaction.attach(newFragment);
                            transaction.commit();


                        }else{


                            Toast.makeText(getActivity().getBaseContext(), getString(R.string.validacion), Toast.LENGTH_SHORT).show();

                        }

                    }else{

                        Toast.makeText(getActivity().getBaseContext(), getString(R.string.validacion), Toast.LENGTH_SHORT).show();

                    }


                }else{
                    Toast.makeText(getActivity().getBaseContext(),  getString(R.string.validaciondatos) , Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getActivity().getBaseContext(), getString(R.string.errorconexion), Toast.LENGTH_SHORT).show();

            }


        });


    }

    public static boolean isEmailValid(String email) {
        return !(email == null || TextUtils.isEmpty(email)) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
