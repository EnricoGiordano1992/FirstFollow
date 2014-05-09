package it.finproductions.firstfollow;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TextView tx;
	private int numeroProduzioni;
	Button vai, calcola, reset;
	TableLayout tableLayout, leftfirstfollowtable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tableLayout = (TableLayout)findViewById(R.id.tabellaContenuti);
		leftfirstfollowtable = (TableLayout)findViewById(R.id.tabellaContenutiLast);
		tx = (TextView)findViewById(R.id.txt_numero_produzioni);
		vai = (Button)findViewById(R.id.btn_vai_left_right);
		vai.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				add_left_right();
			}
		});
	}

	public void add_left_right(){
		try{
			Toast.makeText(this, "Aggiungo " + tx.getText().toString() + " Left/Right", Toast.LENGTH_SHORT).show();
			
			tableLayout.requestFocus();
			tableLayout.removeAllViews();
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			numeroProduzioni = Integer.parseInt(tx.getText().toString());
			for(int pos= 0; pos<Integer.parseInt(tx.getText().toString()); pos++){
				View newView = inflater.inflate(R.layout.left_right, null);
				tableLayout.addView(newView, pos);
			}
			calcola = (Button) findViewById(R.id.btn_calcola);
			calcola.setVisibility(View.VISIBLE);
			calcola.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					calcola.setVisibility(View.INVISIBLE);
					calcola();
				}

			});
		}catch(Exception e){
			
		}
	}
	
	@SuppressLint("NewApi")
	public void calcola() {
		String left[] = new String[numeroProduzioni];
		String right[] = new String[numeroProduzioni];
		for (int i = 0; i < tableLayout.getChildCount(); i++) {
		    View view = tableLayout.getChildAt(i);
		
		    EditText rright = (EditText) view.findViewById(R.id.right);
		    EditText lleft = (EditText) view.findViewById(R.id.left);
		    
		    right[i] = rright.getText().toString().trim();
		    left[i] = lleft.getText().toString().trim();
		    
//		    Log.d("conv", "right: " + rright.getText().toString().trim());
//		    Log.d("conv", "left: " + lleft.getText().toString().trim());
		}
//			  left[i] = tastiera.nextLine();
//		      right[i] = tastiera.nextLine(); 
//	      String left[]={"E","A","T","B","F"};
//	      String right[]={"TA","+TA/e","FB/e","*FB/e","(E)/i"};
		  ArrayList<String> lleft = new ArrayList<String>();
		  ArrayList<String> ffirst = new ArrayList<String>();
		  ArrayList<String> ffollow = new ArrayList<String>();
		  
		  int numeroRighe = 0;
		  
		  for (int i = 0; i < numeroProduzioni; i++) {
			
			  numeroRighe++;
			  
			  if(right[i].contains("/"))
				  numeroRighe++;
				  
		}
		  
	      String right1[][]=new String[numeroRighe+1][6];
	      for(int i  = 0; i < right.length ;i++)
	        {
	          right1[i]=right[i].split("/");
	        }

	      for(int i  = 0; i < right.length ;i++)
	          {
	            for(int j = 0; j < right1[i].length ;j++)
	               {
	                 System.out.print("\t"+right1[i][j]);
	                 lleft.add(right1[i][j]);
	               }
	            System.out.print("\n ");
	          }
	     String first[][]= new String[right.length][2];

	     for(int i  = 0; i < right.length ;i++)
	        {
	         int k = i;

	         for( int j  = 0; j < right1[k].length ;j++)
	            {
	              if(right1[k][j].charAt(0)< 'A' || right1[k][j].charAt(0) >'Z')
	                  {
	                   first[i][j]=""+right1[k][j].charAt(0);
	                  }
	              else
	                 {
	                  for(int h = 0; h < left.length ; h++ )
	                    {
	                     if(left[h].charAt(0)==(right1[k][j].charAt(0)))
	                       {
	                        k = h;
	                        j =-1;
	                        break;
	                      }
	                    }
	                  }
	              }
	           }

	         System.out.print("\nFirst");
	         String comp = "";
	         for(int i  = 0; i < first.length ;i++)
	          {
	           System.out.print("\n");
	           comp="";
	           for(int j = 0; j < first[i].length ;j++)
	               {
	                System.out.print(first[i][j]+" ");
	                if (j == first[i].length - 1){
	                	comp=comp+first[i][j];
	                }else{
		                comp=comp+first[i][j]+" ";
	                }
	               }
	           ffirst.add(comp);
	          }

	         String follow[][]= new String[10][20];
	         int fcount[]= new int[10];
	         follow[0][0]="$";
	         fcount[0]=1;
	         for(int i = 0; i < left.length; i++){
	        	 if(i>0)
	               {
	                fcount[i]=0;
	               }
	             System.out.print("\n");

	             for( int j  = 0; j < right.length ;j++)
	                {
	                 for( int h  = 0; h < right1[j].length ;h++)
	                    {
	                     if(right1[j][h].contains(left[i]))
	                       {
	                         int B = right1[j][h].indexOf(left[i]);
	                         String a =right1[j][h].substring(0, B);
	                         String b =right1[j][h].substring(B+1,right1[j][h].length());

	                         if(b.isEmpty())
	                           {
	                            for(int k = 0; k < fcount[j] && j!= i ;k++)                                {                                                                  follow[i][fcount[i]++]= follow[j][k];                                }                            }                         else                           {                            if((int)b.charAt(0)>='A'&& (int)b.charAt(0)<='Z')
	                             {
	                              for(int k = 0; k < left.length ;k++)
	                                 {
	                                  if(left[k].equalsIgnoreCase(b))
	                                    {
	                                     for(int m = 0; m < first[k].length ;m++)
	                                        {
	                                         if(first[k][m].equalsIgnoreCase("e"))
	                                           {

	                                           }
	                                         else
	                                           {
	                                            follow[i][fcount[i]++]=first[k][m];
	                                           }
	                                        }
	                                      break;
	                                     }

	                                  }
	                              for(int k = 0; k < fcount[j] && j!= i;k++)
	                                 {
	                                   follow[i][fcount[i]++]= follow[j][k];
	                                 }

	                            }
	                         else
	                            {
	                              follow[i][fcount[i]++]=b;
	                            }
	                        }
	                     }
	                   }
	                }
	            }

	          System.out.print("\nFOLLOW");
	          for(int i  = 0; i < left.length ;i++)
	          {
	           System.out.print("\n");
	           for(int j = 0; j < fcount[i] ;j++)
	               {
	                System.out.print("  "+follow[i][j]);
	                ffollow.add(follow[i][j]);
	               }
	           }
	     try{
	  			
	  			leftfirstfollowtable.requestFocus();
	  			leftfirstfollowtable.removeAllViews();
	  			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  			
	  			for(int pos= 0; pos<=numeroProduzioni; pos++){
	  				if(pos==0){
	  					View newView = inflater.inflate(R.layout.last_headers, null);
	  					leftfirstfollowtable.addView(newView, pos);
	  				}else{
		  				View newView = inflater.inflate(R.layout.last, null);
		  				TextView txtleft = (TextView) newView.findViewById(R.id.txtleft);
		  			    TextView txtfirst = (TextView) newView.findViewById(R.id.txtfirst);
		  			    TextView txtfollow = (TextView) newView.findViewById(R.id.txtfollow);
		  			    
		  			    try{txtleft.setText(lleft.get(pos-1).toString());}catch(Exception e){txtleft.setText("");}
		  			  try{txtfirst.setText(ffirst.get(pos-1).toString());}catch(Exception e){txtfirst.setText("");}
		  			try{txtfollow.setText(ffollow.get(pos-1).toString());}catch(Exception e){txtfollow.setText("");}
		  				leftfirstfollowtable.addView(newView, pos);
	  				}
	  			}
//	  			for (int i = 0; i < leftfirstfollowtable.getChildCount(); i++) {
//	  			    View view = leftfirstfollowtable.getChildAt(i);
//	  			
//	  			    TextView txtleft = (TextView) view.findViewById(R.id.txtleft);
//	  			    TextView txtfirst = (TextView) view.findViewById(R.id.txtfirst);
//	  			    TextView txtfollow = (TextView) view.findViewById(R.id.txtfollow);
//	  			    
//	  			    txtleft.setText(lleft.get(i).toString());
//	  			    txtfirst.setText(ffirst.get(i).toString());
//	  			    txtfollow.setText(ffollow.get(i).toString());
////	  			    right[i] = rright.getText().toString().trim();
////	  			    left[i] = lleft.getText().toString().trim();
//	  			    
////	  			    Log.d("conv", "right: " + rright.getText().toString().trim());
////	  			    Log.d("conv", "left: " + lleft.getText().toString().trim());
//	  			}
	  			reset = (Button) findViewById(R.id.btn_clear);
	  			reset.setVisibility(View.VISIBLE);
	  			reset.setOnClickListener(new View.OnClickListener() {
	  				@Override
	  				public void onClick(View v) {
	  					reset.setVisibility(View.INVISIBLE);
	  					tableLayout.removeAllViews();
	  					leftfirstfollowtable.removeAllViews();
	  				}
	  			});
	  		}catch(Exception e){
	  			
	  		}
		}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
