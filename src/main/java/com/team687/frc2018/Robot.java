package com.team687.frc2018;


import com.nerdherd.lib.logging.NerdyBadlog;
import com.nerdherd.lib.misc.AutoChooser;
import com.nerdherd.lib.motor.single.SingleMotorTalonSRX;
import com.nerdherd.lib.motor.single.mechanisms.SingleMotorArm;
import com.nerdherd.lib.pneumatics.Piston;
import com.team687.frc2018.subsystems.Drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;


public class Robot extends TimedRobot {

	public static final String kDate = "2019_01_11_";

    public static Drive drive;

	public static Piston claw;
	public static SingleMotorArm wrist;
	public static SingleMotorTalonSRX intake;
	public static SingleMotorArm arm;

    public static DriverStation ds;
    public static PowerDistributionPanel pdp;
	
	public static AutoChooser chooser;

    public static OI oi;

    SendableChooser<String> sideChooser;
    public static Command autonomousCommand;
    public static String startingPosition;
    public static boolean switchOnLeft, scaleOnLeft;
    SendableChooser<String> typeChooser;
    public static String autoType;

    @Override
    public void robotInit() {
	pdp = new PowerDistributionPanel();
	LiveWindow.disableTelemetry(pdp);
	LiveWindow.disableAllTelemetry();

	// intake = new Intake();
	// intake.setRollerPower(0);

	drive = new Drive();
	drive.resetEncoders();

	intake = new SingleMotorTalonSRX(RobotMap.kIntakeRollers1ID, "intake", false, true);
	wrist = new SingleMotorArm(RobotMap.kWristID, "wrist", true, true);
	wrist.configAngleConversion((1./4096.) * (12./30.) * 360., 117);
	wrist.configFFs(2.85, 0.35);
	wrist.configMotionMagic(2646, 2646);
	wrist.configPIDF(1.0, 0, 1.75, 0.3865);
	wrist.configTalonDeadband(0.004);
	claw = new Piston(RobotMap.kIntakeClawID1, RobotMap.kIntakeClawID2);

	arm = new SingleMotorArm(RobotMap.kArmID, "arm", false, false);
	arm.configAngleConversion((1./4096.) * (12./30.) * 360., 117);
	arm.configFFs(0, 0);
	arm.configMotionMagic(1540, 1540);
	arm.configPIDF(0, 0, 0, 0);
	arm.configTalonDeadband(0.004);
	oi = new OI();

	
	ds = DriverStation.getInstance();
	NerdyBadlog.initAndLog("/home/lvuser/logs/", "wrist_characterization", 0.02, wrist);

	// CameraServer.getInstance().startAutomaticCapture();
	}


	@Override
	public void robotPeriodic() {
		wrist.reportToSmartDashboard();
	}

    @Override
    public void disabledInit() {
	Scheduler.getInstance().removeAll();

	drive.reportToSmartDashboard();

	drive.stopLog();
	}

    @Override
    public void disabledPeriodic() {
		
		drive.reportToSmartDashboard();

	}
	@Override
    public void autonomousInit() {
	// Scheduler.getInstance().removeAll();

	

	 drive.startLog();
    }

    @Override
    public void autonomousPeriodic() {
	Scheduler.getInstance().run();

	drive.reportToSmartDashboard();

	 drive.logToCSV();
	}

    @Override
    public void teleopInit() {
	drive.reportToSmartDashboard();

	drive.startLog();
	}

    @Override
    public void teleopPeriodic() {
	Scheduler.getInstance().run();
	drive.reportToSmartDashboard();
	drive.logToCSV();
    }

    @Override
    public void testPeriodic() {
    }
}
