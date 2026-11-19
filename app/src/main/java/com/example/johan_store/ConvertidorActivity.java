package com.example.johan_store;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class ConvertidorActivity extends AppCompatActivity {

    private static final double TASA_USD = 0.0405;
    private static final double TASA_EUR = 0.03704;

    private EditText txtingresodenumeros;
    private TextView txtmostrarconversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_convertidor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtingresodenumeros = findViewById(R.id.txtingresodenumeros);
        txtmostrarconversion = findViewById(R.id.txtmostrarconversion);
    }

    private double obtenerMonto() {
        String texto = txtingresodenumeros.getText().toString().trim();
        if (texto.isEmpty()) {
            txtingresodenumeros.setError("Ingrese un monto");
            txtmostrarconversion.setText("");
            return -1;
        }
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            txtingresodenumeros.setError("Monto no válido");
            txtmostrarconversion.setText("");
            return -1;
        }
    }

    public void convertirDolar(View view) {
        double lempiras = obtenerMonto();
        if (lempiras < 0) return;
        txtmostrarconversion.setText(String.format(Locale.US, "USD %.2f", lempiras * TASA_USD));
    }

    public void convertirEuro(View view) {
        double lempiras = obtenerMonto();
        if (lempiras < 0) return;
        txtmostrarconversion.setText(String.format(Locale.US, "EUR %.2f", lempiras * TASA_EUR));
    }

    public void regresar(View view) {
        finish();
    }
}