/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveByTime extends Command {
    private int time;
    private int count = 0;
    private int timeout;
    private double power;

    public DriveByTime(double power, int time) {
        // requires(Robot.driveStraightPID);
        // requires(Robot.driveDistancePID);
        requires(Robot.driveSubsystem);
        this.time = time;
        this.power = power;
        // Robot.driveStraightPID.disable();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        // count = 0;
        // RobotMap.gyro.reset();
        // RobotMap.leftEncoder.reset();
        // RobotMap.rightEncoder.reset();
        Robot.driveSubsystem.drive(power, power);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        count++;
        // if(count == 5){ //100 ms to reset, need test
        // Robot.driveStraightPID.setSetpoint(0); //drive straight
        // Robot.driveStraightPID.enable();
        // }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if (count >= time) {
            return true;
        } else {
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