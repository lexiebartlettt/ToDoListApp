package com.ualberta.lexie;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewArchive extends ArchiveScreen{
		private ListView mainListView ;  
		private ArrayAdapter<ToDo> listAdapter;
		
		public void onCreate(Bundle savedInstanceState){
			 super.onCreate(savedInstanceState);
			 setContentView(R.layout.view_archive);
			 
			 archive_Screen();
		}
		
		 public void archive_Screen(){
			    mainListView = (ListView) findViewById( R.id.archiveListView );  
				
			    listAdapter = new ArrayAdapter<ToDo>(this,android.R.layout.simple_list_item_checked, com.ualberta.lexie.ArchiveScreen.archived);  
			    mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			   
			    mainListView.setAdapter( listAdapter ); 
		 }
}
