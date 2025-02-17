import java.util.Scanner; 

class Conversation implements Chatbot {
//this class has the attributes a transcript array, int current_line, and a random responses string int. This class holds the methods chat, print transcript, and respond. 
   String [] transcript; 
   int current_line; 
   String [] random_responses; 
   // random response list here
   // Attributes 

  /**
   * Constructor 
   */
  Conversation() { 
   // this is the constructor it initializes the current line at zero and the random responses with a total of five different random responses
    this.current_line= 0; 
    //put random response list here
    this.random_responses= new String[5]; 
    String random_response1= "Cool, Cool, Cool! Tell me more!";
    random_responses[0]= random_response1; 
    String random_response2= "Very Interesting! What else?";
    random_responses[1]= random_response2; 
    String random_response3= "How awesome! What else did you do?";
    random_responses[2]= random_response3; 
    String random_response4= "So exciting! What else?";
    random_responses[3]= random_response4; 
    String random_response5= "Oooh! Tell me more!";
    random_responses[4]= random_response5; 

    
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() 
  {
    //this is the chat method it starts and runs the conversation with the user. It begins by asking the number of rounds and the conversation will run for that number of rounds (a round consists of the bot asking a question and then the user's response).
    //After everyline of conversation it adds them to the transcript. After the first round it loops through the correct amount of times and then once finished it prints goodbye. 
    //establishing rounds
    String intro_statement= "Hi! Welcome to my chatbot :)! How many rounds of conversation would you like?"; 
    System.out.println(intro_statement);
    Scanner input= new Scanner(System.in);  
    int rounds= input.nextInt(); 
    this.transcript= new String[2*rounds+3]; 
    transcript[current_line]= intro_statement; 
    current_line++; 
    transcript[current_line]= ""+rounds; 
    input.nextLine(); //throw away
    //if they don't want to talk
    if (rounds==0)
    {
      input.close(); 
      String goodbye= "Goodbye!"; 
      System.out.print(goodbye); 
      transcript[current_line]= goodbye;  
      return; 

    }
    //first round of conversation
    String first_question= "What did you do today?"; 
    transcript[current_line]= first_question;  
    current_line++; 
    System.out.println( first_question); 
    String user_response= input.nextLine(); 
    transcript[current_line]= user_response;  
    current_line++; 
    int current_round; 
    //all subsequent rounds
    for( current_round= 1; current_round < rounds; current_round++ )
    {
      String computer_response= respond(user_response); 
      transcript[current_line]= computer_response;  
      current_line++; 
      System.out.println(computer_response); 
      user_response= input.nextLine(); 
      transcript[current_line]= user_response;  
      current_line++;
    }
    //ending
    input.close(); 
    String goodbye= "Goodbye!"; 
    System.out.print(goodbye); 
    transcript[current_line]= goodbye;  
    
    

  }
    



  /**
   * Prints transcript of conversation
   */
  public void printTranscript() 
  {
    //this iterates through the transcript array and prints out each line
    System.out.println("\n\nTranscript: "); 
    for (int transcript_index=0; transcript_index < transcript.length-1; transcript_index++ )
    {
      System.out.println(transcript[transcript_index]); 
    }
    return; 
  }

  

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String user_response) 
  {
    //this method responds to the user by mirroring their langauge. It converts I -> you, me -> you, am->are, you -> I,my-> your, and your->my.
    // If there is no language present to mirror it will generate one of five random responses.
    
    // establish variables we will use
    String computer_response= " "; 
    String working_user_response= user_response; 
    boolean has_keywords= false; 
    

   //removing end punctuation and making all lowercase and adding spaces at beginning and end
    if (user_response.charAt(user_response.length()-1)== '.' || user_response.charAt(user_response.length()-1)== '!' || user_response.charAt(user_response.length()-1)== '?')
    {
      working_user_response= " "+ user_response.substring(0,user_response.length()-1)+ " "; 
      working_user_response= working_user_response.toLowerCase(); 
    }
    else
    {
     working_user_response= " "+ user_response+ " "; 
     working_user_response= working_user_response.toLowerCase(); 
    }
    
    //go through string 
    for (int current_letter_idx= 0; current_letter_idx< working_user_response.length(); current_letter_idx++ )
    {  
      // finding and replacing I with you
      if (working_user_response.charAt(current_letter_idx)== 'i' && working_user_response.charAt(current_letter_idx-1)== ' '&& working_user_response.charAt(current_letter_idx+1)==' ')
      {
        has_keywords= true; 
        // if I is first letter in scentence
        if (current_letter_idx==1)
        {
          String second_half= working_user_response.substring(current_letter_idx+1);
          working_user_response= " You" + second_half; 
        }
        // if I isn't the first letter in the scentence. 
        else
        {
          String first_half= working_user_response.substring(0,current_letter_idx); 
          String second_half= working_user_response.substring(current_letter_idx+1); 
          working_user_response= first_half+ "you" + second_half; 
          
        }
      }
      // //finding and replacing me with you
      else if (working_user_response.charAt(current_letter_idx)== 'm' && working_user_response.charAt(current_letter_idx-1)== ' '&& working_user_response.charAt(current_letter_idx+1)== 'e' && working_user_response.charAt(current_letter_idx+2)== ' ')
      {
        has_keywords= true; 
        
        //if Me is first word
        if (current_letter_idx==1)
        {
          String second_half= working_user_response.substring(current_letter_idx+2); 
          working_user_response= "You"+second_half; 
        }

        //if Me isn't first word
        else
        {
          String first_half= working_user_response.substring(0,current_letter_idx); 
          String second_half= working_user_response.substring(current_letter_idx+2); 
          working_user_response= first_half+ "you" + second_half;
        }
      }
      //finding and replacing am with are
      else if(working_user_response.charAt(current_letter_idx)=='a'&& working_user_response.charAt(current_letter_idx-1)==' '&& working_user_response.charAt(current_letter_idx+1)=='m'&& working_user_response.charAt(current_letter_idx+2)==' ')
      {
        has_keywords= true; 
        if (current_letter_idx==1)
        {
          String second_half= working_user_response.substring(current_letter_idx+2); 
          working_user_response= "Are"+ second_half; 
        }

        else
        {
          String first_half= working_user_response.substring(0,current_letter_idx); 
          String second_half= working_user_response.substring(current_letter_idx+2); 
          working_user_response= first_half+ "are" + second_half;
        }
      }
      //finding and replacing you with I 
      else if(working_user_response.charAt(current_letter_idx)=='y' && working_user_response.charAt(current_letter_idx-1)==' '&& working_user_response.charAt(current_letter_idx+1)=='o'&& working_user_response.charAt(current_letter_idx+2)=='u'&& working_user_response.charAt(current_letter_idx+3)==' ')
      {
        has_keywords= true;
        if(current_letter_idx==1)
        {
          String second_half= working_user_response.substring(current_letter_idx+3); 
          working_user_response= "I"+ second_half;
        }

        else
        {
          String first_half= working_user_response.substring(0,current_letter_idx); 
          String second_half= working_user_response.substring(current_letter_idx+3); 
          working_user_response= first_half+ "I" + second_half;
        }
      }
      //finding and replacing my with your
      else if(working_user_response.charAt(current_letter_idx)=='m'&& working_user_response.charAt(current_letter_idx-1)==' '&&working_user_response.charAt(current_letter_idx+1)=='y'&& working_user_response.charAt(current_letter_idx+2)==' ')
      {
        has_keywords= true; 
        if(current_letter_idx==1)
        {
          String second_half= working_user_response.substring(current_letter_idx+2); 
          working_user_response= "your"+ second_half;
        }

        else
        {
          String first_half= working_user_response.substring(0,current_letter_idx); 
          String second_half= working_user_response.substring(current_letter_idx+2); 
          working_user_response= first_half+ "your" + second_half;
        }

      }
      //finding and replacing your with my
      else if (working_user_response.charAt(current_letter_idx)== 'y'&& working_user_response.charAt(current_letter_idx-1)== ' '&& working_user_response.charAt(current_letter_idx+1)== 'o'&& working_user_response.charAt(current_letter_idx+2)== 'u' & working_user_response.charAt(current_letter_idx+3)=='r'&& working_user_response.charAt(current_letter_idx+4)==' ')
      {
        has_keywords= true; 
        if(current_letter_idx==1)
        {
          String second_half= working_user_response.substring(current_letter_idx+4); 
          working_user_response= "my"+ second_half;
        }

        else
        {
          String first_half= working_user_response.substring(0,current_letter_idx); 
          String second_half= working_user_response.substring(current_letter_idx+4); 
          working_user_response= first_half+ "my" + second_half;
        }
      }


    } 
    computer_response=working_user_response; 
     //if no keywords present
    if (has_keywords==false)
    {
      // random response generator
      double random_number= Math.random(); 
      if (random_number < 0.20)
      {
        computer_response= random_responses[0]; 
      }
      else if (random_number>=0.20 && random_number<0.40)
      {
        computer_response= random_responses[1]; 
      }
      else if (random_number>=0.40 && random_number<0.60)
      {
        computer_response= random_responses[2]; 
      }
      else if (random_number>=0.60 && random_number<0.80)
      {
        computer_response= random_responses[3]; 
      }
      else 
      {
        computer_response= random_responses[4]; 
      }
    }
    
     // making sure first letter isn't space 
     if(computer_response.charAt(0)==' ')
     {
       computer_response=computer_response.substring(1); 
     }

     // making sure first letter is capatalized
     String first_letter= ""+computer_response.charAt(0);
     String first_letter_capitalized= first_letter.toUpperCase();
     
     if (!first_letter.equals(first_letter_capitalized))
     {
       computer_response= first_letter.toUpperCase() + computer_response.substring(1); 
     }
     
     //making sure there is no space at end
     char last_letter= computer_response.charAt(computer_response.length()-1);
     
     if (last_letter== ' ')
     {
       computer_response= computer_response.substring(0,computer_response.length()-1); 
     } 

     //adding question mark at end
     computer_response= computer_response+'?'; 
   return computer_response; 
   }


  


  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}

