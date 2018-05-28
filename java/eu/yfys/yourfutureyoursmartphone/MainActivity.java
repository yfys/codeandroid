package eu.yfys.yourfutureyoursmartphone;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



/**
 * Created by yfys on 02/12/2018.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment newFragment;
    FragmentTransaction transaction;


    Cursor cursor;
    int numElementos;
    AdaptadorBD db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Create new fragment and transaction
        newFragment = new AcercaFragment();
        transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.attach(newFragment);

        // Commit the transaction
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
/*
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();




        switch (id) {
            case R.id.acerca:
                newFragment = new AcercaFragment();
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.attach(newFragment);
                transaction.commit();

                break;
            case R.id.socios:
                newFragment = new SociosFragment();
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.attach(newFragment);
                transaction.commit();

                break;
            case R.id.enlaces:
                newFragment = new EnlacesFragment();
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.attach(newFragment);
                transaction.commit();

                break;
            case R.id.identificacion:

                db = new AdaptadorBD(this);
                db.open();
                cursor = db.getTodosRegistros();

                numElementos = cursor.getCount();

                if (numElementos > 0) {

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                    dialogo.setTitle(getString(R.string.titulodebenologin));
                    dialogo.setMessage(getString(R.string.textodialogodebenologin));
                    dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {





                        }
                    });
                    dialogo.show();

                } else {


                    newFragment = new LoginFragment();
                    transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.attach(newFragment);
                    transaction.commit();


                }
                cursor.close();
                db.close();








                break;
            case R.id.registro:

                db = new AdaptadorBD(this);
                db.open();
                cursor = db.getTodosRegistros();

                numElementos = cursor.getCount();

                if (numElementos > 0) {

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                    dialogo.setTitle(getString(R.string.titulodebenologin2));
                    dialogo.setMessage(getString(R.string.textodialogodebenologin2));
                    dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            newFragment = new AcercaFragment();
                            transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, newFragment);
                            transaction.attach(newFragment);
                            transaction.commit();




                        }
                    });
                    dialogo.show();

                } else {




                    newFragment = new RegistroFragment();
                    transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.attach(newFragment);
                    transaction.commit();


                }
                cursor.close();
                db.close();




                break;

            case R.id.logout:


                db = new AdaptadorBD(this);
                db.open();
                cursor = db.getTodosRegistros();

                numElementos = cursor.getCount();

                if (numElementos == 0) {

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                    dialogo.setTitle(getString(R.string.titulodebelogin2));
                    dialogo.setMessage(getString(R.string.textodialogodebelogin2));
                    dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            newFragment = new LoginFragment();
                            transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, newFragment);
                            transaction.attach(newFragment);
                            transaction.commit();




                        }
                    });
                    dialogo.show();

                } else {


                    AdaptadorBD dbnew = new AdaptadorBD(this);
                    dbnew.open();
                    dbnew.borrarTodosRegistros();
                    dbnew.close();

                    AlertDialog.Builder dialogologout = new AlertDialog.Builder(MainActivity.this);

                    dialogologout.setTitle(getString(R.string.titulologout));
                    dialogologout.setMessage(getString(R.string.textodialogologout));
                    dialogologout.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            AcercaFragment newFragment = new AcercaFragment();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, newFragment);
                            transaction.attach(newFragment);
                            transaction.commit();


                        }
                    });
                    dialogologout.show();


                }
                cursor.close();
                db.close();

                break;

            case R.id.buscador:

                db = new AdaptadorBD(this);
                db.open();
                cursor = db.getTodosRegistros();

                numElementos = cursor.getCount();

                if (numElementos == 0) {

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                    dialogo.setTitle(getString(R.string.titulodebelogin));
                    dialogo.setMessage(getString(R.string.textodialogodebelogin));
                    dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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

                } else {


                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                    dialogo.setTitle(getString(R.string.titulologin));
                    dialogo.setMessage(getString(R.string.textodialogologin));
                    dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {




                            newFragment = new BuscadorFragment();
                            transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, newFragment);
                            transaction.attach(newFragment);
                            transaction.commit();


                        }
                    });
                    dialogo.show();



                }
                cursor.close();
                db.close();


                break;
            case R.id.baja:

                db = new AdaptadorBD(this);
                db.open();
                cursor = db.getTodosRegistros();

                numElementos = cursor.getCount();

                if (numElementos == 0) {

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                    dialogo.setTitle(getString(R.string.titulodebebaja));
                    dialogo.setMessage(getString(R.string.textodialogodebebaja));
                    dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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

                } else {


                    newFragment = new BajaFragment();
                    transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.attach(newFragment);
                    transaction.commit();


                }
                cursor.close();
                db.close();


                break;
            case R.id.disclaimer:
                newFragment = new DisclaimerFragment();
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.attach(newFragment);
                transaction.commit();

                break;

            case R.id.manual:
                newFragment = new ManualFragment();
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.attach(newFragment);
                transaction.commit();

                break;
            case R.id.salir:

                Intent salida = new Intent(Intent.ACTION_MAIN); //Llamando a la activity principal
                finish(); // La cerramos.

                break;


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
