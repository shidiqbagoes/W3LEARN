package com.w3learnteam.w3learn.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import cn.pedant.SweetAlert.SweetAlertDialog;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.florent37.shapeofview.ShapeOfView;
import com.w3learnteam.w3learn.R;

import java.util.ArrayList;

public class QuizRules extends AppCompatActivity {

    ShapeOfView play, Rule1, Rule2, Rule3, Rule4;
    TextView TPlay;
    Animation MoveRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz_rules);

        play = (ShapeOfView) findViewById(R.id.play);
        Rule1 = (ShapeOfView) findViewById(R.id.rule1);
        Rule2 = (ShapeOfView) findViewById(R.id.rule2);
        Rule3 = (ShapeOfView) findViewById(R.id.rule3);
        Rule4 = (ShapeOfView) findViewById(R.id.rule4);
        TPlay = (TextView) findViewById(R.id.tplay);

        MoveRotate = AnimationUtils.loadAnimation(this,R.anim.move_rotate);

        play.startAnimation(MoveRotate);

        TPlay.setTranslationX(600);
        Rule1.setTranslationX(300);
        Rule2.setTranslationX(300);
        Rule3.setTranslationX(300);
        Rule4.setTranslationX(300);

        TPlay.setAlpha(0);
        Rule1.setAlpha(0);
        Rule2.setAlpha(0);
        Rule3.setAlpha(0);
        Rule4.setAlpha(0);

        TPlay.animate().alpha(1).translationX(0).setDuration(800).setStartDelay(700);
        Rule1.animate().alpha(1).translationX(0).setDuration(800).setStartDelay(300);
        Rule2.animate().alpha(1).translationX(0).setDuration(800).setStartDelay(600);
        Rule3.animate().alpha(1).translationX(0).setDuration(800).setStartDelay(900);
        Rule4.animate().alpha(1).translationX(0).setDuration(800).setStartDelay(1100);

        TPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = new EditText(QuizRules.this);
                final SweetAlertDialog ADialog = new SweetAlertDialog(QuizRules.this,SweetAlertDialog.NORMAL_TYPE);
                    ADialog.setTitleText("Enter Your Name");
                    ADialog.setConfirmText("Ok");
                    ADialog.setCustomView(editText);
                    ADialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(final SweetAlertDialog sweetAlertDialog) {
                                if (editText.getText().toString().trim().equals("")) {
                                    SweetAlertDialog dialog = new SweetAlertDialog(QuizRules.this, SweetAlertDialog.WARNING_TYPE);
                                    dialog.setTitleText("Field Can't Be Empty");
                                    dialog.setConfirmText("Ok");
                                    dialog.show();
                                    dialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setVisibility(View.GONE);
                                    editText.setError("Field Can't Be Empty");
                                    ADialog.dismissWithAnimation();

                                } else if (editText.getText().toString().trim().length()<3) {
                                    SweetAlertDialog dialog = new SweetAlertDialog(QuizRules.this, SweetAlertDialog.WARNING_TYPE);
                                    dialog.setTitleText("Fill At Least 3 Alphabet");
                                    dialog.setConfirmText("Ok");
                                    dialog.show();
                                    dialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setVisibility(View.GONE);
                                    editText.setError("Fill At Least 3 Alphabet");
                                    ADialog.dismissWithAnimation();
                                } else {
                                    Intent i = new Intent(QuizRules.this, QuizTime.class);
                                    i.putExtra("nama", editText.getText().toString().trim());
                                    startActivity(i);
                                    ADialog.dismissWithAnimation();
                                    finish();
                                }
                            }
                        });
                ADialog.show();

                Button btn = (Button) ADialog.findViewById(R.id.confirm_button);
                btn.setBackgroundResource(R.drawable.sweetbtn);

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = new EditText(QuizRules.this);
                final SweetAlertDialog ADialog = new SweetAlertDialog(QuizRules.this, SweetAlertDialog.NORMAL_TYPE);
                ADialog.setTitleText("Enter Your Name");
                ADialog.setConfirmText("Ok");
                ADialog.setCustomView(editText);
                ADialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(final SweetAlertDialog sweetAlertDialog) {
                        if (editText.getText().toString().trim().equals("")) {
                            SweetAlertDialog dialog = new SweetAlertDialog(QuizRules.this, SweetAlertDialog.WARNING_TYPE);
                            dialog.setTitleText("Field Can't Be Empty");
                            dialog.setConfirmText("Ok");
                            dialog.show();
                            dialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setVisibility(View.GONE);
                            editText.setError("Field Can't Be Empty");
                            ADialog.dismissWithAnimation();
                        } else if (editText.getText().toString().trim().length()<3) {
                            SweetAlertDialog dialog = new SweetAlertDialog(QuizRules.this, SweetAlertDialog.WARNING_TYPE);
                            dialog.setTitleText("Fill At Least 3 Alphabet");
                            dialog.setConfirmText("Ok");
                            dialog.show();
                            dialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setVisibility(View.GONE);
                            editText.setError("Fill At Least 3 Alphabet");
                            ADialog.dismissWithAnimation();
                        } else
                         {
                            Intent i = new Intent(QuizRules.this, QuizTime.class);
                            i.putExtra("nama", editText.getText().toString().trim());
                            startActivity(i);
                            ADialog.dismissWithAnimation();
                            finish();
                        }
                    }
                });
                ADialog.show();

                Button btn = (Button) ADialog.findViewById(R.id.confirm_button);
                btn.setBackgroundResource(R.drawable.sweetbtn);
            }
            });
    }

}
