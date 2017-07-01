package Allgemein;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public final class Extensions {

	public static <T> List<T> castList(Object obj, Class<T> clazz)
	{
		List<T> result = new LinkedList<T>();
	    if(obj instanceof List<?>)
	    {
	        for (Object o : (List<?>) obj)
	        {
	            result.add(clazz.cast(o));
	        }
	        return result;
	    }
	    return null;
	}

	public static boolean fileExists(String file) {
		File f = new File(file);
		return f.exists() && !f.isDirectory();
	}
	
}
