/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.frc2018.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team687.frc2018.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
* Add your docs here.
*/
public class Climber extends Subsystem {
    
    private final TalonSRX m_climber1;
    private final TalonSRX m_climber2;
    private final DoubleSolenoid m_climberPiston;
    
    public Climber() {
        m_climber1 = new TalonSRX(RobotMap.kClimber1ID);
        m_climber2 = new TalonSRX(RobotMap.kClimber2ID);

        m_climber1.setInverted(true);
        m_climber2.setInverted(false);

        m_climber1.setSensorPhase(true);
        m_climber2.setSensorPhase(false);

        m_climber1.setNeutralMode(NeutralMode.Brake);
        m_climber2.setNeutralMode(NeutralMode.Brake);

        m_climberPiston = new DoubleSolenoid(RobotMap.kClimberPiston1ID, RobotMap.kClimberPiston2ID);
    }

    public void setClimberPower(double power) {
        m_climber1.set(ControlMode.PercentOutput, power);
        m_climber2.set(ControlMode.PercentOutput, power);
    }

    public void retractClimberPiston() {
        m_climberPiston.set(Value.kForward);
    }

    public void extendClimberPiston() {
        m_climberPiston.set(Value.kReverse);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
