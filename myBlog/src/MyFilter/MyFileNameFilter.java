package MyFilter;

import java.io.File;
import java.io.FilenameFilter;

public class MyFileNameFilter implements FilenameFilter {
	private String title;
	
     public MyFileNameFilter(String title) {
		this.title = title;
	}
	@Override
	public boolean accept(File dir, String name) {
		if(name.matches(title)) {
			return true;
		}else {
		return false;
		}
	}

}
