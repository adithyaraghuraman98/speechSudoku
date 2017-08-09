# Speech Sudoku
Welcome to speech sudoku. This project aims to simulate a sudoku puzzle by using user speech as input.

# What the project does
As mentioned earlier, the aim of this project is to simulate a sudoky puzze by using user speech as input. This is done by using the speech recognition engine of python. This game requires speech in two different scenarios, one in which 
Once speech input is recieved, depending on the mode the game is in, the speech is processed and appropriate actions are taken. 


# Purpose of the project

# How you can get started with the project
  Requirements to get started: 
  
    Python 2.7+
    
    PyAudio for microphone input
    
    Python SpeechRecognition module
  
  Instructions: -
  1. Run code
  2. Press return to go to board input interface.
  3. Input the values of a desired sudoku board by reading values across and down (0 for empty location).
  4. Once the board is ready, there are two main commands that must be used to play the game, these are of the form
  * "Put x in (Row, col)"
  
  This is the command to add an element into the board. Here the row is a letter ranging from A to I and the col is an integer ranging from 0 to 9. For example, if we want to add the number to 3 in the 2nd row 3rd column. We would say "Put 3 in C2".
 
 * "Display (row, col)"
 
 This is the command to go to an element in the board. This command will display all of the cells in the board that contain the value present in (row, com).
  

# Where you can get help with the project
