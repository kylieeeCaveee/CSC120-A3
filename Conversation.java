import java.util.Scanner; 
//.split

class Conversation implements Chatbot {
//this class has the attributes a transcript array, int current_line, and a random responses string int. This class holds the methods chat, print transcript, and respond. 
   String [] transcript; 
   int currentLine; 
   String [] randomResponses; 
   // random response list here
   // Attributes 

  /**
   * Constructor 
   */
  Conversation() { 
   // this is the constructor it initializes the current line at zero and the random responses with a total of five different random responses
    this.currentLine= 0; 
    //put random response list here
    this.randomResponses= new String[5]; 
    String randomResponse1= "Cool, Cool, Cool! Tell me more";
    randomResponses[0]= randomResponse1; 
    String randomResponse2= "Very Interesting! What else";
    randomResponses[1]= randomResponse2; 
    String randomResponse3= "How awesome! What else did you do";
    randomResponses[2]= randomResponse3; 
    String randomResponse4= "So exciting! What else";
    randomResponses[3]= randomResponse4; 
    String randomResponse5= "Oooh! Tell me more";
    randomResponses[4]= randomResponse5; 
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    //this is the chat method it starts and runs the conversation with the user. It begins by asking the number of rounds and the conversation will run for that number of rounds (a round consists of the bot asking a question and then the user's response).
    //After everyline of conversation it adds them to the transcript. After the first round it loops through the correct amount of times and then once finished it prints goodbye. 
    //establishing rounds
    String introStatement= "Hi! Welcome to my chatbot :)! How many rounds of conversation would you like?"; 
    System.out.println(introStatement);
    Scanner input= new Scanner(System.in); 
    int rounds= input.nextInt(); 
    this.transcript= new String[2*rounds+3]; 
    transcript[currentLine]= introStatement; 
    currentLine++; 
    transcript[currentLine]= ""+rounds; 
    input.nextLine(); //throw away
    //if they don't want to talk
    if (rounds==0){
      input.close(); 
      String goodbye= "Goodbye!"; 
      System.out.print(goodbye); 
      transcript[currentLine]= goodbye;  
      return; 
    }
    //first round of conversation
    String firstQuestion= "What did you do today?"; 
    transcript[currentLine]= firstQuestion;  
    currentLine++; 
    System.out.println( firstQuestion); 
    String userResponse= input.nextLine(); 
    transcript[currentLine]= userResponse;  
    currentLine++; 
    int current_round; 
    //all subsequent rounds
    for( current_round= 1; current_round < rounds; current_round++ ) {
      String computerResponse= respond(userResponse); 
      transcript[currentLine]= computerResponse;  
      currentLine++; 
      System.out.println(computerResponse); 
      userResponse= input.nextLine(); 
      transcript[currentLine]= userResponse;  
      currentLine++;
    }
    //ending
    input.close(); 
    String goodbye= "Goodbye!"; 
    System.out.print(goodbye); 
    transcript[currentLine]= goodbye;  
  }
    



  /**
   * Prints transcript of conversation
   */
  public void printTranscript()  {
    //this iterates through the transcript array and prints out each line
    System.out.println("\n\nTranscript: "); 
    for (int transcriptIndex=0; transcriptIndex < transcript.length-1; transcriptIndex++ ){
      System.out.println(transcript[transcriptIndex]); 
    }
    return; 
  }

  

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param userResponse the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String userResponse) {
    //this method responds to the user by mirroring their langauge. It converts I -> you, me -> you, am->are, you -> I,my-> your, and your->my.
    // If there is no language present to mirror it will generate one of five random responses.
    
    // establish variables we will use
    String computerResponse= ""; 
    userResponse=userResponse.toLowerCase(); 
    userResponse= userResponse.trim(); 
    if (userResponse.charAt(userResponse.length()-1)=='.'||userResponse.charAt(userResponse.length()-1)=='?'||userResponse.charAt(userResponse.length()-1)=='!'){
      userResponse= userResponse.substring(0,userResponse.length()-1); 
    }
    String [] workingUserResponse= userResponse.split(" "); 
    boolean hasKeywords= false; 
    
    //go through string 
    for (int currentIdx= 0; currentIdx< workingUserResponse.length; currentIdx++ ){ 
      boolean wordEdited= false;  
      // finding and replacing I with you
      if (workingUserResponse[currentIdx].equals("i")){
        hasKeywords= true; 
        workingUserResponse[currentIdx]= "you"; 
        wordEdited= true; 
      }else if (workingUserResponse[currentIdx].equals("me") && wordEdited== false){
        //finding and replacing me with you
        hasKeywords= true; 
        workingUserResponse[currentIdx]= "you";
        wordEdited= true;
      }else if(workingUserResponse[currentIdx].equals("am") && wordEdited== false){
        //finding and replacing am with are
        hasKeywords= true; 
        workingUserResponse[currentIdx]= "are";
        wordEdited= true;
      }else if(workingUserResponse[currentIdx].equals("you") && wordEdited==false){
        //finding and replacing you with I 
        hasKeywords= true;
        workingUserResponse[currentIdx]= "i";
        wordEdited= true;
      }else if(workingUserResponse[currentIdx].equals("my") && wordEdited==false){
        //finding and replacing my with your
        hasKeywords= true; 
        workingUserResponse[currentIdx]= "your";
        wordEdited= true;
      }else if (workingUserResponse[currentIdx].equals("your") && wordEdited==false){
        //finding and replacing your with my
        hasKeywords= true; 
        workingUserResponse[currentIdx]= "my";
        wordEdited= true;
      }else if (workingUserResponse[currentIdx].equals("are") && wordEdited==false){
        //finding and replacing your with my
        hasKeywords= true; 
        workingUserResponse[currentIdx]= "am";
        wordEdited= true;
      }
    } 
    //setting back to string
    for(int i=0; i<workingUserResponse.length; i++){
      computerResponse= computerResponse+ workingUserResponse[i]+ " "; 
    }

     //if no keywords present
    if (hasKeywords==false){
      // random response generator
      double random_number= Math.random(); 
      if (random_number < 0.20) {
        computerResponse= randomResponses[0]; 
      }else if (random_number>=0.20 && random_number<0.40){
        computerResponse= randomResponses[1]; 
      }else if (random_number>=0.40 && random_number<0.60){
        computerResponse= randomResponses[2]; 
      }else if (random_number>=0.60 && random_number<0.80) {
        computerResponse= randomResponses[3]; 
      }else {
        computerResponse= randomResponses[4]; 
      }
    }
    
    //final formatting
    computerResponse= computerResponse.trim(); 
   // making sure first letter is capatalized
    String first_letter= ""+computerResponse.charAt(0);
    String first_letter_capitalized= first_letter.toUpperCase();
    computerResponse= first_letter_capitalized +computerResponse.substring(1); 
    //replacing lower case i's with uppercase ones
    computerResponse= computerResponse.replaceAll(" i ", " I "); 
    //adding question mark at end
    computerResponse= computerResponse+'?'; 
    return computerResponse; 
 }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}

