//package org.usfirst.frc.team5499.robot.commands;
//
//import org.usfirst.frc.team5499.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class TestPID extends Command {
//
//	public TestPID() {
//		// Use requires() here to declare subsystem dependencies
//		// eg. requires(chassis);
//		requires(Robot.driveTrainSubsystem);
//	}
//
//	// Called just before this Command runs the first time
//	protected void initialize() {
//	}
//
//	// Called repeatedly when this Command is scheduled to run
//	protected void execute() {
//		Robot.driveTrainSubsystem.motorFrontLeft.set(250);
//		System.out.println(Robot.driveTrainSubsystem.motorFrontLeft.getEncVelocity());
//	}
//
//	// Make this return true when this Command no longer needs to run execute()
//	protected boolean isFinished() {
//		return false;
//	}
//
//	// Called once after isFinished returns true
//	protected void end() {
//	}
//
//	// Called when another command which requires one or more of the same
//	// subsystems is scheduled to run
//	protected void interrupted() {
//	}
//}
