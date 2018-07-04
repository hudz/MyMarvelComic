package my.edu.inti.asg202.mymarvelcomic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button comicBtn;
    public static TextView comicDataTextView;
    public static ProgressBar progressBarCyclic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comicBtn = (Button) findViewById(R.id.comicBtn);
        comicDataTextView = (TextView) findViewById(R.id.comicDataTextView);
        //progressBarCyclic = (ProgressBar) findViewById(R.id.progressBar_cyclic);


        //comicBtn.setOnClickListener(new View.OnClickListener() {
            //  public void onClick(View view) {
                fetchDataComic process = new fetchDataComic();
                process.execute();

            //}
        //});
    }


}
