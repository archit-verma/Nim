public class NimPlayer
{
    /**
     * Empty Constructor
     */
	private String name;	//class variables 
	private int wins = 0;	//private to ensure data hiding
	private int games = 0;
	
	// Accessors and Mutators for each variable
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
    	return name;
    }

	public int getWins()
	{
		return wins;
	}

	public void setWins()
	{
		wins ++;
	}

	public int getGames()
	{
		return games;
	}

	public void setGames()
	{
		games ++;
	}
    
    public int removeStone(int remove)	//class method to remove stone
    {
    	return remove;
    }
}
