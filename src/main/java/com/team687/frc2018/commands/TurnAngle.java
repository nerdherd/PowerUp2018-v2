/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.frc2018.commands;

import com.nerdherd.lib.drivetrain.singlespeed.AbstractDrivetrain;
// import com.nerdherd.lib.misc.NerdyMath;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;

/**
 * Turn to a specified angle (no vision, absolute)
 * @author tedklin
 */

public class TurnAngle extends Command {

    private double m_desiredAngle;
    private double m_startTime, m_timeout;
    private double m_error;
    private double m_prevTimestamp;
    private double m_prevError;
    private double m_dTerm, m_rotP, m_rotD;
    private AbstractDrivetrain m_drive;

    private int m_counter;
    private int m_tolerance;

    /**
     * @param angle
     * @param timeout
     * @param tolerance
     */
    public TurnAngle(AbstractDrivetrain drive, double angle, int tolerance, double timeout, double rotP, double rotD) {
        m_drive = drive;
        m_desiredAngle = angle;
        m_tolerance = tolerance;
        m_rotP = rotP;
        m_rotD = rotD;
        m_timeout = timeout;
	    requires(m_drive);
    }

    @Override
    protected void initialize() {
	SmartDashboard.putString("Current Drive Command", "TurnToAngle");
	m_startTime = Timer.getFPGATimestamp();
	m_counter = 0;
    }

    @Override
    protected void execute() {
	// double robotAngle = (360 - m_drive.getRawYaw()) % 360;
    // m_error = -m_desiredAngle - robotAngle;
    m_error = Pathfinder.boundHalfDegrees(m_desiredAngle - m_drive.getRawYaw());
	// m_error = (m_error > 180) ? m_error - 360 : m_error;
	// m_error = (m_error < -180) ? m_error + 360 : m_error;

	m_dTerm = (m_prevError - m_error) / (m_prevTimestamp - Timer.getFPGATimestamp());
	m_prevTimestamp = Timer.getFPGATimestamp();

	double power = m_rotP * m_error + m_rotD * m_dTerm;

	m_drive.setPowerFeedforward(-power, power);
	if (Math.abs(m_error) <= 2) {
	    m_counter += 1;
	} else {
	    m_counter = 0;
	}
	m_prevError = m_error;
    }

    @Override
    protected boolean isFinished() {
	return m_counter > m_tolerance || Timer.getFPGATimestamp() - m_startTime > m_timeout;
	// return false;
    }

    @Override
    protected void end() {
	m_drive.setPowerZero();
    }

    @Override
    protected void interrupted() {
	end();
    }

}
