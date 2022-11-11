package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText jetcantidad;
    TextView jtvplanspa,jtvdescuento, jtvtotal;
    RadioButton jrbplabsencillo,jrbplanespecial,jrbplanfull;
    CheckBox jcbchoclaterapia,jcbmascarillacapilar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        jetcantidad=findViewById(R.id.etcantidad);
        jtvplanspa=findViewById(R.id.tvplanspa);
        jtvdescuento=findViewById(R.id.tvdescuento);
        jtvtotal=findViewById(R.id.tvtotal);
        jrbplabsencillo=findViewById(R.id.rbplansencillo);
        jrbplanespecial=findViewById(R.id.rbplanespecial);
        jrbplanfull=findViewById(R.id.rbplanfull);
        jcbchoclaterapia=findViewById(R.id.cbchocolaterapia);
        jcbmascarillacapilar=findViewById(R.id.cbmascarillacapilar);
    }

    public void Calcular_valor(View view){
        String cantidad_planes=jetcantidad.getText().toString();
        if (cantidad_planes.isEmpty()){
            Toast.makeText(this, "La cantidad de planes es requerida", Toast.LENGTH_SHORT).show();
            jetcantidad.requestFocus();
        }
        else{
            int cantidad,valor_plan,descuento = 0,valor_total,valor_opcional;
            cantidad= Integer.parseInt(cantidad_planes);

            if (cantidad < 1){
                Toast.makeText(this, "La cantidad mínima requerida es 1", Toast.LENGTH_SHORT).show();
                jetcantidad.requestFocus();
            }
            else{
                if (jrbplanfull.isChecked()){
                    jtvplanspa.setText("300000");
                    valor_plan=300000;
                }
                else{
                    if (jrbplanespecial.isChecked()){
                        jtvplanspa.setText("200000");
                        valor_plan=200000;
                    }
                    else {
                        jtvplanspa.setText("100000");
                        valor_plan=100000;
                    }
                }

                if (cantidad <4){
                    jtvdescuento.setText("0%");
                    descuento= 0;

                }
                else {
                    if (cantidad >= 4 && cantidad <= 8){
                        jtvdescuento.setText("20%");
                        descuento= (valor_plan * cantidad)*20/100;

                    }
                    else {
                        if (cantidad >= 2) {
                            jtvdescuento.setText("10%");
                            descuento = (valor_plan * cantidad) * 10 / 100;

                        }
                    }
                }

                if (jcbchoclaterapia.isChecked()){

                    valor_opcional= cantidad * 150000;

                }
                else {

                    valor_opcional= 0;

                }

                if (jcbmascarillacapilar.isChecked()){

                    valor_opcional= cantidad * 100000;

                }
                else {

                    valor_opcional= 0;

                }

                valor_total= (valor_plan * cantidad) - descuento + valor_opcional;
                jtvtotal.setText(String.valueOf(valor_total));

            }
        }

    }

    public void LimpiarDatos(View view){
        //System.out.println("El codigo pasa por aqui");
        jrbplanfull.setChecked(true);
        jcbchoclaterapia.setChecked(false);
        jcbmascarillacapilar.setChecked(false);
        jtvdescuento.setText("Descuento");
        jtvplanspa.setText("100000");

        jtvtotal.setText("0");
        jetcantidad.setText("");
        jetcantidad.requestFocus();
    }

}




