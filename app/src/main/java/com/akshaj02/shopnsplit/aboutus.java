//package com.akshaj02.shopnsplit;
//
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.provider.CalendarContract;
//import android.sax.Element;
//import android.view.Gravity;
//import android.view.View;
//import android.widget.Toast;
//import java.util.Calendar;
//
//import mehdi.sakout.aboutpage.AboutPage;
////import mehdi.sakout.aboutpage.Element;
//
//public class aboutus extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_aboutus);
//        Element adsElement = new Element();
//        View aboutPage = new AboutPage(this)
//                .isRTL(false)
//                .setDescription("Splitwise is a Providence, RI-based company that makes it easy to split bills with friends and family. We organize all your shared expenses and IOUs in one place, so that everyone can see who they owe. Whether you are sharing a ski vacation, splitting rent with roommates, or paying someone back for lunch, Splitwise makes life easier. We store your data in the cloud so that you can access it anywhere: on iPhone, Android, or on your computer. ")
//                .addItem(new Element().setTitle("Version 1.0"))
//                .addGroup("CONNECT WITH US!")
//                .addEmail("yashvwaghmare@gmail.com")
//                //.addWebsite("Your website/")
//                //.addYoutube("UCbekhhidkzkGryM7mi5Ys_w")   //Enter your youtube link here (replace with my channel link)
//                //.addPlayStore("com.example.Shop'N'Split")   //Replace all this with your package name
//                //.addInstagram("akshajmurhekar ")    //Your instagram id
//                .addItem(createCopyright())
//                .create();
//        setContentView(aboutPage);
//    }
//    private Element createCopyright()
//    {
//        Element copyright = new Element();
//        @SuppressLint("DefaultLocale") final String copyrightString = String.format("Copyright %d by Your Name", Calendar.getInstance().get(Calendar.YEAR));
//        copyright.setTitle(copyrightString);
//        // copyright.setIcon(R.mipmap.ic_launcher);
//        copyright.setGravity(Gravity.CENTER);
//        copyright.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(aboutus.this,copyrightString,Toast.LENGTH_SHORT).show();
//            }
//        });
//        return copyright;
//    }
//}