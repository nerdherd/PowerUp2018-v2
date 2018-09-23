/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.frc2018.commands.intake;

import com.team687.frc2018.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetIntakePowerFromDashboard extends Command {

    private double m_voltageToSet;

    public SetIntakePowerFromDashboard() {
        requires(Robot.intake);
    }

    @Override
    protected void initialize() {
        // Defaults to zero if it can't find the correct thing
        m_voltageToSet = SmartDashboard.getNumber("Intake voltage to set", 0);
        SmartDashboard.putString("Current Command", "SetPowerFromDashboard");
    }

    @Override
    protected void execute() {
        Robot.intake.setRollerPower(m_voltageToSet / 12.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.intake.setRollerPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
