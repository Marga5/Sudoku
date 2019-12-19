package com.example.aulasudoku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import pt.isec.ans.sudokulibrary.Sudoku;

public class MainActivity extends AppCompatActivity {

    SudokuView sudokuView;

    //MAIS COMPLICADO SERA O LANDSCAPE, QUANDO TELEMOVEL GIRA, SOCKET É RECRIADO E TEM DE SER LIGADO OUTRA VEZ
    //MUITOS VAO FAZER SO EM PORTRAIT XD

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout flSudoku = findViewById(R.id.flSudoku);
        sudokuView = new SudokuView(this);
        flSudoku.addView(sudokuView);
    }

    public void onGerar(View view) {
        //TEMOS 81 NUMEROS
        //DEVE SER FEITO NO CONTEXTO DE UMA THREAD E NAO ASSIM
        //GERA TABULEIRO COM NUMERO E COPIA PARA UM TABULEIRO VAZIO,
        //CRIAR THREAD E NO QUANDO ESTA ACABAR DIZER LHE O QUE FAZER
        String strJson = Sudoku.generate(7);
        Log.i("Sudoku","JSON: "+strJson);
        try{
            JSONObject json = new JSONObject(strJson);
            if(json.optInt("result",0)==1){
                JSONArray arrayJson = json.getJSONArray("board");
                //IMPLEMENTAR O CONVERT!!
                int [][] array = convert(arrayJson);
                sudokuView.setBoard(array);
            }
        } catch (Exception e){

        }
    }
    //CONVERT DO GERAR
    private int[][] convert(JSONArray arrayJson) {
        int [][] array = new int [9][9];
        try{
            for(int r=0;r<9;r++){
                JSONArray jsonRow = arrayJson.getJSONArray(r);
                for(int c=0;c<9;c++){
                    array[r][c] = jsonRow.getInt(c);
                }
            }
        } catch (Exception e){

        }
        return array;
    }

    public void onResolver(View view) {
        //NAO DEVE SER APRESENTADO, SO VER SE TEM SOLUCAO
        try{
            JSONObject json = new JSONObject();
            json.put("board",convert(sudokuView.board));
            //PODE HAVER TABULEIROS QUE DEMOREM MUITO TEMPO (HORAS) A SER CALCULADOS (OS QUE TÊM POUCOS NUMEROS), CONVEM PASSAR COM + de 17 NUMEROS!!
            String strJson = Sudoku.solve(json.toString(), 1500);
            json = new JSONObject(strJson);
            if(json.optInt("result",0)==1){
                JSONArray arrayJson = json.getJSONArray("board");
                //IMPLEMENTAR O CONVERT!!
                int [][] array = convert(arrayJson);
                sudokuView.setBoard(array);
            }
        } catch (Exception e){

        }

    }
    //CONVERT DO RESOLVER
    private JSONArray convert(int [][]array){
        JSONArray arrayJson = new JSONArray();
        try{
            for(int r=0;r<9;r++){
                JSONArray jsonRow = new JSONArray();
                for(int c=0;c<9;c++){
                    jsonRow.put(array[r][c]);
                }
                arrayJson.put(jsonRow);
            }
        } catch (Exception e){

        }
        return arrayJson;
    }
}
