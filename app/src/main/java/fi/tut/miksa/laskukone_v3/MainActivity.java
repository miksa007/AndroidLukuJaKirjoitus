package fi.tut.miksa.laskukone_v3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DataLuokka data;
    private EditText editText1;
    private EditText editText2;
    private TextView textView1;
    private TextView textViewLoki;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data=new DataLuokka();
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        textView1=(TextView)findViewById(R.id.textView_summa);
        textViewLoki=findViewById(R.id.textViewloki);


        data.lueTiedostosta(this);
        textViewLoki.setText(data.getData());
        final Button button = findViewById(R.id.button_summa);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                laske();
            }
        });
        //Nollaa buttonin toiminta
        final Button buttonNollaa = findViewById(R.id.button_nollaa);
        buttonNollaa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Siira data
                //Luetaan arvo
                String message1 = editText1.getText().toString();
                String message2 = editText2.getText().toString();
                //muutetaan arvot kokonaisluvuiksi
                int luku=Integer.parseInt(message1);
                int luku2=Integer.parseInt(message2);

                int summa=luku+luku2;

                String lause=message1+"+"+message2+"="+summa;
                String tmp=data.getData()+"\n"+lause;
                data.setData(tmp);
                textViewLoki.setText(data.getData());
                //Nollaa kentät
                editText1.setText("");
                editText2.setText("");
                textView1.setText("");
            }
        });
    }

    public void laske(){
        //Luetaan arvo
        String message1 = editText1.getText().toString();
        String message2 = editText2.getText().toString();
        //muutetaan arvot kokonaisluvuiksi
        int luku=Integer.parseInt(message1);
        int luku2=Integer.parseInt(message2);

        int summa=luku+luku2;

        //luvun sijoitus näkymään

        textView1.setText(""+summa);
    }

    @Override
    protected void onPause() {
        super.onPause();
        data.kirjoitaTiedostoon(this);
    }

}
