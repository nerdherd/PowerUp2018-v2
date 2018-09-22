/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.frc2018.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**
* Add your docs here.
*/
public class Climber extends Subsystem {
    
    private final TalonSRX climber1;
    private final TalonSRX climber2;
    
    public Climber() {
        climber1 = new TalonSRX(RobotMap.kClimber1ID);
        climber2 = new TalonSRX(RobotMap.kClimber1ID);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
