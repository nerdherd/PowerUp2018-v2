package com.team687.frc2018;

import static org.junit.Assert.assertArrayEquals;

import com.nerdherd.lib.motor.commands.MotorVoltageRamping;
import com.nerdherd.lib.motor.commands.ResetSingleMotorEncoder;
import com.nerdherd.lib.motor.commands.mechanisms.MechanismVoltageRampingWithFF;
import com.nerdherd.lib.motor.commands.mechanisms.SetArmAngleMotionMagic;
import com.nerdherd.lib.oi.DefaultOI;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI extends DefaultOI {

    public OI() {
        super();
        // SmartDashboard.putData("Wrist Ramp Up", new MechanismVoltageRampingWithFF(Robot.wrist, 0.25/12.0));
        // SmartDashboard.putData("Wrist Ramp Down", new MechanismVoltageRampingWithFF(Robot.wrist, -0.25/12.0));
        // SmartDashboard.putData("Set Wrist Angle 15", new SetArmAngleMotionMagic(Robot.wrist, 15));
        // SmartDashboard.putData("Reset Wrist Encoder", new ResetSingleMotorEncoder(Robot.wrist));
        // SmartDashboard.putData("Set Wrist Angle 75", new SetArmAngleMotionMagic(Robot.wrist, 75));
        // SmartDashboard.putData("Set Wrist Angle 45", new SetArmAngleMotionMagic(Robot.wrist, 45));
        // SmartDashboard.putData("Set Wrist Angle 90", new SetArmAngleMotionMagic(Robot.wrist, 90));
        // SmartDashboard.putData("Set Wrist Angle 0", new SetArmAngleMotionMagic(Robot.wrist, 0));

        SmartDashboard.putData("Arm Ramp Up", new MotorVoltageRamping(Robot.arm, 0.25/12.0));
        SmartDashboard.putData("Arm Ramp Down", new MotorVoltageRamping(Robot.arm, -0.25/12.0));
        SmartDashboard.putData("Reset Arm Encoder", new ResetSingleMotorEncoder (Robot.arm));
        
    }

}
