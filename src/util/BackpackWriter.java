package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.ListView;
import entities.Things;
import javafx.collections.ObservableList;

public class BackpackWriter {
	
	public BackpackWriter(List<Things> things, ObservableList<String> list){
		this.things = things;
		this.list = list;
	}
	
	public BackpackWriter() {}

	private String dir = "mybackpack";
	private ObservableList<String> list;
	
	public BackpackWriter setList(ObservableList<String> list) {
		this.list = list;
		return this;
	}

	public BackpackWriter setThings(List<Things> things) {
		this.things = things;
		return this;
	}

	private List<Things> things;
	
	public void ensureExists(){
		File dir = new File("mybackpack");
		if(!dir.exists())
			dir.mkdirs();
	}
	
	public void save(String fname){
		if (fname == null || fname.isEmpty())
			return;
		String f  = dir + "/" + fname;
		try(FileWriter fileOut = new FileWriter(f)){
			for(int i= 0; i < things.size(); i++){
				if(!things.get(i).getThingName().isEmpty()){
					fileOut.write(things.get(i).getThingName()+" : ");
					fileOut.write(things.get(i).getQuantity()+"\r\n");
				}
			}
		}catch(IOException ex){
			System.out.println("File write error.");
		} finally {
			search();
		}
	}
	
	public void read(String name){
		String f = dir + "/" + name;
		try(BufferedReader bf = new BufferedReader(new FileReader(f))){
			things.clear();
			String s;
			while((s = bf.readLine())!=null){
				if(!s.isEmpty())
					if (s.contains(":"))
							things.add(new Things(s.split(":")[0], s.split(":")[1]));
					else things.add(new Things(s.split(" ")[0], s.split(" ")[1]));
			}
		}catch(IOException e){
		}
	}
	
	public void search(){
		search("");
	}
	public void search(String selected){
		ensureExists();
		if (list != null)
			list.clear();
		File fsearch = new File(dir + "/" + selected);
		for(File item: fsearch.listFiles()){
			if(!item.isDirectory())
				list.addAll(item.getName());
		}	
	}
	
	public void delete(String selected){
		new File(dir + "/" + selected).delete();
		search();
	}
}
