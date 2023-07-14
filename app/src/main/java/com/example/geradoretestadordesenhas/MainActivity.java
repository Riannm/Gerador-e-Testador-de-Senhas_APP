package com.example.geradoretestadordesenhas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText lerTamanho;
    EditText senhaInputUser;
    TextView senhaGerada;
    TextView resultadoSenha;
    TextView textView2;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lerTamanho = findViewById(R.id.lerTamanho);
        senhaGerada = findViewById(R.id.senhaGerada);

        senhaInputUser = findViewById(R.id.senhaInputUser);
        resultadoSenha = findViewById(R.id.resultadoSenha);

        textView2 = findViewById(R.id.textView2);

    }

    public void gerarSenha(View view) {

        Random random = new Random();
        StringBuilder stringbuilder = new StringBuilder();
        int i;

        String tamanhoSenha = lerTamanho.getText().toString();

        if (!tamanhoSenha.isEmpty()) {

            textView2.setText("A senha gerada é: ");

            int tamanhoSenhaInt = Integer.parseInt(tamanhoSenha);

            String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"#$%&'()*+,-./:;?@[\\]~";

            stringbuilder.setLength(0); // Clear the String Builder

            for (i = 0; i < tamanhoSenhaInt; i++) {
                int IndexRandomico = random.nextInt(caracteres.length());
                char randomChar = caracteres.charAt(IndexRandomico);
                stringbuilder.append(randomChar);
            }

            String senhaRandom = stringbuilder.toString();
            senhaGerada.setText(senhaRandom);
        } else {
            textView2.setText("Por favor insira o tamanho: ");
        }
    }


    public void testarSenha(View view) {

        String especial_caracteres = "[!@#$%&*()_+=|<>?{}\\[\\]~0123456789-]";

        String senha = String.valueOf(senhaInputUser.getText());

        if (!senha.isEmpty()) {

            if (senha.length() >= 10 && senha.matches(".*" + especial_caracteres + ".*")) {
                String fullText = "Senha <font color='#14B82F'>forte</font>";
                resultadoSenha.setText(Html.fromHtml(fullText));
            } else if (senha.length() >= 8 && senha.length() < 10 && senha.matches(".*" + especial_caracteres + ".*")) {
                String fullText = "Senha <font color='#E8D50E'>média</font>";
                resultadoSenha.setText(Html.fromHtml(fullText));
            } else if (senha.length() < 8 || !senha.matches(".*" + especial_caracteres + ".*")) {
                String fullText = "Senha <font color='#A4212F'>fraca</font>";
                resultadoSenha.setText(Html.fromHtml(fullText));
            }
        } else {
            resultadoSenha.setText("Por favor insira uma senha!");
        }
    }
}