package com.fiuba.taller.materials.responses;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class PollQuestionElementBundler {

	private List<QuestionWithOptions> questionsWithOptions = new LinkedList<QuestionWithOptions>();
	private List<QuestionWithoutOptions> questionsWithoutOptions = new LinkedList<QuestionWithoutOptions>();
	
	@XmlElement(name="preguntasConOpciones")
	public List<QuestionWithOptions> getQuestionsWithOptions() {
		return questionsWithOptions;
	}
	public void setQuestionsWithOptions( List<QuestionWithOptions> questionsWithOptions) {
		this.questionsWithOptions = questionsWithOptions;
	}
	
	public void addQuestionsWithOptions( QuestionWithOptions questionsWithOptions) {
		this.questionsWithOptions.add(questionsWithOptions);
	}
	
	
	@XmlElement(name="preguntasSinOpciones")
	public List<QuestionWithoutOptions> getQuestionsWithoutOptions() {
		return questionsWithoutOptions;
	}
	public void setQuestionsWithoutOptions( List<QuestionWithoutOptions> questionsWithoutOptions) {
		this.questionsWithoutOptions = questionsWithoutOptions;
	}
	
	public void addQuestionsWithoutOptions( QuestionWithoutOptions questionsWithoutOptions) {
		this.questionsWithoutOptions.add(questionsWithoutOptions);
	}
	
	@Override
	public String toString(){
		String sizes = "Size with option: " + 
						questionsWithOptions.size() + 
						" Size without options: " +
						questionsWithoutOptions.size();
	
		return sizes;
	}
	
}
