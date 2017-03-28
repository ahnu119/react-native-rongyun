package com.rongyun;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
/**
 * Created by yanghaibo on 2017/3/23.
 */

public class CustomerFragment extends FragmentActivity implements OnClickListener {

    private TextView mTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.conversation_activity);

        backButton = (Button) findViewById(R.id.backButton);
        mTextView = (TextView) findViewById(R.id.title);
        mTextView.setText(getIntent().getData().getQueryParameter("title"));

        backButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backButton) {
            this.finish();
        }
    }
}