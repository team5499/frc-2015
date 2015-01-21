package org.usfirst.frc.team5499.robot.subsystems;

import java.util.Arrays;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//Create new motors
	public CANTalon motorFrontLeft = new CANTalon(RobotMap.motorFrontLeftid);
	public CANTalon motorFrontRight = new CANTalon(RobotMap.motorFrontRightid);
	public CANTalon motorBackLeft = new CANTalon(RobotMap.motorBackLeftid);
	public CANTalon motorBackRight = new CANTalon(RobotMap.motorBackRightid);
	
	
    public void initDefaultCommand() {}
    
    public void move(double X, double Y, double Z){
    	System.out.print("X: ");
    	System.out.print(X);
    	System.out.print(" Y: ");
    	System.out.print(Y);
    	System.out.print(" Z: ");
    	System.out.println(Z);
    	double[] motorspeeds = motorspeeds(X, Y, Z, 0);
    	System.out.println(" Motor 1: ");
    	System.out.println(motorspeeds[0]);
    	System.out.println(" Motor 2: ");
    	System.out.println(motorspeeds[1]);
    	motorFrontLeft.set(motorspeeds[RobotMap.frontLeftWheelnum]);
    	motorFrontRight.set(motorspeeds[RobotMap.frontRightWheelnum]);
    	motorBackLeft.set(motorspeeds[RobotMap.backLeftWheelnum]);
    	motorBackRight.set(motorspeeds[RobotMap.backRightWheelnum]);
    	
    	//The Talons are on break mode, which is ideal for our purpose.
    	//However, sudden breaking is bad for the gears, so this should gradually decrease the speed of the motors at stopping
    	//This is for both up and down
    	//the 6 is arbitrary.
    	motorFrontLeft.setVoltageRampRate(6);
    	motorFrontRight.setVoltageRampRate(6);
    	motorBackLeft.setVoltageRampRate(6);
    	motorBackRight.setVoltageRampRate(6);
    }
    public double[] motorspeeds_polar(double direction, double magnitude, double rotation){
    	double motorspeeds[] = new double[4];
    	double sinDir = Math.sin(direction + Math.PI/4);
    	double cosDir = Math.cos(direction + Math.PI/4);
    	
    	motorspeeds[RobotMap.frontLeftWheelnum] = magnitude * sinDir + rotation;
    	motorspeeds[RobotMap.frontRightWheelnum] = magnitude * cosDir - rotation;
    	motorspeeds[RobotMap.backLeftWheelnum] = magnitude * cosDir + rotation;
    	motorspeeds[RobotMap.backRightWheelnum] = magnitude * sinDir - rotation;
    	
    	normalize(motorspeeds);
    	
    	return motorspeeds;
    }
    public double[] motorspeeds(double X, double Y, double Z, double gyroAng){
    	double[] motorspeeds = new double[4];
    	double cosAng = Math.cos(Math.PI/4);
    	double sinAng = Math.sin(Math.PI/4);
    	double Xprime = cosAng * X - sinAng * Y;
    	double Yprime = sinAng * X + cosAng * Y;
    	
    	System.out.println("Xprime");
    	System.out.println(Xprime);
    	System.out.println("Yprime");
    	System.out.println(Yprime);
    	
    	motorspeeds[RobotMap.frontLeftWheelnum] = Yprime + Z;
    	motorspeeds[RobotMap.frontRightWheelnum] = Xprime - Z;
    	motorspeeds[RobotMap.backLeftWheelnum] = Xprime  + Z;
    	motorspeeds[RobotMap.backRightWheelnum] = Yprime - Z;
    	
    	normalize(motorspeeds);
    	
    	return motorspeeds;
    	
    }

	private void normalize(double[] motorspeeds) {
		double currentmax = 0;
		for (int i=0; i<4; i++){
			if(Math.abs(motorspeeds[i])>=currentmax){
				currentmax = Math.abs(motorspeeds[i]);
			}
		}
		System.out.println(currentmax);
		for (int i=0; i<4; i++){
			motorspeeds[i] = motorspeeds[i] / currentmax;
		}
	}
}

