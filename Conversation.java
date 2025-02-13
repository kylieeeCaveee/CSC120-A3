import java.util.Scanner; 

class Conversation implements Chatbot {

   String [] transcript; 
   int current_line; 
   // Attributes 

  /**
   * Constructor 
   */
  Conversation() { 
    // this.transcript = transcript[40]; 
    this.current_line= 0; 
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    String intro_statement= "Hi! Welcome to my chatbot :)! How many rounds of conversation would you like?"; 
    System.out.println(intro_statement); 
    // transcript[current_line]= intro_statement; 
    // current_line++; 
    Scanner input= new Scanner(System.in); 
    int rounds= input.nextInt(); 
    String first_question= "What did you do today?"; 
    System.out.print( first_question); 
    // transcript[current_line]= first_question;  
    // current_line++; 
    int current_round; 
    for( current_round=0 ; current_round <= rounds; current_round++ ){
      String user_response= input.nextLine(); 
      // transcript[current_line]=  user_response; 
      // current_line++;
      String computer_response= " "; 
      String working_user_response= " "; 
      if (user_response.charAt(user_response.length()-1)== '.' || user_response.charAt(user_response.length()-1)== '!'){
        working_user_response= " "+ user_response.substring(0,user_response.length()-2) + " "; 
      }else{
        working_user_response= " "+ user_response + " "; 
      }
      for (int current_letter_idx= 0; current_letter_idx< working_user_response.length(); current_letter_idx++ ){ 
        if (working_user_response.charAt(current_letter_idx)== 'I' && working_user_response.charAt(current_letter_idx-1)== ' '&& working_user_response.charAt(current_letter_idx+1)==' '){
          if (current_letter_idx==0){
            String second_half= working_user_response.substring(current_letter_idx+1);
            computer_response= "you" + second_half+ "?"; 
            System.out.println(computer_response);
          }else {
            String first_half= working_user_response.substring(0,current_letter_idx); 
            String second_half= working_user_response.substring(current_letter_idx+1); 
            computer_response= first_half+ "you" + second_half+ "?"; 
            System.out.println(computer_response);
          }
          
          
          }
        // else{ 
        //     computer_response= "cool cool cool! Tell me more";
        } 
      }
      // 
      // transcript[current_line]= computer_response;  
      // current_line++; 
      
      input.close(); 
    }
    



  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    // System.out.println("Transcript: "); 
    // for (int transcript_index=0; transcript_index <= transcript.length; transcript_index++ ){
    //   System.out.println(transcript[transcript_index]); 
    return; 
    }

  

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    String returnString = ""; 
    return returnString; 
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
