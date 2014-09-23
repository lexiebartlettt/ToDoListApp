package com.ualberta.lexie;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class EmailScreen1 extends MainActivity {
	 private ListView mainListView ;  
	 private ArrayAdapter<ToDo> listAdapter; 	
	 private ArrayList<ToDo> emailStrings = new ArrayList <ToDo>();
	 
	 
	 @Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email_screen);
		
		//Toast.makeText(EmailScreen1.this,"hello",Toast.LENGTH_SHORT).show();

		
		
		mainListView = (ListView) findViewById( R.id.emailListView);
		 mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
    	public void onItemClick(AdapterView<?> listAdapter,View v, int position, long id)
    	{
    		emailStrings.add(MainActivity.toDoList.get(position));
    		
    }
    		
    });
	   Button email_select_button=(Button) findViewById(R.id.emailSelected);
	    
	    email_select_button.setOnClickListener(new View.OnClickListener() {
	    	@Override
	    	public void onClick(View v){
	        	 emailToDos(emailStrings);
	         }
    });
   Button email_all_button=(Button) findViewById(R.id.emailAll);
	    
	    email_all_button.setOnClickListener(new View.OnClickListener() {
	    	@Override
	    	public void onClick(View v){
	        	 emailToDos(com.ualberta.lexie.MainActivity.toDoList);
	         }
    });
		
	}
	 
public void emailToDos(ArrayList<ToDo> emailText){
	String[] to ={"lexie@ualberta.ca"};
	String[] from={"lexie@ualberta.ca"};
	
	Intent emailIntent = new Intent(Intent.ACTION_SEND);
	emailIntent.setData(Uri.parse("mailto:"));
	emailIntent.setType("text/plain");
	
	emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
	emailIntent.putExtra(Intent.EXTRA_CC, from);
	emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
	
	emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
	
	try{
		startActivity(Intent.createChooser(emailIntent, "send mail..."));
		finish();
		Log.i("Finished sending email...","");
	}
	catch(android.content.ActivityNotFoundException ex){
		Toast.makeText(EmailScreen1.this, "There is no email client installed", Toast.LENGTH_SHORT).show();
	}
}
	
	
public void emailScreen(){
    mainListView = (ListView) findViewById( R.id.emailListView );  
	
    listAdapter = new ArrayAdapter<ToDo>(this,android.R.layout.simple_list_item_checked, MainActivity.toDoList);  
    mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
   
    mainListView.setAdapter( listAdapter ); 
    
}
@Override 
public void onStart(){
	super.onStart();
	//put load here
	  mainListView = (ListView) findViewById( R.id.emailListView );  
		
	    listAdapter = new ArrayAdapter<ToDo>(this,android.R.layout.simple_list_item_checked, com.ualberta.lexie.MainActivity.toDoList);  
	    mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	   
	    mainListView.setAdapter( listAdapter ); 
}
}
