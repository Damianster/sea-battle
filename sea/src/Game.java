import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Game {
    private ArrayList<Ship> ship = new ArrayList<Ship>();
    int numGuess = 0;

    public static void main(String[] args) {
        Game game = new Game();
        game.setUp();
    }
    private void setUp() {

        ship.add(new Ship("Sub",3));
        ship.add(new Ship("Battle Ship",3));
        ship.add(new Ship("War Machine", 3));
        setLocations();

        System.out.println("Welcome to the battle ship game\n your goal is to sink all of the ships as fast as possible\n good luck");
        play();
    }
    private void play() {
        String guess, result;
        Scanner input = new Scanner(System.in);
        while (!ship.isEmpty()){
            result = "miss";
            numGuess++;
            System.out.println("Enter a guess");
            guess = input.nextLine();
            guess = guess.toUpperCase();
            for (int i = 0; i < ship.size(); i++){
                result = ship.get(i).check(guess);
                if(result.equals("kill")){
                    result = ("you sunk"  + ship.get(i).getName());
                    ship.remove(i);
                    break;
                }else if (result.equals("hit")){
                    break;
                }
            }
            System.out.println(result);
        }
        input.close();
        finish();
    }
    private void finish() {
        if(numGuess == 9){
            System.out.println("Congradulations! you got a perfect score!!");
        }else if(numGuess < 20){
            System.out.println("Congradulations you did great! It took you " + numGuess + "  guesses");
        }else if(numGuess < 30){
            System.out.println("You did so so, it took you " + numGuess + " guesses. Maybe you will do better next time");
        }else{
            System.out.println("You suck it took you" + numGuess + " guesses");
        }
    }
    private void setLocations() {
        Random rand = new Random();
        ArrayList<String> locationToSet = new ArrayList<String>();
        ArrayList<String> temp = null;
        int let, num, incl, incn;
        String alpha = "ABCDEFG";
        boolean worked;
        for (int i = 0; i <ship.size(); i++){
            worked = false;
            start:
            while (!worked){
                locationToSet.clear();//clear the loc setter
                worked = true;
                let = rand.nextInt(5);
                num = 1 + rand.nextInt(5);
                //this code will figure out whether to put the ship
                //vertical or horizontal
                if (num % 2 == 0){
                    incl = 1; //vert
                    incn = 0;
                }else{
                    incl = 0;
                    incn = 1; //horizontal
                }//close if
                for (int j = 0; j < ship.get(i).getSize(); j++){
                    String loc = "" + alpha.charAt(let) + num;
                    let += incl;
                    num += incn;
                    for  (int t = 0; t < ship.size(); t++){
                        if (t != i){
                            temp = ship.get(t).getLocations();
                            if (temp.contains(loc)){
                                worked  = false;
                                continue start;//we failed start over
                            }
                        }//close if
                    }//close t for we passed if we get here
                    locationToSet.add(loc);
                }//close for j
                ship.get(i).setLocation(locationToSet);
            }//close while
        }//close i for
    }//close location to set
}//close class




