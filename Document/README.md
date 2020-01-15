RMIT University Vietnam
Course: INTE2512 Object-Oriented Programming
Semester: 2019C
Assessment name: Final Project
Team name: Team 4
Team members: Le Minh s3757324
              Ho Duy Hoang s3672214
              Ta Quoc Thang s3713564
              Pham Thanh Dat s3678437
              Le Nguyen Thien Phu s3639855

1. INTRODUCTION
In this Final Project, we use Intellij IDEA 2019 to design and build Co Ca Ngua. In this game, we have implemented most of the requirements that the Final Assignment required. Our game follows the basic rules of Co Ca Ngua and we also add some functions to make the game more interesting such as Auto Play, Volume Adjustment, etc.

2. FEATURES
Display game and its GUI components: The game will display the Start Menu and then the Game pane of the game. In addition, the game also displays the Setting page.
Set up players: The game allows players to choose a maximum of 4 players. If the player chooses less than the system will automatically select the remaining players are machines.
Set up the order of play: Players set the volume before playing, then they choose Start Game to play. How many players are allowed to play in a game if the player chooses fewer than 4 players. The system will select the remaining players as machines. After entering the game the player will Roll Dice to get points for horses out of the barn and move on the chessboard. After the horse enters the barn and reaches the 6th box, players will be graded. Players can also adjust the volume directly while playing.
Roll dices: Players will Roll Dices to move horses. In the first turn to get the horse out of the barn the player must roll out 6 points. In the next turn the player can choose 1 of 2 dice for the horse to move.
Move: The horse will only leave the barn when the player rolls 6 or 1 point, and the horse then moves according to the dice the player has selected. After completing a round of the board, the horse will enter the barn with its corresponding color.
Stop: The game will pause when the user clicks on "Pause". Then the game will stop until the player continues to play.	
Moving algorithm: The system will automatically let the horse go if the player does not set up 4 players. If the player test system is empty, the system will switch to the auto-play function. The system will automatically select the most suitable horse to move 
Sound:	There are 3 sound bars in the Setting function. User can adjust the volume as a percentage of Sound Effect and Background Music. In addition, users can adjust the volume for the whole game via the Maser sound bar. In this game, we have 5 sounds: Background music, horse moving, horse kicking, roll dice, open gate.We set up the function to move the hourse(0: Do not thing, 1: simple move,2: move and kick,3: release horse,4: release and kick,5: move to the last point,6: move to the last point and kick )
Score: The game will update the score below each horse base. 
Play again or Quit: This game does not have Play Again function but has Quit function to exit the game. 	
Game status: The game will display the GUI, buttons, GamePane and horses.
Language: Players can convert 2 languages Vietnamese and English in Menu.

3. DESIGN
Game Pane
Start menu
Menu
Setting 
Change horse position in model
Create animation for horses
Checking which player has the remaining turn

4. INSTALLATION
Users click Run to run the project. Then the system will display the Start Game screen, the player will set up the sound and language, then press the start game to play or Exit to exit the game. Then the player will choose how many players to play with if less than 4 systems will automatically select the remaining player as the machine.After entering the main screen, the player will click on Roll Dice to take turns out of the stable for horses . Horses only sloop when the roll dice are 6 points. After leaving the stables will move based on the number of points the player can roll dice. After the roll of dice is over and the horse is selected to move, another player will play. When all four horses of a player enter the barn, the player wins and the other players continue to play until the last player's horse finally entered the barn. Users can pause the game if they click on the Pause button. Players can also adjust the volume for the game or quit the game if they want.

5. KNOWN BUGS
Do not have Networking

6. ACKNOWLEDGEMENT
[1] "Java Internationalization - javatpoint", www.javatpoint.com, 2020. [Online]. Available: https://www.javatpoint.com/internationalization-in-java. [Accessed: 14- Jan- 2020]
[2]"Internationalizing the Sample Program (The Java™ Tutorials > Internationalization > Introduction)", Docs.oracle.com, 2020. [Online]. Available: https://docs.oracle.com/javase/tutorial/i18n/intro/steps.html?fbclid=IwAR00AiifBkc8cmbF43D0YEUM6ZYayCq_ldGYnVxZBzE0xlL_-2n-5ElxSeE. [Accessed: 14- Jan- 2020]
[3]Tutorials.jenkov.com, 2020. [Online]. Available: http://tutorials.jenkov.com/java-internationalization/resourcebundle.html?fbclid=IwAR2FqduxMXmdSdnltj4q7BIrOCbadtPCiSEWwg2da_mvY71n-rPZHuQEG58. [Accessed: 14- Jan- 2020]
[4]"Code Conventions for the Java Programming Language: 9. Naming Conventions", Oracle.com, 2020. [Online]. Available: https://www.oracle.com/technetwork/java/codeconventions-135099.html?fbclid=IwAR2ZlGNIfZqOB3fUjnW0A5dKgyPZFeaq8XKSgGbbfc6qBrw3dnmC-skDNdo. [Accessed: 14- Jan- 2020]
[5]"JavaFX Playing Audio - javatpoint", www.javatpoint.com, 2020. [Online]. Available: https://www.javatpoint.com/javafx-playing-audio?fbclid=IwAR3WFDkfH5LvjWqk6OCoY55aLShLcb4VWCM7IFAyA-JkZBAYRlSpz0U-hFc. [Accessed: 14- Jan- 2020]
[6]"CSS Examples", W3schools.com, 2020. [Online]. Available: https://www.w3schools.com/css/css_examples.asp. [Accessed: 14- Jan- 2020]