package triggers;

import org.usfirst.frc.team5449.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class LiftTriggerTest extends Trigger {
	
	@Override
    public boolean get() {
        return !Robot.lifter.is_down();
    }
}
