package com.ualberta.lexie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	  private ListView mainListView ;  
	  private ArrayAdapter<ToDo> listAdapter;  
	  public static ArrayList<ToDo> toDoList;  
	  private Button add_button;
	  private static final String FILENAME="saves6.sav";
	  private static final String FILENAME2 = "save13.sav";
	  int max = 0;
	  private ArrayList<Integer> check=new ArrayList<Integer>();
	  private int []savedints=new int[100];
	  
	  /** Called when the activity is first created. */  
	  @Override  
	  public void onCreate(Bundle savedInstanceState) { 
	    super.onCreate(savedInstanceState);  
	    setContentView(R.layout.fragment_main);  
	    toDoList= new ArrayList<ToDo>();
	    
	    //loadFromFile();
	    //loadCheckFromFile();
	    
	    

	    add_button=(Button) findViewById(R.id.addBtn);
	    
	    add_button.setOnClickListener(new View.OnClickListener() {
	    	@Override
	    	public void onClick(View v){
	        	 addClick();
	         }
    });
	    mainListView = (ListView) findViewById( R.id.listView);
	    mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
	    
	    	public void onItemClick(AdapterView<?> listAdapter,View v, int position, long id)
	    	{
	    	if(toDoList.get(position).getCheck()==true){
	    		toDoList.get(position).Check(false);
	    	}
	    	else {
	    		toDoList.get(position).Check(true);
	    	}
	    	
	    }
	    		
	    });

	    }
	 
@Override 
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
	super.onCreateContextMenu(menu, v,  menuInfo);
	AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
	String title = "Delete?";
	menu.setHeaderTitle(title);
	
	menu.add(Menu.NONE,R.menu.delete_menu, Menu.NONE, "Delete");
}

@Override
public boolean onContextItemSelected(MenuItem item){
	switch(item.getItemId()){
	case R.menu.delete_menu:
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		toDoList.remove(info.position);
		listAdapter.notifyDataSetChanged();
		return true;
		
	default:
		return super.onContextItemSelected(item);
	}
}
//the following two functions was taken from http://developer.android.com/guide/topics/ui/menus.html
//September 18 2014 
//changes from the original have been made 	  
@Override
public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.options_menu, menu);
	return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
        case R.id.emailItm:
        	Intent emailIntent= new Intent(getApplicationContext(),EmailScreen1.class);
        	startActivity(emailIntent);
            return true;
        case R.id.archiveItm:
        	Intent archiveIntent= new Intent(getApplicationContext(),ArchiveScreen.class);
        	startActivity(archiveIntent);
        	return true;	
        
        default:
            return super.onOptionsItemSelected(item);
    }
}

 void addClick(){
    	EditText editText=(EditText)findViewById(R.id.newToDo);
		String toDo=editText.getText().toString();
		toDoList.add(new ToDo(toDo));
		listAdapter.notifyDataSetChanged();
		editText.setText("");
		setResult(RESULT_OK);
		//saveInFile(toDo);
	}
 
 /*
	private String[] loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			while (line != null) {
				int already=1;
				for(int g=0;g<toDoList.size();g++){
					if (toDoList.get(g)==line){
						already=0;
					}
				}
				if(already==1){
				toDoList.add(line);}
	    		//Toast.makeText(MainActivity.this,line,Toast.LENGTH_SHORT).show();

				line = in.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
		FileOutputStream fos = new FileOutputStream(FILENAME,false);
		FileWriter fwriter;
		String s = "";
		try{
			fwriter = new FileWriter(fos.getFD());
			
			fwriter.write(s);
			fwriter.flush();
			fwriter.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			fos.getFD().sync();
			fos.close();
		}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return toDoList.toArray(new String[toDoList.size()]);
	}
	
	private void loadCheckFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME2);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			int already=1;
			//line.replaceAll("\\s+", "");
			int i =0;
			while(line!= null){
				
				int p=Integer.parseInt(line);
				savedints[i]= p;
				i++;
					line = in.readLine();
				}
			
			File file=new File(FILENAME2);
			file.delete();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void checkChecked(){
		
		//int []check = new int [100];
		check.add(savedints[0]);
		int m=0;
		for(int i=1;i<savedints.length;i++){
			if(savedints[i]!=savedints[i-1]){
				check.add(savedints[i]);
				mainListView.setItemChecked(savedints[i], true);
				++m;
			}
		}

		
		
	}
	private void saveInFile(String text) {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			fos.write(new String(text+"\n")
					.getBytes());
			fos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void saveInFile2(int x) {
		try {
			String y=String.valueOf(x); 
			FileOutputStream fos = openFileOutput(FILENAME2,
					Context.MODE_APPEND);
			
			fos.write(new String(y+"\n").getBytes());
			fos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	@Override 
	public void onStart(){
		super.onStart();
		//put load here
		listAdapter= new ArrayAdapter<ToDo>(this,android.R.layout.simple_list_item_multiple_choice, toDoList);
		mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		   // checkChecked();
		mainListView.setAdapter( listAdapter ); 
		registerForContextMenu(mainListView);
	}
	public void refreshScreen(){
	    mainListView = (ListView) findViewById( R.id.listView );  
		
	    listAdapter = new ArrayAdapter<ToDo>(this,android.R.layout.simple_list_item_multiple_choice, toDoList);  
	    mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	   // checkChecked();
	    mainListView.setAdapter( listAdapter ); 
	    registerForContextMenu(mainListView);
		
	}
}
	
