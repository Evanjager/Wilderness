//TO DO: import all libraries that you use here

public class Wild {
	static final int MAXT = 3, // maximum number of simulation time steps
			MAXX = 2, // maximum number of cells on the x-axis
			MAXY = 4; // maximum number of cells on the y-axis

	static int wolfnum = 0;
	static int deernum = 0;

	public static void main(String[] args) {
		Wild w = new Wild();
		Wild map[][] = new Wild[MAXY][MAXX];
		w.initallize(map);
		System.out.println("Generation 0" + "\n" + "Numnber of wolfs: " + wolfnum + " Number of Deers: " + deernum);

		w.display(map);
		System.out.println("=================");
		for (int t = 0; t < MAXT; t++) {
			// TO DO: write your code here
			w.Sim(map);
			System.out.println(
					"Generation " + (t + 1) + "\n" + "Numnber of wolfs: " + wolfnum + " Number of Deers: " + deernum);

			w.display(map);
			System.out.println("=================");

		}

	}

	// TO DO: write your code here; create as many classes, interfaces, etc. that
	// you see fit

	public void display(Wild[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] instanceof Wolf) {
					String wolfs = map[i][j].toString().replace(map[i][j].toString(), "W ");
					System.out.print(wolfs);
				} else if (map[i][j] instanceof Deer) {

					String deers = map[i][j].toString().replace(map[i][j].toString(), "D ");
					System.out.print(deers);
				}
			}
			System.out.println();
		}
	}

	public void initallize(Wild[][] map) { // randomly selects if a deer or wool will be in each cell
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				int animal = (int) (Math.random() * 2);

				if (animal == 0) {
					map[i][j] = new Wolf();
					wolfnum++;
				} else if (animal == 1) {
					map[i][j] = new Deer();
					deernum++;
				}
			}
		}
	}

	public void Sim(Wild[][] map) { // gives the direction to move for each animal and counts the animals at each
									// time step
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				int direction = (int) (Math.random() * 4);

				if (map[i][j] instanceof Wolf) {
					((Wolf) map[i][j]).movewolf(i, j, direction, map);
				} else if (map[i][j] instanceof Deer) {
					((Deer) map[i][j]).movedeer(i, j, direction, map);
				}
			}
		}

		wolfnum = 0;
		deernum = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] instanceof Wolf) {
					wolfnum++;
				} else if (map[i][j] instanceof Deer) {
					deernum++;
				}
			}
		}
	}

}

class Animal extends Wild {
	public void move(int i, int j, int cellI, int cellJ, Wild[][] map, Wild ani) {
		map[cellI][cellJ] = ani; // moves animal to new cell
		map[i][j] = null; // makes cell were animal moved from ow empty
	}
}

class Wolf extends Animal {
	public void eat(int i, int j, int deerI, int deerJ, Wild[][] map) {
		map[deerI][deerJ] = new Wolf(); // replaes deer with wolf
		map[i][j] = null; // makes cell were wolf moved from ow empty

	}

	public void movewolf(int i, int j, int direction, Wild[][] map) { // takes in the direction given from Sim method
																		// then eats,gets eaten, stays put or moves
																		// according to cell instance
		if (direction == 0 && i != map.length - 1) {
			if (map[i + 1][j] instanceof Deer) {
				eat(i, j, i + 1, j, map);

			} else if (map[i + 1][j] instanceof Wolf) {

			} else if (map[i + 1][j] == null) {
				move(i, j, i + 1, j, map, new Wolf());

			}

		} else if (direction == 1 && j != map[i].length - 1) {
			if (map[i][j + 1] instanceof Deer) {
				eat(i, j, i, j + 1, map);

			} else if (map[i][j + 1] instanceof Wolf) {

			} else if (map[i][j + 1] == null) {
				move(i, j, i, j + 1, map, new Wolf());

			}
		} else if (direction == 2 && i != 0) {
			if (map[i - 1][j] instanceof Deer) {
				eat(i, j, i - 1, j, map);

			} else if (map[i - 1][j] instanceof Wolf) {

			} else if (map[i - 1][j] == null) {
				move(i, j, i - 1, j, map, new Wolf());

			}
		} else if (direction == 3 && j != 0) {
			if (map[i][j - 1] instanceof Deer) {
				eat(i, j, i, j - 1, map);

			} else if (map[i][j - 1] instanceof Wolf) {

			} else if (map[i][j - 1] == null) {
				move(i, j, i, j - 1, map, new Wolf());

			}
		}

	}

}

class Deer extends Animal {
	public void movedeer(int i, int j, int direction, Wild[][] map) { // takes in direction from Sim method then eats,
																		// gets eaten, stays put or moves accordingly to
																		// cell instance
		if (direction == 0 && i != map.length - 1) {
			if (map[i + 1][j] instanceof Wolf) {
				map[i][j] = null;
			} else if (map[i + 1][j] instanceof Deer) {

			} else if (map[i + 1][j] == null) {
				move(i, j, i + 1, j, map, new Deer());

			}
		} else if (direction == 1 && j != map[i].length - 1) {
			if (map[i][j + 1] instanceof Wolf) {
				map[i][j] = null;
			} else if (map[i][j + 1] instanceof Deer) {

			} else if (map[i][j + 1] == null) {
				move(i, j, i, j + 1, map, new Deer());

			}
		} else if (direction == 2 && i != 0) {
			if (map[i - 1][j] instanceof Wolf) {
				map[i][j] = null;
			} else if (map[i - 1][j] instanceof Deer) {

			} else if (map[i - 1][j] == null) {
				move(i, j, i - 1, j, map, new Deer());

			}
		} else if (direction == 3 && j != 0) {
			if (map[i][j - 1] instanceof Wolf) {
				map[i][j] = null;
			} else if (map[i][j - 1] instanceof Deer) {

			} else if (map[i][j - 1] == null) {
				move(i, j, i, j - 1, map, new Deer());

			}
		}
	}
}
