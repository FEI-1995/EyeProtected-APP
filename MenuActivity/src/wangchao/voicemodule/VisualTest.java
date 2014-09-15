package wangchao.voicemodule;

import java.util.ArrayList;
import java.util.HashMap;

import com.special.ResideMenuDemo.R;

import wangchao.service.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/*
 * ������ʱ�ã��������ο��ã���ʵ������
 */
public class VisualTest extends Activity {

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		finish();
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		VisualTest.this.finish();
	}
	private Button uptest;
	private Button downtest;
	private Button righttest;
	private Button lefttest;
	private ImageView imageView = null;
	private TextView testtitle;
	
//	private Button speak;
	
	int imageNum = 0;
	
	
	int direct = 2;//��ʼĬ�Ϸ���Ϊright
	int chart_row = 1; //��ʼ����������
	int chart_counter = 0;//��ͬһ���ж���ȷ����
	int chart_counterf = 0; //��ͬһ���жϴ������
	int times = 0;//���Դ�����0Ϊ���ۣ�1Ϊ���ۣ��˺����
	int result[] =new int[2]; //������������Ӧ������
	
	VisualChartMethods vcm = new VisualChartMethods();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualtest);
		
		Intent intent_go=getIntent();
		
		testtitle = (TextView) findViewById(R.id.testtitle);
		testtitle.setText("���۲��ԣ�");
		
		uptest = (Button) findViewById(R.id.uptest);
		downtest = (Button) findViewById(R.id.downtest);
		righttest = (Button) findViewById(R.id.righttest);
		lefttest = (Button) findViewById(R.id.lefttest);
//		speak = (Button) findViewById(R.id.speaktest);
		
	     imageView = (ImageView)findViewById(R.id.image);
	     imageView.setImageDrawable(getResources().getDrawable(R.drawable.e_right));     
   
	    uptest.setOnClickListener(new JudgeListener());
	    downtest.setOnClickListener(new JudgeListener());
	    righttest.setOnClickListener(new JudgeListener());
	    lefttest.setOnClickListener(new JudgeListener());
	    
//	    speak.setOnClickListener(new VoiceListener());    
	
	
	}
	
//	/*
//	 * speak��ť
//	 */
//	class VoiceListener implements OnClickListener
//	{
//
//		@Override
//		public void onClick(View arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//	}
	
	/*
	 * �жϲ��԰����Ƿ���ͼ��һ�£��������жϽ��
	 */
	class JudgeListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			int judgeok = 0;//����������ж�ֵ
			//imageView.setImageDrawable(getResources().getDrawable(vcm.setImageE(direct)));
//			System.out.println("imageView.getId():"+imageView.getId());
//        	System.out.println("v.getId():"+v.getId());
//        	System.out.println("up:"+R.id.uptest);
        
        
			//���η����ж�
			if(v.getId() == R.id.uptest)
			{
				if(direct == 0)
					{
					judgeok = 1;
					chart_counter += 1;
					}
				else
				{
					judgeok = 0;
					chart_counterf +=1;
				}
			}else if(v.getId() == R.id.downtest)
			{
				if(direct == 1)
				{
					judgeok = 1;
					chart_counter += 1;
				}
			    else
			    {
			    	judgeok = 0;
			    	chart_counterf +=1;
			    }
			}else if(v.getId() == R.id.righttest)
			{
				if(direct == 2)
				{
					judgeok = 1;
					chart_counter += 1;
				}
			    else
			    {
			    	judgeok = 0;
			    	chart_counterf +=1;
			    }
			}else if(v.getId() == R.id.lefttest)
			{
				if(direct == 3)
				{
					judgeok = 1;
					chart_counter += 1;
				}
			    else
			    {
			    	judgeok = 0;
			    	chart_counterf +=1;
			    }
			}
			
			//�ܷ�ʽ�ļ���
			if(chart_row >14)
			{
				if(times == 0)
				{
			        result[times] = chart_row;
			        times += 1;
			        testtitle.setText("���۲��ԣ�");
				}else if(times == 1)
				{
					result[times] = chart_row;
					times = 0;
					System.out.println("result[0]:"+result[0]);
					System.out.println("result[1]:"+result[1]);					
					Intent result_show = new Intent();
					result_show.putExtra("leftresult",vcm.chart(result[0]));
					result_show.putExtra("rightresult",vcm.chart(result[1]));
					result_show.setClass(VisualTest.this,ResultActivity.class);
					VisualTest.this.startActivity(result_show);
				}
			}else if(judgeok == 0)
			{
				chart_counter = 0;
				if(chart_counterf == 2)
				{
					chart_counterf = 0;
					chart_counter = 0;
					System.out.println("result:  "+chart_row);
					if(times == 0)
					{
				        result[times] = chart_row;
				        times += 1;
					}else if(times == 1)
					{
						result[times] = chart_row;
						times = 0;
						System.out.println("result[0]:"+result[0]);
						System.out.println("result[1]:"+result[1]);
						Intent result_show = new Intent();
						result_show.putExtra("leftresult",vcm.chart(result[0]));
						result_show.putExtra("rightresult",vcm.chart(result[1]));
						result_show.setClass(VisualTest.this,ResultActivity.class);
						VisualTest.this.startActivity(result_show);
					}
					
					chart_row = 1;
				}
			}else if(judgeok == 1)
			{
				if(chart_row == 1)
				{
					chart_row += 1;
					chart_counter = 0;
					chart_counterf = 0;
				}else if(chart_row != 1)
				{
					if(chart_counter == 2)
						{
						chart_row += 1;
						chart_counterf = 0;
					    chart_counter = 0;
					    }
				}
			}
			
			
			System.out.println("judgeok        = "+ judgeok);		
			System.out.println("chart_counter  = "+ chart_counter);
			System.out.println("chart_counterf = "+ chart_counterf);
			System.out.println("chart_row      = "+ chart_row);
			direct = vcm.direct();
			
			
			//�л�ͼƬ
			imageView.setImageDrawable(getResources().getDrawable(vcm.setImageE(direct)));
		}}
}

