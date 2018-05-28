package eu.yfys.yourfutureyoursmartphone;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * Created by yfys on 02/12/2018.
 */


public class EnlacesFragment extends Fragment {

    Button boton1, boton2;
    ImageButton facebook, twitter;

    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_enlaces, container, false);
        boton1 = (Button) view.findViewById(R.id.botonEnlace1);

        boton1.setOnClickListener(
                new View.OnClickListener()
                { @Override public void onClick(View v)
                { Uri webpage = Uri.parse("https://yfys.eu");
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(intent); }

                });

        boton2 = (Button) view.findViewById(R.id.botonEnlace2);

        boton2.setOnClickListener(
                new View.OnClickListener()
                { @Override public void onClick(View v)
                { Uri webpage = Uri.parse("https://yfys.eu/dissemination/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent); }

                });
        facebook = (ImageButton) view.findViewById(R.id.facebook);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://www.facebook.com/YFYSmartphone/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });

        twitter = (ImageButton) view.findViewById(R.id.twitter);

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://twitter.com/@yfysmartphone");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });

        return view;

    }
}