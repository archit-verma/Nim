import java.util.Scanner;	//import scanner class
public class Nimsys 
{
    public static void main(String[] args)
    {
    	Scanner keyboard = new Scanner(System.in);	//Create a Scanner Object
        System.out.println("Welcome to Nim");
        System.out.println("");
        System.out.println("Please enter a command to continue");
        System.out.println("");
        int flag = 0;	//flag variable to control execution of program
        while (flag == 0)
        {
        	System.out.print("$ ");		//Command prompt symbol
        	String command = keyboard.next();
        	
        	switch(command.toLowerCase())	//command case handling
        	{
        		case "help":
        			help();
        			break;
        		
        		case "commands":
        			commands();
        			break;
        		
        		case "exit":
        			flag = exit();	//change flag value to stop program
        			break;
        		
        		case "start":
        			NimPlayer player1 = new NimPlayer();	//Player object creation1
        	    	NimPlayer player2 = new NimPlayer();	//Player object creation2
        	    	System.out.println("");
        			System.out.print("Please enter Player 1's name : ");
        			player1.setName(keyboard.next()); 
        			System.out.print("Please enter Player 2's name : ");
        			player2.setName(keyboard.next());
        			boolean game = true;
        			while (game == true)	//variable to control the start of game
        			{
        				game = startGame(keyboard, player1, player2);
        			}
            		
        		default:
        			continue;
        			
        	}
        }
    }
    
    public static void help()	//help command
    {
    	System.out.println("Type 'commands' to list all available commands");
		System.out.println("Type 'start' to play game");
		System.out.println("Player to remove last stone loses!");
    }
    
    public static void commands()	//commands command
    {
    	System.out.println("");
    	System.out.println(": start");
		System.out.println(": exit");
		System.out.println(": help");
		System.out.println(": commands");
    }
    
    public static int exit()	//exit command
    {
    	int flag = 1;
    	System.out.println("");
		System.out.println("Thank you for playing Nim");
		System.out.println("");
		return flag;
    }
    
    public static boolean startGame(Scanner keyboard, NimPlayer player1, NimPlayer player2)
    {
    	player1.setGames();
    	player2.setGames();	
    	System.out.print("Enter upper bound : ");
    	int upperBound = keyboard.nextInt();
		System.out.print("Enter initial number of stones : ");
		int stones = keyboard.nextInt();
		int turn = 0;	//variable to track turn
		NimPlayer [] players = new NimPlayer[] {player1, player2};		//Creating object array
		while (stones > 0)
		{
			System.out.println("");
			System.out.printf("%d stones left :", stones);
			for(int i = 0; i < stones; i++)		//loop to print stones
			{
				System.out.print(" *");
			}
			
			int remove = 0;
			
			while (true)	//loop to check valid input
			{
				System.out.println("");
				System.out.printf("%s's turn. Enter stones to remove : ", players[turn].getName());
				remove = keyboard.nextInt();
				
				if (remove > upperBound)
				{
					System.out.printf("Upper bound limit exceed, upper bound maximum choice is %d", upperBound);
					System.out.println("");
				}
				
				else if (remove > stones)
				{
					System.out.printf("Invalid attempt, only %d stones remaining! Try again:", stones);
					System.out.println("");
				}
				
				else
				{
					break;
				}
			}
			
			stones -= players[turn].removeStone(remove);
			
			turn = (turn + 1)%2;	//expression to alternate turns
			
			if (stones == 0)
			{
				System.out.println("");
				System.out.println("Game Over");
				System.out.printf("%s wins!", players[turn].getName());	//person who does not have stones left to pick, wins
				System.out.println("");
				players[turn].setWins();
			}
		}
		
		System.out.println("");
		System.out.print("Do you want to play again (Y/N): ");
		String replay = keyboard.next();
		
		if (replay.equals("y") || replay.equals("Y"))	//checking case to replay
		{
			return true;
		}
		
		else 
		{
			for (int i=0; i<2; i++)		//display results
			{
				if (players[i].getWins() > 1)
				{
					System.out.printf("%s won %d games out of %d played",players[i].getName(),players[i].getWins(),players[i].getGames());
				}
				
				else
				{
					System.out.printf("%s won %d game out of %d played",players[i].getName(),players[i].getWins(),players[i].getGames());
				}
				
				if (i == 0)
				{
					System.out.println("");
				}
			}
			System.out.println("");
			return false;
		}	
    }
}
