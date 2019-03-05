package com.w3learnteam.w3learn.learn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import com.pddstudio.highlightjs.HighlightJsView;
import com.pddstudio.highlightjs.models.Language;
import com.pddstudio.highlightjs.models.Theme;
import com.w3learnteam.w3learn.R;

public class List_Code extends AppCompatActivity {

    HighlightJsView highlightJsView;
    View seeRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__code);

        //find and instantiate the view
        highlightJsView = (HighlightJsView) findViewById(R.id.code_view);

        //optional: register callbacks and style the view

        //register theme change listener
        highlightJsView.setOnThemeChangedListener(new HighlightJsView.OnThemeChangedListener() {
            @Override
            public void onThemeChanged(@NonNull Theme theme) {
            }
        });

        //change theme and set language to auto detect
        highlightJsView.setTheme(Theme.AGATE);
        highlightJsView.setHighlightLanguage(Language.HTML);
        //load the source (can be loaded by String, File or URL)
        highlightJsView.setSource("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "    <head>\n" +
                "    \t<link href=\"slider/images/logo2.png\" rel=\"shortcut icon\">\n" +
                "\t\t<meta charset=\"UTF-8\" />\n" +
                "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> \n" +
                "        <title>Indovote</title>\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> \n" +
                "        <meta name=\"description\" content=\"Slideshow with jmpress.js\" />\n" +
                "        <meta name=\"keywords\" content=\"jmpress, slideshow, container, plugin, jquery, css3\" />\n" +
                "        <meta name=\"author\" content=\"for Codrops\" />\n" +
                "        <link rel=\"shortcut icon\" href=\"../favicon.ico\"> \n" +
                "\t\t<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:700,300,300italic' rel='stylesheet' type='text/css'>\n" +
                "        <link rel=\"stylesheet\" type=\"text/css\" href=\"slider/css/demo.css\" />\n" +
                "        <link rel=\"stylesheet\" type=\"text/css\" href=\"slider/css/style_alt.css\" />\n" +
                "\t\t<!--[if lt IE 9]>\n" +
                "\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style_ie.css\" />\n" +
                "\t\t<![endif]-->\n" +
                "\t\t<!-- jQuery -->\n" +
                "\t\t<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js\"></script>\n" +
                "\t\t<!-- jmpress plugin -->\n" +
                "\t\t<script type=\"text/javascript\" src=\"slider/js/jmpress.min.js\"></script>\n" +
                "\t\t<!-- jmslideshow plugin : extends the jmpress plugin -->\n" +
                "\t\t<script type=\"text/javascript\" src=\"slider/js/jquery.jmslideshow.js\"></script>\n" +
                "\t\t<script type=\"text/javascript\" src=\"slider/js/modernizr.custom.48780.js\"></script>\n" +
                "\t\t<noscript>\n" +
                "\t\t\t<style>\n" +
                "\t\t\t.step {\n" +
                "\t\t\t\twidth: 100%;\n" +
                "\t\t\t\tposition: relative;\n" +
                "\t\t\t}\n" +
                "\t\t\t.step:not(.active) {\n" +
                "\t\t\t\topacity: 1;\n" +
                "\t\t\t\tfilter: alpha(opacity=99);\n" +
                "\t\t\t\t-ms-filter: \"progid:DXImageTransform.Microsoft.Alpha(opacity=99)\";\n" +
                "\t\t\t}\n" +
                "\t\t\t.step:not(.active) a.jms-link{\n" +
                "\t\t\t\topacity: 1;\n" +
                "\t\t\t\tmargin-top: 40px;\n" +
                "\t\t\t}\n" +
                "\t\t\t</style>\n" +
                "\t\t</noscript>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <div class=\"container\">\n" +
                "\t\t\t<!-- Codrops top bar -->\n" +
                "            <div class=\"codrops-top\">\n" +
                "               <span class=\"right\">\n" +
                "                    \n" +
                "           <a class=\"link link--login\" href=\"indovote.php\"><span>LOG</span><span>IN</span></a>\n" +
                "                </span>\n" +
                "                <div class=\"clr\"></div>\n" +
                "            </div><!--/ Codrops top bar -->\n" +
                "\t\t\t\n" +
                "\t\t\t<section id=\"jms-slideshow\" class=\"jms-slideshow\">\n" +
                "\t\t\t\t<div class=\"step\" data-color=\"color-1\" data-x=\"2000\" data-y=\"1000\" data-z=\"3000\" data-rotate=\"-20\">\n" +
                "\t\t\t\t\t<div class=\"jms-content\">\n" +
                "\t\t\t\t\t\t<h3>IndoVote</h3>\n" +
                "\t\t\t\t\t\t<p>Vote Kebudayaanmu Sekarang Juga<br>Batas Waktu : 1 Bulan dari sekarang !</p>\n" +
                "\t\t\t\t\t\t<br><p style=\"fonts-size:15px;\">&copy;CODETEAM</p>\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<img style=\"height:360px; width:360px; \" src=\"slider/images/S.png\" />\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t<div class=\"step\" data-color=\"color-2\" data-x=\"1000\" data-z=\"2000\" data-rotate=\"20\">\n" +
                "\t\t\t\t\t<div class=\"jms-content\">\n" +
                "\t\t\t\t\t\t<h3>Vote For Our Culture</h3>\n" +
                "\t\t\t\t\t\t<p>Vote untuk kebudayaan daerahmu dan jadikan budayamu menjadi budaya yang terpopuler</p>\n" +
                "\t\t\t\t\t\t<br><p style=\"fonts-size:15px;\">&copy;CODETEAM</p>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<img style=\"height:360px; width:360px; \"  src=\"slider/images/vote.png\" />\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t<div class=\"step\" data-color=\"color-3\" data-x=\"2000\" data-y=\"1500\" data-z=\"1000\" data-rotate=\"20\">\n" +
                "\t\t\t\t\t<div class=\"jms-content\">\n" +
                "\t\t\t\t\t\t<h3>Travelling Indonesia</h3>\n" +
                "\t\t\t\t\t\t<p>Jelajahi dan enikmati alam dan tempat tempat wisata di Indonesia</p>\n" +
                "\t\t\t\t\t\t<br><p style=\"fonts-size:15px;\">&copy;CODETEAM</p>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<img style=\"height:360px; width:360px; \" src=\"slider/images/wisata.png\" />\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t<div class=\"step\" data-color=\"color-4\" data-x=\"3000\" data-y=\"2000\">\n" +
                "\t\t\t\t\t<div class=\"jms-content\">\n" +
                "\t\t\t\t\t\t<h3>Baju Adat Daerah</h3>\n" +
                "\t\t\t\t\t\t<p>Mengenal beragam baju adat daerah di Indonesia</p>\n" +
                "\t\t\t\t\t\t<br><p style=\"fonts-size:15px;\">&copy;CODETEAM</p>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<img style=\"height:360px; width:360px; \" src=\"slider/images/rumah.png\" />\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t<div class=\"step\" data-color=\"color-5\" data-x=\"4000\" data-y=\"1500\" data-z=\"1000\" data-rotate=\"-20\">\n" +
                "\t\t\t\t\t<div class=\"jms-content\">\n" +
                "\t\t\t\t\t\t<h3>CODE TEAM</h3>\n" +
                "\t\t\t\t\t\t<p>SMK NEGERI 1 CIMAHI - RPL</p>\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<img style=\"height:360px; width:360px; \" src=\"slider/images/logocode.png\" />\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</section>\n" +
                "        </div>\n" +
                "\t\t<script type=\"text/javascript\">\n" +
                "\t\t\t$(function() {\n" +
                "\t\t\t\t\n" +
                "\t\t\t\tvar jmpressOpts\t= {\n" +
                "\t\t\t\t\tanimation\t\t: { transitionDuration : '0.8s' }\n" +
                "\t\t\t\t};\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t$( '#jms-slideshow' ).jmslideshow( $.extend( true, { jmpressOpts : jmpressOpts }, {\n" +
                "\t\t\t\t\tautoplay\t: true,\n" +
                "\t\t\t\t\tbgColorSpeed: '0.8s',\n" +
                "\t\t\t\t\tarrows\t\t: false\n" +
                "\t\t\t\t}));\n" +
                "\t\t\t\t\n" +
                "\t\t\t});\n" +
                "\t\t</script>\n" +
                "    </body>\n" +
                "</html>");

        highlightJsView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        seeRes = findViewById(R.id.goResult);
        seeRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_Code.this,Result.class);
                startActivity(intent);
            }
        });


    }
}

