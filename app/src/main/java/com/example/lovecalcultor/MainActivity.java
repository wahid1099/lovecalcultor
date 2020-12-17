package com.example.lovecalcultor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.ConversationActions;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
     EditText bname,gname;
     Button calculte;
    DecimalFormat df = new DecimalFormat();
   TextView result;
    private double percent = 0, check = 0, total;
 //   OkHttpClient client = new OkHttpClient();
 private String name1, name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   bname=findViewById(R.id.boyname);
      //  gname=findViewById(R.id.girname);
        calculte=findViewById(R.id.calculte);
      //  result=findViewById(R.id.result);

        calculte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchdata();
            }
        });

    }
  //  @AllArgsConstructor
    private void fetchdata(){
        percent = 0; check = 0;
        EditText firstName = (EditText) findViewById(R.id.boyname);
        EditText secondName = (EditText) findViewById(R.id.girname);
        result = (TextView) findViewById(R.id.result);
        name1 = firstName.getText().toString();
        name1 = name1.trim();
        name1 = name1.toUpperCase();
        name2 = secondName.getText().toString();
        name2 = name2.trim();
        name2 = name2.toUpperCase();
        //Main Code Starts here
        if(name1.isEmpty() || name1.equals(""))
        {
            result.setText("0%");
            Toast.makeText(getApplicationContext(), "Fill your name.", Toast.LENGTH_SHORT).show();
        }
        else if(name2.isEmpty() || name2.equals(""))
        {
            result.setText("0%");
            Toast.makeText(getApplicationContext(), "Fill your crush's name.", Toast.LENGTH_SHORT).show();
        }
        if(!name1.isEmpty() && !name2.isEmpty())
        {
            for (int i = 0; i < name1.length(); i++) {
                for (int j = 0; j < name2.length(); j++) {
                    if ((name1.charAt(i) == name2.charAt(j))) {
                        check++;
                    }
                }
            }
            total = (name1.length() + name2.length());
            percent = ((check) / (total) * 100);
            exceptionalCase();
        }
    }
    private void exceptionalCase()
    {
        if(percent<30)
        {
            total = (name1.length()+name2.length());
            percent = (((check)/(total)*100)+36);
        }
        else if(percent<40)
        {
            total = (name1.length()+name2.length());
            percent = (((check)/(total)*100)+43);
        }
        else if(percent>100)
        {
            if(name1.length()<10)
            {
                percent = name2.length()*9;
            }
            else if(name2.length()<10)
            {
                percent = name2.length()*7;
                if (name1.length()>10)
                {
                    percent = name1.length()/10;
                    if(percent < 10)
                    {
                        percent = percent + 35;
                    }
                    else if(percent < 20)
                    {
                        percent = percent + 30;
                    }
                    else if(percent < 30)
                    {
                        percent = percent + 20;
                    }
                    else if(percent < 40)
                    {
                        percent = percent + 15;
                    }
                    else if(percent > 100)
                    {
                        percent = 85;
                    }
                }
            }
            else if(name2.length()>10)
            {
                percent = name2.length()/9;
                if (name1.length()>10)
                {
                    percent = name1.length()/10;
                    if(percent < 10)
                    {
                        percent = percent + 35;
                    }
                    else if(percent < 20)
                    {
                        percent = percent + 30;
                    }
                    else if(percent < 30)
                    {
                        percent = percent + 20;
                    }
                    else if(percent < 40)
                    {
                        percent = percent + 15;
                    }
                    else if(percent > 100)
                    {
                        percent = 85;
                    }
                }
            }
        }
        df.setMaximumFractionDigits(2);
        result.setText(df.format(percent)+"%");
    }
}
