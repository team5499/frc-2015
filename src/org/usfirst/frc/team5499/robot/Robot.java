
package org.usfirst.frc.team5499.robot;

import org.usfirst.frc.team5499.robot.commands.Autonomous;
import org.usfirst.frc.team5499.robot.commands.RateMotors;
import org.usfirst.frc.team5499.robot.commands.TeleOpDrive;
import org.usfirst.frc.team5499.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final RateMotors rateMotors= new RateMotors();
	public static final DrivetrainSubsystem driveTrainSubsystem = new DrivetrainSubsystem();
	public static final Autonomous autonomous = new Autonomous();
	public static OI oi;
	public static PowerDistributionPanel pdp;
	
    Command autonomousCommand;
    Command teleopCommand;
    


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
        autonomousCommand = new RateMotors();
        teleopCommand = new TeleOpDrive();
        pdp = new PowerDistributionPanel();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
//    	autonomousCommand.start(); 	
    	
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        if (teleopCommand != null) teleopCommand.start();
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
