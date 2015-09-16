
package org.usfirst.frc.team5499.robot.subsystems;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;


/**
 *
 */
public class GyroSubsystem extends DrivabaseSubsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private I2C gyroChannel = new I2C(Port.kMXP, 110101);
	
	public Gyro gyro = new Gyro(/*RobotMap.GYRO_CH*/ I2C.Port.kOnboard.getValue());

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
