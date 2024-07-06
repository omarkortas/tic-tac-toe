package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //declaration des variables
    private boolean enabled = true; // Indique si le jeu est actif ou non
    private String winner = " "; //indique qui gagne la partie
    private char turn = 'X';
    private char[] chars = new char[9];
    private TextView label;
    private ArrayList<TextView> lbs;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //affectation par les id
        label = findViewById(R.id.text_view_p1);

        TextView lb1 = findViewById(R.id.lb1);
        TextView lb2 = findViewById(R.id.lb2);
        TextView lb3 = findViewById(R.id.lb3);
        TextView lb4 = findViewById(R.id.lb4);
        TextView lb5 = findViewById(R.id.lb5);
        TextView lb6 = findViewById(R.id.lb6);
        TextView lb7 = findViewById(R.id.lb7);
        TextView lb8 = findViewById(R.id.lb8);
        TextView lb9 = findViewById(R.id.lb9);

        lbs = new ArrayList<>();
        lbs.add(lb1);
        lbs.add(lb2);
        lbs.add(lb3);
        lbs.add(lb4);
        lbs.add(lb5);
        lbs.add(lb6);
        lbs.add(lb7);
        lbs.add(lb8);
        lbs.add(lb9);
        //les conditions de jeu ca depend la click du button
        for (TextView lb : lbs) {
            lb.setOnClickListener(view -> {
                if (enabled) {
                    if (lb.getText().toString().isEmpty()) {
                        lb.setText(String.valueOf(turn));
                        int position = lbs.indexOf(lb);
                        chars[position] = turn;

                        if (checkWinner()) {
                            enabled = false;
                            if (winner.equals("X")) {
                                label.setText("Player 1 wins!");// Affiche le message de victoire pour le 1er joueur
                                label.setTextColor(getResources().getColor(R.color.blue));
                            } else if (winner.equals("O")) {
                                label.setText("Player 2 wins!");// Affiche le message de victoire pour le 2eme joueur
                                label.setTextColor(getResources().getColor(R.color.red));
                            }
                        } else {
                            if (isBoardFull()) {
                                enabled = false;
                                label.setText("Draw");// Affiche un message de match nul
                            } else {
                                turn = (turn == 'X') ? 'O' : 'X';
                                label.setText((turn == 'X') ? "Player 1, it's your turn" : "Player 2, it's your turn");
                            } // le tour de chaque joueur
                        }
                    } else {
                        label.setText("Position Taken!");
                    }
                }
            });
        }
    }

    private boolean checkWinner() {
        for (int[] winPos : new int[][]{
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
                {0, 4, 8}, {2, 4, 6}             // diagonals
        }) {
            if (chars[winPos[0]] == chars[winPos[1]] &&
                    chars[winPos[1]] == chars[winPos[2]] &&
                    chars[winPos[0]] != '\0') {
                winner = String.valueOf(chars[winPos[0]]);
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (char c : chars) {
            if (c == '\0') {
                return false;
            }
        }
        return true;
    }

    public void newGame(View view) {
        for (TextView lb : lbs) {
            lb.setText("");
        }
        winner = " ";
        turn = 'X';
        enabled = true;
        label.setText("Player 1, it's your turn");
        label.setTextColor(getResources().getColor(R.color.black));
        chars = new char[9];
    }
}
