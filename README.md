# chessGame

What it is:
  A standard chess game that follows the standard move set and rules.
  
Skills exercised:
  Object Oriented Programming

What I learned:
  In the creation process I began to realize my short comings in design. My general approach of a check list of things to add was not a proper way to implement new components to the game. This realization stressed to me the importance of design, preparation, and architecture.

Challenging bugs:
  - Check: accounting for multiple checks in the event of a discovered attack was particuarly tricky. As well as making sure a players own king won't be put into check because of a move. In my approach, I scan all potential check paths for one king. If a check path is found to indeed be a check then the scan ends. However, this scan does not account for the possibility of multiple checks.

Changes I'd like to implement:
  + Checkmate
  + Rules like castling and 'en passant
  + Piece graphics
