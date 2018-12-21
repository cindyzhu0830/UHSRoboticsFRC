/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveSubsystem;

public class DriveTurn extends Command {
    private int angle;
    private double power;
    private int countAngle = 0;

    public DriveTurn(double power, int angle) {//input true to turn left and false to turn right
        // requires(Robot.driveStraightPID);
        // requires(Robot.driveDistancePID);
        requires(Robot.driveSubsystem);
        this.angle = angle;
        this.power = power;
        // Robot.driveStraightPID.disable();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        countAngle = (int) (angle - RobotMap.gyro.getAngle());
        if(countAngle >= 0){
            Robot.driveSubsystem.drive(this.power, 0);
        }
        else{
            Robot.driveSubsystem.drive(0, this.power);
        }

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
      
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if((RobotMap.gyro.getAngle() >= this.angle && countAngle > 0)||(RobotMap.gyro.getAngle() <= this.angle && countAngle < 0) ){
            return true;
        }
        else{
            return false;
        }
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        // Robot.driveStraightPID.disable();
        Robot.driveSubsystem.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}