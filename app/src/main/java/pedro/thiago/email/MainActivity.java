package pedro.thiago.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        //Definição da ação de click do botão
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtendo dados digitados pelo usuário.
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                Intent i = new Intent(Intent.ACTION_SENDTO);
                //Criando uma intenção implicita para o android

                i.setData(Uri.parse("mailto:"));
                //Definindo o tipo de app para pesquisar, no caso de enviar emails

                String[] emails = new String[]{email};
                //Criando strings com emails
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);
                //Enviando os dados de email, assunto e texto para a intenção

                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                    //Tentando ativar a intenção pesquisando por apps compativeis para a ação SENDTO
                }
                catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "Não há nenhum APP que possa realizar essa ação",
                            Toast.LENGTH_LONG).show();
                            //Mostrando mensagem para avisar que não há nenhum app para a ação
                }
            }
        });
    }
}