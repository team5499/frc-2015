
package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OutOfWayAutoCommand extends Command {

    public OutOfWayAutoCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivebaseSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebaseSubsystem.timedMove(3, 0, 60);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}