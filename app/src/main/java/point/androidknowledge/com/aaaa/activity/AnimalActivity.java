package point.androidknowledge.com.aaaa.activity;

import android.os.Bundle;
import android.view.View;

import point.androidknowledge.com.aaaa.BaseActivity;
import point.androidknowledge.com.aaaa.R;

public class AnimalActivity extends BaseActivity {
    private TransitionsImageView img_switcher;
    private boolean oo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_change_type);
        img_switcher = findViewById(R.id.img_switcher);
        img_switcher.setAnimType(TransitionsImageView.ANIM_ROTATE_IN_UP_LEFT );
        img_switcher.setImage(R.drawable.pic_second);
        img_switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oo) {
                    oo = false;
                    img_switcher.setImage(R.drawable.pic_second);//初始化时显示，必须放在工厂后面，否则会报NullPointerException
                } else {
                    oo = true;
                    img_switcher.setImage(R.drawable.pic_first);//初始化时显示，必须放在工厂后面，否则会报NullPointerException
                }

            }
        });
    }
}
