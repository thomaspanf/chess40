TodoList: 

Implemented:

-  game state 
-  turns 
-  move piece
-  user input 
-  capture
-  remove piece 
-  castle
-  draw
-  resignation
-  check
-  checkmate
-  en passant 
-  promotion 





To Implement: 
-  bugs :) 

To compile javadoc:    javadoc -d docs src -tag Version:X -tag Since:X

/loop through every tile
for each tile with enemy piece
check if enemy piece has valid move to king 

once you find a valid move to king, return yes in check 

pass in current location of king, call function 9 time for all
locations of king. 