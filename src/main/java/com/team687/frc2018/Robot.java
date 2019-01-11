package com.team687.frc2018;


import com.team687.frc2018.constants.DriveConstants;
import com.team687.frc2018.constants.SuperstructureConstants;
import com.team687.frc2018.subsystems.Arm;
import com.team687.frc2018.subsystems.Drive;
import com.team687.frc2018.subsystems.Intake;
import com.team687.frc2018.subsystems.Wrist;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

    public static final String kDate = "2018_10_12_";

    public static Drive drive;
    public static Arm arm;
    public static Wrist wrist;
    public static Intake intake;

    public static DriverStation ds;
    public static PowerDistributionPanel pdp;
    public static Compressor compressor;

    public static OI oi;

    SendableChooser<String> sideChooser;
    public static Command autonomousCommand;
    public static String startingPosition;
    public static boolean switchOnLeft, scaleOnLeft;
    SendableChooser<String> typeChooser;
    public static String autoType;

    @Override
    public void robotInit() {
    CameraServer.getInstance().startAutomaticCapture();
	pdp = new PowerDistributionPanel();
	LiveWindow.disableTelemetry(pdp);
	compressor = new Compressor();
	compressor.start();

	arm = new Arm();
	arm.setVoltage(0);
	arm.resetEncoder();

	wrist = new Wrist();
	wrist.setPercentOutput(0);
	wrist.resetEncoder();

	intake = new Intake();
	intake.setRollerPower(0);

	drive = new Drive();
	drive.resetEncoders();

	oi = new OI();
	ds = DriverStation.getInstance();

	// CameraServer.getInstance().startAutomaticCapture();
	}



    @Override
    public void disabledInit() {
	Scheduler.getInstance().removeAll();

	drive.reportToSmartDashboard();
	arm.reportToSmartDashboard();
	wrist.reportToSmartDashboard();
	intake.reportToSmartDashboard();

	SmartDashboard.putBoolean("HEALTHY SUPERSTRUCTURE CURRENT",
		!(arm.getCurrent() > SuperstructureConstants.kArmSafeCurrent
			|| wrist.getCurrent() > SuperstructureConstants.kWristSafeCurrent));

	drive.stopLog();
	arm.stopLog();
    wrist.stopLog();
    oi.stopLog();
    intake.stopLog();
    }

    @Override
    public void disabledPeriodic() {

	drive.reportToSmartDashboard();
	arm.reportToSmartDashboard();
	wrist.reportToSmartDashboard();
	intake.reportToSmartDashboard();

	}
	@Override
    public void autonomousInit() {
	// Scheduler.getInstance().removeAll();

	

	 drive.startLog();
	 arm.startLog();
     wrist.startLog();
     oi.startLog();
     intake.startLog();
    }

    @Override
    public void autonomousPeriodic() {
	Scheduler.getInstance().run();

	drive.reportToSmartDashboard();
	arm.reportToSmartDashboard();
	wrist.reportToSmartDashboard();
	intake.reportToSmartDashboard();

	SmartDashboard.putBoolean("HEALTHY SUPERSTRUCTURE CURRENT",
		!(arm.getCurrent() > SuperstructureConstants.kArmSafeCurrent
			|| wrist.getCurrent() > SuperstructureConstants.kWristSafeCurrent));

	 drive.logToCSV();
	 arm.logToCSV();
     wrist.logToCSV();
     oi.logToCSV();
     intake.logToCSV();
    }

    @Override
    public void teleopInit() {
	drive.reportToSmartDashboard();
	arm.reportToSmartDashboard();
	wrist.reportToSmartDashboard();
	intake.reportToSmartDashboard();

	SmartDashboard.putBoolean("HEALTHY SUPERSTRUCTURE CURRENT",
		!(arm.getCurrent() > SuperstructureConstants.kArmSafeCurrent
			|| wrist.getCurrent() > SuperstructureConstants.kWristSafeCurrent));
	compressor.start();
	drive.startLog();
	arm.startLog();
    wrist.startLog();
    oi.startLog();
    intake.startLog();
    }

    @Override
    public void teleopPeriodic() {
	Scheduler.getInstance().run();
	compressor.start();
	drive.reportToSmartDashboard();
	arm.reportToSmartDashboard();
	wrist.reportToSmartDashboard();
	intake.reportToSmartDashboard();

	SmartDashboard.putBoolean("HEALTHY SUPERSTRUCTURE CURRENT",
		!(arm.getCurrent() > SuperstructureConstants.kArmSafeCurrent
			|| wrist.getCurrent() > SuperstructureConstants.kWristSafeCurrent));

	drive.logToCSV();
	arm.logToCSV();
    wrist.logToCSV();
    oi.logToCSV();
    intake.logToCSV();

	if (ds.getMatchTime() < 5) {
	    Robot.wrist.enableBrakeMode();
	}
    }

    @Override
    public void testPeriodic() {
    }
}
