find . -type f -path "./src/*/*" -name "*.class" -delete
 
cd src
javac game/Game.java
java game.Game