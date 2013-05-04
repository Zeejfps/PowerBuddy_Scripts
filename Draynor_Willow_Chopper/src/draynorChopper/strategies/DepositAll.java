package draynorChopper.strategies;

import org.powerbuddy.api.methods.Bank;
import org.powerbuddy.api.methods.Camera;
import org.powerbuddy.api.methods.GameObjects;
import org.powerbuddy.api.methods.Inventory;
import org.powerbuddy.api.util.Random;
import org.powerbuddy.api.wrapper.GameObject;

import draynorChopper.enums.State;
import draynorChopper.main.DraynorChopper;
import draynorChopper.util.Strategy;

/*
 * Deposits all items once in bank
 * 
 * Add:
 *   Need to add randomness on closing the bank.
 */
public class DepositAll implements Strategy{

	private static int[] boothIds = {2213, 2215};
	private static int willowLog = 1520;
	
	@Override
	public boolean isValid() {
		return DraynorChopper.state.equals(State.IN_BANK);
	}

	@Override
	public void execute() {
		GameObject bankBooth = GameObjects.getNearest(boothIds);
		
		if (bankBooth != null && bankBooth.onScreen()) {
			bankBooth.interact("Bank");
			DraynorChopper.sleep(500, 1500);
			
			if (Bank.isOpen()) {
				DraynorChopper.logsChopped += Inventory.getCount(willowLog);
				Bank.deposit(willowLog, 28);		
				DraynorChopper.sleep(200, 500); //Need to replace with different sleep methods!
				switch(Random.nextInt(0, 5)) {
				
				case 1: case 2:
					Bank.close();
					break;
					
				default: 
					break;
				}
				
				DraynorChopper.sleep(200, 500); //Need to replace with different sleep methods!
				
				if (!Inventory.isFull()) {
					DraynorChopper.state = State.WALKING_TO_TREES;
				}
			}
			
		} else if (bankBooth != null && !bankBooth.onScreen()) {
			Camera.turnTo(bankBooth);
		} else {
			return;
		}	
	}
}
