package com.example.festaanonovo.view;

import  androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.festaanonovo.R;
import com.example.festaanonovo.constant.AnoNovoConstant;
import com.example.festaanonovo.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);
        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.check_participate) {
            if (this.mViewHolder.checkParticipate.isChecked()) {
                //Salva presença
                this.mSecurityPreferences.storeString(AnoNovoConstant.PRESENCE_KEY, AnoNovoConstant.CONFIRMATION_YES);

            } else {
                //Salva ausencia
                this.mSecurityPreferences.storeString(AnoNovoConstant.PRESENCE_KEY, AnoNovoConstant.CONFIRMATION_NO);

            }
        }
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String presence = extras.getString(AnoNovoConstant.PRESENCE_KEY);
            if (presence != null && presence.equals(AnoNovoConstant.CONFIRMATION_YES)) {
                this.mViewHolder.checkParticipate.setChecked(true);
            } else {
                this.mViewHolder.checkParticipate.setChecked(false);
            }
        }
    }

    private static class ViewHolder {
        CheckBox checkParticipate;
    }
}
