package com.stone.shop.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

import com.stone.shop.R;
import com.stone.shop.model.Teacher;

public class FindResultActivity extends Activity {
	String mes;
	String a;
	private ListView listView;

	private ArrayList<HashMap<String, Object>> data;
	private ArrayList<HashMap<String, Object>> data1;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findresult);  
		
	    listView = (ListView) this.findViewById(R.id.mylistview);
	   
	    Intent myintent =getIntent();
	    mes=myintent.getStringExtra("result");	
	    listView.setAdapter(null);
		BmobQuery<Teacher> query = new BmobQuery<Teacher>();
		query.addWhereEqualTo("dnum", mes);
		
		data = new ArrayList<HashMap<String, Object>>();
		data1= new ArrayList<HashMap<String, Object>>();
		
		final SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item, 
		  new String[] { "name", "phone"
		  }, new int[] { R.id.tname,
					R.id.tphone
					});
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new ItemClickListener());
		query.setLimit(20);
		query.findObjects(this, new FindListener<Teacher>() {
			   @Override
		          public void onSuccess(List<Teacher> arg0) {
		              // TODO Auto-generated method stub
				   //toast("查询成功：共"+arg0.size()+"条数据。");
		              for (Teacher d: arg0) {
		            	  HashMap<String, Object> item = new HashMap<String, Object>();
		            	  HashMap<String, Object> item1 = new HashMap<String, Object>();
		            	  item.put("name",d.getRname());
		            	  //toast("姓名"+d.getRname());
		            	  item.put("phone",d.getPhone());
//		            	  try {
//							JSONObject jsonObject=new JSONObject(d.getMessage()); 
							item1.put("message",d.getMessage());
//						} catch (JSONException e) {
//							// TODO Auto-generated catch block
////							e.printStackTrace();
//						}
//		            	 
		            	 // item1.get(d.getObjectId());
		            	  //a=d.getMessage();
		            	  data.add(item);
		            	  data1.add(item1);
			              }
		              
		              adapter.notifyDataSetChanged();
		          }         
		          @Override
		          public void onError(int arg0, String msg) {
		              // TODO Auto-generated method stub
		              toast("查询失败："+msg);
		          }
				
		  });
		  
		
			
		
	  }
	private final class ItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
//			toast(data1.get(position).values().toString());
//			if(data1.get(position).values().toString().isEmpty())
//				{a="无留言!";}
//			else {
				a=data1.get(position).values().toString();
				//}
			new AlertDialog.Builder(FindResultActivity.this) 
			.setTitle("留言：")
			.setMessage(a)
			//.setMessage("jojkl")
			.setNegativeButton("确认", null)
			.show();
		}
	}
	protected void toast(String toast) {
		// TODO Auto-generated method stub
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
		
	
	}
	