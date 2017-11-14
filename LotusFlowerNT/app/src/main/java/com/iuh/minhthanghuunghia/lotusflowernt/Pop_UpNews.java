package com.iuh.minhthanghuunghia.lotusflowernt;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.iuh.minhthanghuunghia.lotusflowernt.AdapterRecyclerView.AdapterNews;
import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivityPopUpNewsBinding;

public class Pop_UpNews extends Activity {
    public static final String key_popsend = "popsend";
    private ActivityPopUpNewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pop_up_news);
        binding.buttonUpnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(key_popsend, binding.editTextContent.getText().toString());
                intent.putExtra(key_popsend, bundle);
                setResult(HomeActivity.key_popNewsUp, intent);
                Pop_UpNews.this.finish();

            }
        });
    }
}
