# BoggleICS4U
 ____   __    ___   ___  __    ____ 
(  _ \ /  \  / __) / __)(  )  (  __)
 ) _ ((  O )( (_ \( (_ \/ (_/\ ) _) 
(____/ \__/  \___/ \___/\____/(____)

#Summary:
  The game of boggle is centered around a player or players finding words on a 5x5 grid of die. The die are all randomized and then players are to find words horizontally, vertically, diagonally, and in a criss-cross fashion. There is a one-player option in which the player tries to beat the clock and there is a two-player option in which each player gets 15 seconds to find a word until a certain points limit is reached or time runs out. Many restrictions are placed on the game, such as word minimum lengths, a time limit, and a point score to reach. Each letter of the word is a point.

#Features:
General:
GUI very user friendly, bright colours, quick and simple movement between frames.
All buttons operate as labelled and act quickly and efficiently.
Descriptions/short comments pop up after choosing a mode to play in for user friendliness.


Dice Class - Randomizes the board and its die.
Validation of words:
- binary search recursively to find word in dictionary
- recursively checks the board to find if it exists

Timers:
- 3 minute timer for single player
- 15 seconds per turn for multiplayer

Settings Menu:
- option to change minimum word length
- something else


