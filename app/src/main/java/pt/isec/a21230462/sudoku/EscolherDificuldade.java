package pt.isec.a21230462.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EscolherDificuldade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_dificuldade);
    }

    public void onFacil(View view){
        Intent intent = new Intent(this, TabuleiroJogo.class);
        startActivity(intent);
    }
}
