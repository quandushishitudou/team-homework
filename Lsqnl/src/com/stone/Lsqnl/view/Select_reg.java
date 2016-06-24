package com.stone.Lsqnl.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.stone.Lsqnl.R;

public class Select_reg extends Activity{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectreg);
		
		RadioGroup RG=(RadioGroup)findViewById(R.id.RG);
		RadioButton RB1=(RadioButton)findViewById(R.id.rB1);
		RadioButton RB2=(RadioButton)findViewById(R.id.rB2);
		
		//RG.setOnCheckedChangeListener(ChangeRadioGroup);
		
	        
	        //根据ID找到该文本控件
	        
	        //绑定一个匿名监听器
	      RG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	            
	            @Override
	            public void onCheckedChanged(RadioGroup group, int checked) {
	                // TODO Auto-generated method stub
	                //获取变更后的选中项的ID
	            	int id= group.getCheckedRadioButtonId();
                    switch (group.getCheckedRadioButtonId()) {
	                case R.id.rB1:
	                	Intent intent = new Intent();
						intent.setClass(Select_reg.this, RegisterActivity.class);
						startActivity(intent);
	                    break;
	                case R.id.rB2:
	                	Intent intent1 = new Intent();
						intent1.setClass(Select_reg.this, Stu_reg.class);
						startActivity(intent1);
	                    break;
	                default:
	                    
	                    break;
	                }

	                	                //根据ID获取RadioButton的实例
	                //RadioButton rb = (RadioButton)MyActiviy.this.findViewById(radioButtonId);
	                //更新文本内容，以符合选中项
	                //tv.setText("您的性别是：" + rb.getText());
	            }
	        });
		}
	/*private RadioGroup.OnCheckedChangeListener ChangeRadioGroup=new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if(checkedId=RB1.getId()&&RB1.isChecked())
			{
				
			}
			
		}
	};*/
	
}