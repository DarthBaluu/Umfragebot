package Allgemein;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Survey implements Iterable<Question>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Question> questions;
	private String name;
	private int id;
	private String description;
	
	
	private Survey(String name, String description, List<Question> questions) {
		super();
		
		this.name = name;
		this.description = description;
		this.questions = questions;
		this.id = -1; // should get real id on save
	}

	
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public List<Question> getQuestionsReadOnly() {
		return Collections.unmodifiableList(questions);
	}
	
	
	public static Survey create() {
		String name = "";
		String desc = null;
		
		// TODO: Ask user for name and description. Name should not be null or empty at least.
		// ...
		
		
		List<Question> questions = new LinkedList<Question>();
		
		// TODO: Ask user for questions. There should be at least one question.
		// ...
		
		Survey survey = new Survey(name, desc, questions);		
		survey.save();
		return survey;
	}
	
	private int save() {
		this.id = Surveys.getNextId();
		Surveys.getInstance().add(this);
		return this.getId();
	}


	@Override
	public Iterator<Question> iterator() {
		return questions.iterator();
	}

	public boolean contains(Object o) {
		return questions.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return questions.containsAll(c);
	}

	public Question get(int index) {
		return questions.get(index);
	}

	public int indexOf(Object o) {
		return questions.indexOf(o);
	}

	public int size() {
		return questions.size();
	}	
}
