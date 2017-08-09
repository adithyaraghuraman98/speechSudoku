# Speech Sudoku
Welcome to speech sudoku. This project aims to simulate a sudoku puzzle by using user speech as input.

# What the project does
As mentioned earlier, the aim of this project is to simulate a sudoky puzze by using user speech as input. This is done by using the speech recognition engine of python. This game requires speech in two different scenarios, one in which 
Once speech input is recieved, depending on the mode the game is in, the speech is processed and appropriate actions are taken. 


# Purpose of the project

There are two main reasons I started developing this project. Firstly, I wanted to improve my coding abilities in python and wanted to learn to use emerging technologies like speech recognition. I feel that the ability to learn new technologies in a short period of time is essential in industry and such projects will definitely help me gather that skill. Secondly, I felt that a project like this would be beneficial to people who have a physical impairment and are unable to use a keyboard. That is the reason that I decided to make this project the way it is. 

# How you can get started with the project
  Requirements to get started: 
  
    Python 2.7+
    
    PyAudio for microphone input
    
    Python SpeechRecognition module: https://pypi.python.org/pypi/SpeechRecognition/
  
  Instructions: -
  1. Run code
  2. Press return to go to board input interface.
  3. Input the values of a desired sudoku board by reading values across and down (0 for empty location).
  
  Board values must be input in 3 row segments so as to ensure optimal performance of the speech recignition module. The segment of the board being processed will be displayed on-screen.
  4. Once the board is ready, there are two main commands that must be used to play the game, these are of the form
  * "Put x in (Row, col)"
  
  This is the command to add an element into the board. Here the row is a letter ranging from A to I and the col is an integer ranging from 0 to 9. For example, if we want to add the number 3 in the 2nd row 3rd column. We would say "Put 3 in C2".
 
 * "Display (row, col)"
 
 This is the command to go to an element in the board. This command will display all of the cells in the board that contain the value present in (row, col).

If you wish to see a solution to the board, simply say "solution" and the game will present a solution to the existing board. This is done using the solve(row,col,board) method in the code. This is a recursive backtracking algorithm that traverses the board in an across and down fasion and tries to add a value at each index. If it is a valid value, then the algorithm goes on to the next index. If we see that we have reached the end of the board, the game is solved. On the other hand, if we run out of values for a given index we backtrack until the index where we can make a change. If we reach the beginning of the board, and run out of values, then we know that there exists no solution to the board and display that to the user. 

# Where you can get help with the project
A good place to start would be to view the documentation of python available here: https://docs.python.org/3/ and the documentation of the speech recognition module (link provided above).

Additionally, you can contact me at adithyaraghuraman98@gmail.com if you have any questions.
