package script.util.enums;

import org.powerbuddy.api.wrapper.Area;
import org.powerbuddy.api.wrapper.Tile;

public enum GnomeAreas {
	BEFORE_LOG(new Area(
		new Tile(2478, 3441, 0),
		new Tile(2473, 3441, 0),
		new Tile(2470, 3436, 0),
		new Tile(2478, 3435, 0)
		)),
	
	AFTER_LOG(new Area(
		new Tile(2478, 3430, 0),
		new Tile(2471, 3430, 0),
		new Tile(2471, 3426, 0),
		new Tile(2478, 3426, 0)
		)),
	
	TREE_ONE(new Area(
		new Tile(2477, 3425, 1),
		new Tile(2470, 3425, 1),
		new Tile(2470, 3421, 1),
		new Tile(2477, 3421, 1)
		)),
	
	ROPE(new Area(
		new Tile(2478, 3422, 2),
		new Tile(2472, 3421, 2),
		new Tile(2472, 3418, 2),
		new Tile(2478, 3417, 2)
		)),
	
	TREE_TWO(new Area(
		new Tile(2483, 3422, 2),
		new Tile(2483, 3418, 2),
		new Tile(2488, 3418, 2),
		new Tile(2489, 3422, 2)
		)),

	UNDER_TREE(new Area(
		new Tile(2488, 3417, 0),
		new Tile(2483, 3417, 0),
		new Tile(2483, 3426, 0),
		new Tile(2489, 3426, 0)
		)),
	
	BEFORE_PIPES(new Area(
		new Tile(2482, 3426, 0),
		new Tile(2482, 3433, 0),
		new Tile(2489, 3433, 0),
		new Tile(2489, 3426, 0)
		)),
		
	AFTER_PIPES(new Area(
		new Tile(2483, 3437, 0),
		new Tile(2483, 3441, 0),
		new Tile(2489, 3441, 0),
		new Tile(2489, 3437, 0)
		));
	
	private final Area area;
	
	GnomeAreas(Area area) {
		this.area = area;
	}
	
	public Area getArea() {
		return area;
	}
	
}
