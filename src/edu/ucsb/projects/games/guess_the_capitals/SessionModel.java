package edu.ucsb.cs56.projects.games.guess_the_capitals;

import java.util.Observable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Model that keeps track of the current session.
 *
 * @author Sean Shelton, Armin Mahini
 * @version UCSB CS56, F16
 */
public class SessionModel extends Observable{
    
    /**
     * Represents number of questions in the current session
     */
    private int numQuestions;

	/**
	 * Current question number
	 */
	private int questionNum;
    
    /**
     * Represents number of correct answers in the current session
     */
    private int numCorrect = 0;
    
    /**
     * Represents final grade of the current session
     */
    private double grade = 0;

	/**
	 * The number of choices available to pick from in the multiple choice game.
	 */ 
	private int numChoices;
    
    /**
     * Represents answers for each question in the current session
     */
    private ArrayList<Territory> possibleAnswers;

	/**
	 *	Current answer for the question
	 */
	private Territory answerTerritory;

	/** 
	 * Singleton GameData class used for generating questions/answers
	 */
	GameData gameData = GameData.getInstance();

    /**
     * Constructor for a session of the game, by default sets 
     * number of questions to 10. Makes an empty ArrayList and
     * clears it just to be safe
     */
    SessionModel() {
	numQuestions = 10;
      	numChoices = 4;
        possibleAnswers = new ArrayList<Territory>();
	possibleAnswers.clear();
		//updateCurrentQuestion();
	//questionTerritories
        //territoryOfQuestion = new Territory();
    }
    
    /** Constructor for a session of the game. Like the one above, sets number of questions to 10,
     *  creates an  empty ArrayList and clears it. 
     *  @param gameData gameData allows this SessionModel to access a particular list of capitals 
     *  and terroritories depending on which selections the user has made.
     */
	SessionModel(GameData gameData) {
		this.gameData = gameData;
		numQuestions = 10;
		numChoices = 4;
        possibleAnswers = new ArrayList<Territory>();
		possibleAnswers.clear();
		//updateCurrentQuestion();
	}

    /**
     * Returns true if the capital guessed by the player is correct,
     * otherwhise returns false
     * @param guess represents the user's guess
     * @return true if guess is correct, else return false
     */
    public boolean checkAnswer(int guess){
		return possibleAnswers.get(guess).getName() == answerTerritory.getName();
		//return guess.getName() == answerTerritory.getCapital().getName();
		//return possibleAnswers.get(questionNumber-1) == guess;
    }

    /**
     * Sets the number of questions to the user's input
     * @param numQuestions represents the user's desired number of questions
     */
    public void setNumQuestions(int numQuestions){
		this.numQuestions = numQuestions;
		setChanged();
		notifyObservers();
    }
    
    /**
     * @return number of questions in the current session
     */
    public int getNumQuestions(){
		return numQuestions;
    }

    /** Sets the number of questions to 10, 50, or a custom number selected by the user
     * @param questionNum is number of questions indicated by the user
     */
	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
		setChanged();
		notifyObservers();
	}

    /** 
     * @return the current question number
     */
	public int getQuestionNum() {
		return this.questionNum;
	}
    
    /**
     * Sets the number of correct answers in the current session
     * @param numCorrect represents the number of correct answers 
     */
    public void setNumCorrect(int numCorrect){
		this.numCorrect = numCorrect;
		setChanged();
		notifyObservers();
    }

    /**
     * @return  number of correct answers in the current session
     */
    public int getNumCorrect(){
		return numCorrect;
    }

    /**
     * Sets the user's grade of their performance in the current session
     * @param grade represents the user's grade based on the number of correct responses 
     */
    public void setGrade(double grade){
		this.grade = grade;
		setChanged();
		notifyObservers();
    }

    /**
     * @return the user's grade
     */
    public double getGrade(){
		return grade;
    }

	/**
	 *	Changes the number of choices to play the game with.
	 *	(e.g. having to guess between 6 choices instead of the usual 4)
	 * @param numChoices represents number of options the user can pick from
	 */
	public void setNumChoices(int numChoices) {
		this.numChoices = numChoices;
		setChanged();
		notifyObservers();
	}

	/**
	 *  @return number of choices for this session of the game
	 */
	public int getNumChoices() {
		return this.numChoices;
	}

    /** 
	 * @return the possible answers for the question
	 */
	public ArrayList<Territory> getPossibleAnswers() {
		return possibleAnswers;
	}

    /** 
     * @return the Territory for which the Capital belongs to
     */
	public Territory getAnswerTerritory() {
		return answerTerritory;
	}

	/**
	 * Changes the possible answers to a random set of capitals
	 */
	public void updateCurrentQuestion(){
		// clear current set of answers
		possibleAnswers.clear();

		// Creating r to be our random number generator
		Random r = new Random();

		// Making an ArrayList of locations to make the question	
		// TODO: remove locations from GameData and move the logic of setting the current
		// set of locations to SessionModel
		ArrayList<Territory> totalData = gameData.getLocations();
		Territory t = new Territory();

		for (int i = 0; i < numChoices; ++i) {
			// Set t equal to some random territory
			t = totalData.get(r.nextInt(totalData.size()));
			possibleAnswers.add(t);
		}

		// Choose a possible answer to actually be the answer
		answerTerritory = possibleAnswers.get(r.nextInt(numChoices));

		setChanged();
		notifyObservers();
		//gameData.getLocations.get(r.nextInt(
		
	}
    /** Sets the number of questions, correct answers and grade to their default users to get the game ready
     * for the next session to be played
     */
	public void reset() {
		this.numQuestions = 10;
		this.numCorrect = 0;
		this.grade = 0;
		setChanged();
		notifyObservers();
	}
    
    /*
    public void setTerritory(Capital capital){
	
    }*/

    
    
}
