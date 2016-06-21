package com.stone.shop.view;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;

import com.stone.shop.R;
import com.stone.shop.model.Teacher;
import com.stone.shop.model.User;

/**
 * 应用主界面
 * @date  2014-4-24
 * @author Stone
 */
@SuppressWarnings("deprecation")
public class BaseActivity extends TabActivity {
	
	@SuppressWarnings("unused")
	private static final String TAG = "BaseActivity";
	
	private TabHost tabHost;
	private LayoutInflater layoutInflater;
	
	
	String[] mTitle = new String[] { "留言", "发现", "我的"};  
    int[] mIcon = new int[] { R.drawable.ic_shop, R.drawable.ic_sale,  
            R.drawable.ic_mine };   

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base); 
		
        initTabView();
        
        
	}
	
	public View getTabItemView(int i) {  
        // TODO Auto-generated method stub  
       View view = layoutInflater.inflate(R.layout.tab_widget_item, null);  
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);  
        imageView.setImageResource(mIcon[i]);  
        TextView textView = (TextView) view.findViewById(R.id.textview);  
        textView.setText(mTitle[i]);  
        return view;  
    } 
	
	public void initTabView() {
			
        tabHost = getTabHost();  
        layoutInflater = LayoutInflater.from(this);
        TabHost.TabSpec spec;  
        
        //小菜
		Intent intent1 = new Intent(this, ShopActivity.class);  
        spec = tabHost.newTabSpec(mTitle[0]).setIndicator( getTabItemView(0) ).setContent(intent1);  
        tabHost.addTab(spec);  
        
        //发现
       Intent intent2 = new Intent(this, FinderActivity.class);  
        spec = tabHost.newTabSpec(mTitle[1]).setIndicator( getTabItemView(1) ).setContent(intent2);  
        tabHost.addTab(spec);
        
        //我的
        Intent intent3 = new Intent(this, MineActivity.class);  
        spec = tabHost.newTabSpec(mTitle[2]).setIndicator( getTabItemView(2) ).setContent(intent3);  
        tabHost.addTab(spec); 
   
        
        tabHost.setCurrentTab(0);
	}
	
	@Override
	public void onBackPressed() {
		Toast.makeText(this, "确定要退出吗?", Toast.LENGTH_LONG).show();
		//super.onBackPressed();
	}
	

}
