package br.com.beberagua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify;
    private EditText editMinutes;
    private TimePicker timePicker;

    private TextView textMenssage;

    private int hour;
    private int minute;
    private int interval;
/*status do botão trocando de cor / ação*/
    private boolean activated = false;
/* usando um BD simples interno do android */
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotify = findViewById(R.id.btn_notify);
        editMinutes = findViewById(R.id.edit_txt_number_interval);
        timePicker = findViewById(R.id.time_picker);

        timePicker.setIs24HourView(true);

        /* preparando o BD*/
        preferences = getSharedPreferences("dbagua", Context.MODE_PRIVATE);
        /* verificando se há dados gravados */
        activated = preferences.getBoolean("activated", false);

        if(activated){

            btnNotify.setText(R.string.pause);
            btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.black));

            int interval = preferences.getInt("interval",0);
            int hour = preferences.getInt("hour",timePicker.getCurrentHour());
            int minute = preferences.getInt("minutes",timePicker.getCurrentMinute());

            String msgTela = ""+hour+" Hora(s) : "+minute+" Minuto(s) com Intervalo "+interval;
            mostrarSelecao(msgTela);

            editMinutes.setText(String.valueOf(interval));
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);


        }

        /* metodo 3 - outra forma de declarar pode ser tudo junto em um código só sem precisar ficar criando funções
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sInterval = editMinutes.getText().toString();

                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();
                interval = Integer.parseInt(sInterval);

                String msgTela = "" + hour + " Hora(s) : " + minute + " Minuto(s) com Intervalo " + interval;
                Log.d("Teste", "" + msgTela + "");

                mostrarSelecao(msgTela);
            }
        });*/

        /* metodo 2 declarar on click
        btnNotify.setOnClickListener(notifyClick);*/
    }

    /*metodo 2 - variavel para o metodo onclickLIster
    public View.OnClickListener notifyClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            String sInterval = editMinutes.getText().toString();

            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
            interval = Integer.parseInt(sInterval);

            String msgTela = ""+hour+" Hora(s) : "+minute+" Minuto(s) com Intervalo "+interval;
            Log.d("Teste",""+msgTela+"");

            mostrarSelecao(msgTela);
        }
    };*/

    /*metodo 1 fácil via xml Onclick button  add no xml do botão =>   android:onClick="notifyClick"*/

    public void notifyClick(View view){
        String sInterval = editMinutes.getText().toString();
        if(sInterval.isEmpty()){
            Toast.makeText(this, R.string.error_msg, Toast.LENGTH_LONG).show();
            return;
        }

        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();
        interval = Integer.parseInt(sInterval);
        if(!activated) {
            mudarCorBtn();
            /*gravando dados de estado quando ativado*/
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("activated", true);
            editor.putInt("interval", interval);
            editor.putInt("hour", hour);
            editor.putInt("minutes", minute);
            editor.apply();

        }else{
            mudarCorBtn();
            /* "apagando" dados de estado quando ativado*/
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("activated", false);
            editor.remove("interval");
            editor.remove("hour");
            editor.remove("minutes");
            editor.apply();
        }
        String msgTela = ""+hour+" Hora(s) : "+minute+" Minuto(s) com Intervalo "+interval;
        Log.d("Teste",""+msgTela+"");

        mostrarSelecao(msgTela);

    }

    public void mostrarSelecao(String msg1){
        textMenssage = findViewById(R.id.textResult);
        textMenssage.setText("Selecionado: "+msg1+"");
    }

    public void mudarCorBtn(){
        //Toast.makeText(this, "Status "+activated, Toast.LENGTH_LONG).show();
        if(!activated) {
            btnNotify.setText(R.string.pause);
            btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.black));
            activated = true;
        } else {
            btnNotify.setText(R.string.notify);
            btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            activated = false;
        }
    }
}