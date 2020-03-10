package com.example.roomtester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.room.Room;
import androidx.sqlite.db.SimpleSQLiteQuery;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.roomtester.db.MyDatabase;
import com.example.roomtester.db.Schueler;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDatabase myDatabase;

    private EditText editText;
    private Button submitButton;
    private TableLayout output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "schuledb").allowMainThreadQueries().build();

        //fill db manually
        /*for(int i=0; i<10; i++){
            Schueler s = new Schueler();
            s.setId(i);
            s.setHeight(i*10);
            s.setName("patrick"+Integer.toString(i));
            insert(s);
        }*/

        editText = (EditText) findViewById(R.id.query);
        submitButton = (Button) findViewById(R.id.submit);
        output = (TableLayout) findViewById(R.id.output);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = editText.getText().toString();
                request(query);
            }
        });



    }

    public void removeRows(){
        output.removeAllViews();
    }

    public void addHeadRow(){
        //new row for attribute names
        TableRow newRow = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        newRow.setLayoutParams(lp);

        TextView id = new TextView(this);
        id.setText("id");
        newRow.addView(id);

        TextView name = new TextView(this);
        name.setText("schueler_name");
        newRow.addView(name);

        TextView height = new TextView(this);
        height.setText("schueler_height");
        newRow.addView(height);

        output.addView(newRow);
    }

    public void addRow(Object o){
        Log.d("add object: ", "o");
        if(o instanceof Schueler){
            Log.d("add object: ", "s");
            Schueler s = (Schueler) o;
            //new row for new instance with all the attributes
            TableRow newRow = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            newRow.setLayoutParams(lp);
            Log.d("###", "added row");

            TextView id = new TextView(this);
            id.setText(Integer.toString(s.getId()));
            newRow.addView(id);
            Log.d("###", "id");

            TextView name = new TextView(this);
            name.setText(s.getName());
            newRow.addView(name);
            Log.d("###", "name");

            TextView height = new TextView(this);
            height.setText(Integer.toString(s.getHeight()));
            newRow.addView(height);
            Log.d("###", "height");

            output.addView(newRow);
        }
    }

    public void insert(Schueler s){
        myDatabase.myDao().addSchueler(s);
    }

    public void request(String q){
        try{
            removeRows();
            List<Schueler> schueler = myDatabase.myDao().getSchueler(new SimpleSQLiteQuery(q));
            addHeadRow();
            for(int i=0; i<schueler.size(); i++){
                addRow(schueler.get(i));
            }
            editText.setBackgroundColor(Color.parseColor("#ffffff"));

        }catch (Exception e){
            editText.setBackgroundColor(Color.parseColor("#ff0000"));
        }
    }
}
