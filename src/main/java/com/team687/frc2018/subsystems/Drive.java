package com.team687.frc2018.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.nerdherd.lib.drivetrain.singlespeed.Drivetrain;
import com.nerdherd.lib.drivetrain.teleop.ArcadeDrive;
import com.nerdherd.lib.misc.AutoChooser;
import com.team687.frc2018.Robot;
import com.team687.frc2018.RobotMap;
import com.team687.frc2018.constants.DriveConstants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class Drive extends Drivetrain {

	public Drive() {
		super(RobotMap.kLeftMasterTalonID, RobotMap.kRightMasterTalonID,
		 new VictorSPX[] {new VictorSPX(RobotMap.kLeftSlaveVictorID)},
		 new VictorSPX[] {new VictorSPX(RobotMap.kRightSlaveVictorID)},
		 false, true);
		 super.configAutoChooser(new AutoChooser());
		 super.configMaxVelocity(DriveConstants.kLeftCruiseVelocity);
		 super.configSensorPhase(true, true);
		 super.configTicksPerFoot(DriveConstants.kTicksPerFootLeft, DriveConstants.kTicksPerFootRight);
		 super.configLeftPIDF(DriveConstants.kLeftP, DriveConstants.kLeftI, DriveConstants.kLeftD, DriveConstants.kLeftF);
		 super.configRightPIDF(DriveConstants.kRightP, DriveConstants.kRightI, DriveConstants.kRightD, DriveConstants.kRightF);
		 super.configStaticFeedforward(DriveConstants.kLeftStatic, DriveConstants.kRightStatic);
		 super.configDate("2019_2_8_");
		}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive(Robot.drive, Robot.oi));
	}
}