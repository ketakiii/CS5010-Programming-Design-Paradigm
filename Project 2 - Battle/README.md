# Project Description 
The purpose of this project is to design and implement a model for a role-playing game that will allow gamers to build players that can then be entered into an arena for one-on-one combat following this turn-based battle strategy.

# UML 
Updated UML
![UML](../UML/new2.png)

Old UMl 
![UML](../UML/oldUML.png)

# Changes Made to the Updated UML 
* Addition of a Battle Interface.
* Addition of a few more methods in the classes to support essential functionality.

# Assumptions 
* We would have to know the id of the players in the playerList to pick the weapons. 
* Gear Bag is randomly selected and the players choose from that.

# Limitations 
* The battle can only be played with 2 players. 
* Java version 11 or more required. 

# How to run a program

Run the jar file:
java -jar out/artifacts/Project_2_jar/Project\ 2.jar

## Create a new Battle instance
``Battle battle = new Battle(2);``

## Assign Gears to the Players 
``battle.assignGearsToPlayers();``

## Create an Armoury 
``battle.createArmory();``

## Pick Weapons for Player 
``battle.pickWeaponForPlayer(0);``

``battle.pickWeaponForPlayer(1);``

## Start the Battle 
``battle.play();``


# Example Run 
```
Battle battle = new Battle(2);
battle.getPlayerInfo();
battle.assignGearsToPlayers();
battle.getPlayerInfo();
battle.createArmory();
battle.pickWeaponForPlayer(0);
battle.pickWeaponForPlayer(1);
battle.getPlayerFullInfo()
battle.getFirstStrikingPlayer()
battle.play();
```

# Citations
1. [Canvas Question](https://northeastern.instructure.com/courses/136753/assignments/1707739#submit)
2. [Google](https://www.google.com/)
3. [StackOverflow](https://stackoverflow.com/)
4. [Java Documentation](https://docs.oracle.com/en/java/)