package eu.yfys.yourfutureyoursmartphone;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by yfys on 02/12/2018.
 */

public class SociosFragment extends Fragment {
    View view;

    private Button botonsocio1, botonsocio2, botonsocio3, botonsocio4 ;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=  inflater.inflate(R.layout.layout_socios, container, false);


        botonsocio1= (Button) view.findViewById(R.id.buttonlink1);
        botonsocio2= (Button) view.findViewById(R.id.buttonlink2);
        botonsocio3= (Button)  view.findViewById(R.id.buttonlink3);
        botonsocio4= (Button) view.findViewById(R.id.buttonlink4);

        botonsocio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("http://www.iescristobaldemonroy.es/wordpress/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);

            }
        });


        botonsocio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("http://ficaalcala.es/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);

            }
        });
        botonsocio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("http://www.itisgrassi.it/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);

            }
        });
        botonsocio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("http://www.ipsantarem.pt/en/655-2/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);

            }
        });

        return  view;
    }
}