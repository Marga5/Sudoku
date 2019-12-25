package pt.isec.a21230462.sudoku;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onM1(View view) {
        Intent intent = new Intent(this, EscolherDificuldade.class);
        startActivity(intent);
    }

    public void onM2(View view) {
        //Intent intent = new Intent(this, M2Activity.class);
        //startActivity(intent);
    }
}

