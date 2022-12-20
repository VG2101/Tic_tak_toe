package com.example.v_tic_tak_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer=1 , count1=0, count2=0, count=0;
    int [] playerWins ={0 , 0};
    int [] gameState = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    int [] [] winState = { {0,1,2}, {3,4,5}, {6,7,8},
                           {0,3,6}, {1,4,7}, {2,5,8},
                           {0,4,8}, {2,4,6} };
    TextView textView, player1,player2; boolean gameActive= true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =findViewById(R.id.textView);
        player1=findViewById(R.id.textView4);
        player2=findViewById(R.id.textView5);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!gameActive){
                    gameReset(view);
                }
            }
        });
    }
    public void playerTap(View view){
        ImageView img = (ImageView) view ;
        int position = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        else {
            if (gameState[position] == 0 ) {
                gameState[position] = activePlayer;
                img.setTranslationY(-1000f);
                if (activePlayer == 1) {
                    img.setImageResource(R.drawable.x);
                    count1 += 1;
                    count += 1;
                    activePlayer = 2;
                } else {
                    img.setImageResource(R.drawable.o);
                    count2 += 1;
                    count += 1;
                    activePlayer = 1;
                }
                img.animate().translationYBy(1000f).setDuration(300);
            }
        }
        if(count1>=3 || count2>=3){
            winnerCheck();
        }
        if(count==9){
            gameReset(view);
        }
    }
    public void winnerCheck(){
        String s;
        for (int [] winPosition: winState){
            if (gameState[winPosition[0]]==gameState[winPosition[1]] && gameState[winPosition[1]]==gameState[winPosition[2]] && gameState[winPosition[0]]!=0){
                if(gameState[winPosition[0]]==1){
                    s="Player 1 Win's";
                    textView.setText(s);
                    playerWins[0] += 1;
                }
                else {
                    s="Player 2 Win's";
                    textView.setText(s);
                    playerWins[1] +=1;
                }
              gameActive=false;
            }
        }
    }
    public void gameReset(View view){
        String s;
        textView.setText(R.string.welcome);
        s= Integer.toString(playerWins[0]);
        player1.setText(s);
        s= Integer.toString(playerWins[1]);
        player2.setText(s);
        for (int i=0; i<gameState.length ;i++){
            gameState[i]=0;
        }
        count=0; count1=0; count2=0;
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        gameActive =true;
    }
}