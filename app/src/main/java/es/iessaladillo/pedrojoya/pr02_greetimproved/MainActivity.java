package es.iessaladillo.pedrojoya.pr02_greetimproved;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.iessaladillo.pedrojoya.pr02_greetimproved.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;
    String name, surname;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot() );
        setupViews();

    }

    private void setupViews() {
       // Boton de saludar
        binding.btnGreet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getandsetgreet();
                count++;
                binding.contador.setText(count + "/10");
                binding.progressBar.setProgress(count);
                if (count >= 10 && binding.lblSwitch.isChecked() == false) {

                    premium();
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    binding.contador.setVisibility(View.INVISIBLE);
                }
                else {
                    getandsetgreet();
                }
            }
        });
        //Boton del premium
        binding.lblSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.lblSwitch.isChecked() == true) {
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    binding.contador.setVisibility(View.INVISIBLE);
                } else {
                    count = 0;
                    binding.contador.setText(count + "/10");
                    binding.progressBar.setProgress(View.VISIBLE);
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.contador.setVisibility(View.VISIBLE);
                }
            }
        });
        //Botones del género
        binding.rdbMr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.imgFace.setImageResource(R.drawable.ic_mr);
            }
        });
        binding.rdbMs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.imgFace.setImageResource(R.drawable.ic_mrs);
            }
        });
        binding.rdbMrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.imgFace.setImageResource(R.drawable.ic_ms);
            }
        });
        //Escritura en EditText nombre
        binding.editNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validate(binding.editNombre);
            }
        });
        //Escritura en EditText apellido
        binding.editApellido.addTextChangedListener((new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                validate(binding.editApellido);
            }
        }));
        binding.editNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                    binding.lblContadorNombre.setTextColor(getResources().getColor(R.color.colorAccent));

                }
                else{

                    binding.lblContadorNombre.setTextColor(getResources().getColor(R.color.textPrimary));
                }
            }
        });
        binding.editApellido.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                    binding.lblContadorApellido.setTextColor(getResources().getColor(R.color.colorAccent));

                }
                else{

                    binding.lblContadorApellido.setTextColor(getResources().getColor(R.color.textPrimary));
                }
            }
        });


    }

    private void premium() {

        Toast.makeText(this, getString(R.string.compra_premium), Toast.LENGTH_SHORT).show();
    }

    private void getandsetgreet() {
        name = binding.editNombre.getText().toString();
        surname = binding.editApellido.getText().toString();

       if(!name.isEmpty() && !surname.isEmpty()){

           if(binding.chkPolitely.isChecked() == true){
               polite(name,surname);
           }else{
               unpolite(name,surname);
           }
       }
       else{
           if(name.isEmpty()){

               binding.editNombre.setError(getString(R.string.invalid_requiered));
               binding.editNombre.requestFocus();
           }
           else{

               binding.editApellido.setError(getString(R.string.invalid_requiered));
               binding.editApellido.requestFocus();
           }
       }
    }

    public void polite(String name, String surname){
        String tipo;

        if(binding.rdbMr.isChecked() == true){
           tipo = binding.rdbMr.getText().toString();
         Toast.makeText(this,
                    getString(R.string.politely, tipo, name,surname),
                    Toast.LENGTH_LONG).show();
        }
        if(binding.rdbMs.isChecked() == true){
            tipo = binding.rdbMr.getText().toString();
            Toast.makeText(this,
                  getString(R.string.politely, tipo, name,surname),
                  Toast.LENGTH_LONG).show();
        }
        if(binding.rdbMrs.isChecked() == true){
            tipo = binding.rdbMr.getText().toString();
           Toast.makeText(this,
                    getString(R.string.politely, tipo, name,surname),
                    Toast.LENGTH_LONG).show();
        }


    }

    public void unpolite(String name, String surName){
        String tipo;

        if(binding.rdbMr.isChecked() == true){

            tipo = binding.rdbMr.getText().toString();
            Toast.makeText(this,
                    getString(R.string.politely, tipo, name,surname),
                    Toast.LENGTH_SHORT).show();
        }
        if(binding.rdbMs.isChecked() == true){
            tipo = binding.rdbMs.getText().toString();

            Toast.makeText(this,
                    getString(R.string.unpolite, tipo, name,surname),
                    Toast.LENGTH_SHORT).show();
        }
        if(binding.rdbMrs.isChecked() == true){

            tipo = binding.rdbMs.getText().toString();
            Toast.makeText(this,
                    getString(R.string.politely, tipo, name,surname),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void validate(EditText comprobar) {
        if(comprobar.length() == 0){
            comprobar.setError(getString(R.string.invalid_requiered));
        }
    }

}