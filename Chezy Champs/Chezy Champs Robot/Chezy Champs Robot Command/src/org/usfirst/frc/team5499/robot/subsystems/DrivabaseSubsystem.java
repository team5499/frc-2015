package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

public class DrivabaseSubsystem extends Subsystem{
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final CANTalon RIGHT_MOTOR_F = new CANTalon(RobotMap.RIGHT_MOTOR_F_IND);
	private final CANTalon RIGHT_MOTOR_B = new CANTalon(RobotMap.RIGHT_MOTOR_B_IND);
	private final CANTalon LEFT_MOTOR_F = new CANTalon(RobotMap.LEFT_MOTOR_F_IND);
	private final CANTalon LEFT_MOTOR_B = new CANTalon(RobotMap.LEFT_MOTOR_B_IND);
	
	private Timer timer = new Timer();
	
	//private I2C gyroChannel = new I2C(Port.kOnboard, 110101);
	
	//public Gyro gyro = new Gyro(/*RobotMap.GYRO_CH*/ I2C.Port.kOnboard.getValue());
	
	//Implement correction based on the accelerometer for now since implementation is
	//easier. Try implementing gyro afterwards.
	Accelerometer accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);
	
	//private final double KP = 0.03; //proportional gain
	
	private double Kp = 0.1;
	private double Ki = 0.001;
	private double Kd = 0.0;
	
	private double p;
	private double i;
	private double d;	
	private double f = 0.0;
	private int izone = 0;
	private double closeLoopRampRate = 27;
	private int profile = 1;
	
	private double xValTarget = 0;
	private double yValTarget = 0;
	//private double zValTarget = 0;
	
	/*private PIDController pidR_F = new PIDController(Kp, Ki, Kd, accel, RIGHT_MOTOR_F);
	private PIDController pidR_B = new PIDController(Kp, Ki, Kd, gyro, RIGHT_MOTOR_B);
	private PIDController pidL_F = new PIDController(Kp, Ki, Kd, gyro, LEFT_MOTOR_F);
	private PIDController pidL_B = new PIDController(Kp, Ki, Kd, gyro, LEFT_MOTOR_B);*/
	
	

	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		/*pidR_F.enable();
		pidR_B.enable();
		pidL_F.enable();
		pidL_B.enable();*/
	}
	public void updatePIDVals(){
		boolean isChanged = false;
		
		double xVal = accel.getX();
		double yVal = accel.getY();
		//double zVal = accel.getZ();
		
		double xError = xValTarget - xVal;
		double yError = yValTarget - yVal;
		//double zError = zValTarget - zVal;
		
		if(Math.abs(xError) > 0){
			//p = error * p;			
		} else if(Math.abs(yError) > 0){
			
		} /*else if(Math.abs(zError) > 0){
			
		}*/
		
		if(isChanged)
			updatePID();
		
		
	}

	public void move(){
		RIGHT_MOTOR_F.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.RIGHT_STICK_Y));
		RIGHT_MOTOR_B.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.RIGHT_STICK_Y));
		
		LEFT_MOTOR_F.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.LEFT_STICK_Y));
		LEFT_MOTOR_B.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.LEFT_STICK_Y));
	}
	
	/**
	 * 
	 * @param time time for the robot to move in seconds
	 * @param degrees direction of the move in degrees with forward being 0, right being 90
	 * @param speed speed with which to move 0 - 100
	 */
	public void timedMove(int time, int angle, int speed){
		timer.start();
		
		double rightSpeed = Math.sin(angle * Math.PI/180) * speed - Math.sin(angle * Math.PI/180) * speed;
		double leftSpeed = Math.cos(angle * Math.PI/180) * speed + Math.sin(angle * Math.PI/180) * speed;
		
		
		while(timer.get() < time){
			RIGHT_MOTOR_F.set(rightSpeed);
			RIGHT_MOTOR_B.set(rightSpeed);
			
			LEFT_MOTOR_F.set(leftSpeed);
			LEFT_MOTOR_B.set(leftSpeed);
		}
		
	}
	
	private void updatePID(){
		RIGHT_MOTOR_F.setPID(p, i, d, f, izone, closeLoopRampRate, profile);
		RIGHT_MOTOR_B.setPID(p, i, d, f, izone, closeLoopRampRate, profile);
		LEFT_MOTOR_F.setPID(p, i, d, f, izone, closeLoopRampRate, profile);
		LEFT_MOTOR_B.setPID(p, i, d, f, izone, closeLoopRampRate, profile);
	}
	
	private void setAccelTargets(int xAxis, int yAxis){
	}
}
