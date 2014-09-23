package com.ualberta.lexie;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ArchiveScreen extends MainActivity {
	 private ListView mainListView ;  
	 private ArrayAdapter<ToDo> listAdapter;
	 public static ArrayList<ToDo> archived = new ArrayList<ToDo>();
	 private ArrayList<ToDo> preArchived = new ArrayList<ToDo>();
	 private ArrayList<Integer> positions = new ArrayList<Integer>();
	 
	 @Override
		public void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		setContentView(R.layout.archive_screen);
		
		mainListView = (ListView) findViewById( R.id.archiveListView);
		 mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
   	public void onItemClick(AdapterView<?> listAdapter,View v, int position, long id)
   	{
   		preArchived.add(com.ualberta.lexie.MainActivity.toDoList.get(position));
   		
   }
   		
   });
	   Button archive_select_button=(Button) findViewById(R.id.ArchiveSelected);
	    
	    archive_select_button.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){
	        	 archiveSelect();
	         }
   });
  Button archive_all_button=(Button) findViewById(R.id.ArchiveAll);
	    
	   archive_all_button.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){
	        	archiveAll();
	         }
   });
	   Button view_archive_button=(Button) findViewById(R.id.viewArchived);
	    
	   view_archive_button.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v){
	    		Intent viewIntent= new Intent(getApplicationContext(),ViewArchive.class);
	        	startActivity(viewIntent);
	         }
   });
	 }
	 public void archiveSelect(){
		 for(int i=0; i<preArchived.size();i++){
			 archived.add(preArchived.get(i));
			 //Toast.makeText(ArchiveScreen.this,archived.get(i),Toast.LENGTH_SHORT).show();
		 }
		 for(int j=0;j<positions.size();j++){
			 MainActivity.toDoList.remove(positions.get(j));
			 listAdapter.notifyDataSetChanged();
		 }
	 }
	 
	 public void archiveAll(){
		 for(int i=0; i<com.ualberta.lexie.MainActivity.toDoList.size();i++){
			 ToDo t=MainActivity.toDoList.get(i);
			 String s= t.getString();
			 archived.add(new ToDo(s));
		 }
	 }
	 public void archiveScreen(){
		    mainListView = (ListView) findViewById( R.id.archiveListView );  
			
		    listAdapter = new ArrayAdapter<ToDo>(this,android.R.layout.simple_list_item_checked, com.ualberta.lexie.MainActivity.toDoList);  
		    mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		   
		    mainListView.setAdapter( listAdapter ); 
	 }


@Override 
public void onStart(){
	super.onStart();
	//put load here
	  mainListView = (ListView) findViewById( R.id.archiveListView );  
		
	    listAdapter = new ArrayAdapter<ToDo>(this,android.R.layout.simple_list_item_checked, com.ualberta.lexie.MainActivity.toDoList);  
	    mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	   
	    mainListView.setAdapter( listAdapter ); 
}
}
