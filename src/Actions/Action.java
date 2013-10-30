package Actions;

import Data.Personnage;
import Data.Terrain;

public abstract class Action {
	private Personnage owner;
	private Terrain plateau;
	
	public Action(Personnage owner, Terrain plateau) {
		super();
		this.owner = owner;
		this.plateau = plateau;
	}

	public int getCoutPA()  { return 2; }
	
	public abstract void getParameters();
	
	public abstract void execute();
	
}
