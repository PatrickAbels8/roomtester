package com.example.roomtester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.roomtester.db.MyDatabase;
import com.example.roomtester.db.Schueler;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDatabase myDatabase;

    private EditText editText;
    private Button submitButton;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "schuledb").allowMainThreadQueries().build();

        //fill db manually
        for(int i=0; i<10; i++){
            Schueler s = new Schueler();
            s.setId(i);
            s.setHeight(i*10);
            s.setName("patrick"+Integer.toString(i));
            insert(s);
        }

        editText = (EditText) findViewById(R.id.query);
        submitButton = (Button) findViewById(R.id.submit);
        outputText = (TextView) findViewById(R.id.output);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = editText.getText().toString();
                request(query);
            }
        });



    }

    public void insert(Schueler s){
        myDatabase.myDao().addSchueler(s);
    }

    public void request(String q){
        try{
            List<Schueler> schueler = myDatabase.myDao().getSchueler(q);
            String o = "";
            for (Schueler s: schueler){
                o += Integer.toString(s.getId());
                o += s.getName();
                o += Integer.toString(s.getHeight());
                o += "\n";
            }
            outputText.setText(o);
        }catch (Exception e){
            outputText.setText("no valid query");
        }
    }
}
