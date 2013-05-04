package draynorChopper.conditions;

import org.powerbuddy.api.wrapper.Area;

import draynorChopper.main.DraynorChopper;
import draynorChopper.util.Condition;

public class TillInArea extends Condition{
	private Area area;
	
	public TillInArea(Area area) {
		this.area = area;
	}
	
	@Override
	public boolean isValid() {

		return DraynorChopper.inArea(area);
	}

}
