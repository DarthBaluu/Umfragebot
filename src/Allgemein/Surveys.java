package Allgemein;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public final class Surveys implements List<Survey>, Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Surveys instance;
	
	private int lastReservedId;	
	private List<Survey> list;
	
	
	static {
		CreateInstance();
	}
	
	private static void CreateInstance() {
		instance = new Surveys();
		instance.load();
	}
	
	private Surveys() {
		super();
		lastReservedId = -1;
	}

	
	public static Surveys getInstance() {
		return instance;
	}
	
	public static int getNextId() { 
		return ++instance.lastReservedId;
	}

	private void reload() {
		CreateInstance();
	}
	
	private boolean load() {
		String file = Config.getProperty(Config.Property.DBFile);
		if (!Extensions.fileExists(file)) return false;
		
		try (
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))
		) {
			Object o = in.readObject();
			list = Extensions.castList(o, Survey.class);
			lastReservedId = list.size();
			return true;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean save() {
		String file = Config.getProperty(Config.Property.DBFile);
		if (!Extensions.fileExists(file)) return false;
		
		try (
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fout)
		) {
			out.writeObject(list);
			out.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}  
	}
	
	private boolean update() {
		// TODO: add update handling. Maybe do not save every time or let developer choose.  
		if (save()) {
			return true;
		}
		reload();
		return false;
	}

	
	@Override
	public boolean add(Survey e) {
		if (list.add(e)) {
			return update();
		}
		return false;
	}

	@Override
	public void add(int index, Survey element) {
		list.add(index, element);
		update();
	}

	@Override
	public boolean addAll(Collection<? extends Survey> c) {
		if (list.addAll(c)) {
			return update();
		}
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends Survey> c) {
		if (list.addAll(index, c)) {
			return update();
		}
		return false;
	}
	
	@Override	
	public void clear() {
		list.clear();
		update();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public Survey get(int index) {
		return list.get(index);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<Survey> iterator() {
		return list.iterator();
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<Survey> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<Survey> listIterator(int index) {
		return list.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		if (list.remove(o)) {
			return update();
		}
		return false;
	}

	@Override
	public Survey remove(int index) {
		Survey tmp = list.remove(index);
		if (update()) {
			return tmp;
		}
		return null;
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		if (list.removeAll(c)) {
			return update();
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (list.retainAll(c)) {
			return update();
		}
		return false;
	}

	@Override
	public Survey set(int index, Survey element) {
		Survey tmp = list.set(index, element);
		if (update()) {
			return tmp;
		}
		return null;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public List<Survey> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}	

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}
}
