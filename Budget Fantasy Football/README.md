# Fantasy Football App

## General Idea
In this program, I will present the user with a program where they can create teams and add 
them to the applications league.
With these teams, I will allow users to add up to 11 players to their team.
Adding these players will allow the users to keep track of the goals and assist that these players produce
which will automatically update the teams score if the player was in/ or is added to their team.

*Key Parts*:

- Users can make their own teams

- Users can add 11 players to their team in order to keep track of their progress

- By adding how many goals/assists a given player produces, the user can keep a score of how well 
the player is doing.

- For this phase, I will be using a pre-existing list of players that the user can view.
After viewing this list, the user can add one of these players at a time to their team.
This will be updated in the future phases.

The target for this program is to allow fans of football to create their own fantasy team and input the scores of
the players that they chose to keep in their team. It will act as a fun way to keep a tally of which 
players are doing well.

The reason I chose this project is that I have been a fan of football for many years now and found that
this program would be a fun way to improve my coding skills while also creating something that I find
interesting. Although it is a basic version of a Fantasy Football App, I see this is the best mix of work
and fun for myself which I think will lead me to the best results I can get.

##*User Story*

- As a user, I want to be able to create a team which can include 11 players

- As a user, I want to be able to add up to 11 players to my team by naming them and their club.

- As a user, I want to be able to update my players stats such as goals and assists in order to 
keep track of how well the players are doing

- As a user, I want to be able to add these players to my team which will result in my
teams overall score going up by the score the player has (For the first phase).

- As a user, I want to be able to save the teams that I have added to a league with the correct score.

- As a user, I want to be able to load a file with my saved league in it.

- As a user, I want to see the option to save and load my file from the main menu.


*Phase 4: Task 2*

- Made the 'Team' class robust by adding an exception to the addPlayer method.

- This exception is thrown by the method in the 'Team' class and is caught in multiple other methods where
 'addPlayer' is used.
 
- This includes the 'JsonReader', 'FantasyApp', and 'MainGui' classes.

*Phase 4: Task 3*

- The UML Design Diagram that I made for this shows the multiplicities and other relationships between the classes
of my application.

- If I had more time to work on this, I would attempt to create a super/sub class relationship between the 'League',
'Team', and 'Player' classes as I feel that it would work well and improve the simplicity of the application.



