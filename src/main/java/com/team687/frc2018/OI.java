package com.team687.frc2018;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.nerdherd.lib.motor.commands.SetMotorPower;
import com.team687.frc2018.commands.drive.auto.DriveDistanceMotionMagic;
import com.team687.frc2018.commands.drive.auto.ResetDriveEncoders;
import com.team687.frc2018.commands.drive.characterization.DriveCharacterizationTest;
import com.team687.frc2018.commands.intake.ClawClose;
import com.team687.frc2018.commands.intake.ClawOpen;
import com.team687.frc2018.commands.superstructure.AdjustForwardsScale;
import com.team687.frc2018.commands.superstructure.DefaultIntake;
import com.team687.frc2018.commands.superstructure.DefaultStow;
import com.team687.frc2018.commands.superstructure.IntakeSequenceCurrent;
import com.team687.frc2018.commands.superstructure.StowToForwardsScale;
import com.team687.frc2018.commands.superstructure.SwitchScorePositionTeleop;
import com.team687.frc2018.constants.SuperstructureConstants;
import com.team687.frc2018.utilities.NerdyMath;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {

    private String m_filePath1 = "/media/sda1/logs/";
    private String m_filePath2 = "/home/lvuser/logs/";
    private File m_file;
    public FileWriter m_writer;
    private boolean writeException = false;
    private double m_logStartTime;

    public Joystick driveJoyLeft = new Joystick(0);
    public Joystick driveJoyRight = new Joystick(1);
    public Joystick driveJoyArtic = new Joystick(2);

    // public Joystick gamepadJoy = new Joystick(0);

    
    public JoystickButton intake_1;
    public JoystickButton outtake_2;
    public JoystickButton stopIntake_3;
    public JoystickButton openCloseClaw_4;
    // public JoystickButton intakePosition_4;

    public JoystickButton switchPosition_11;
    public JoystickButton intakeRollers_9;
    public JoystickButton stowToForwards_7;
    public JoystickButton adjustMiddle_8;
    public JoystickButton defaultStow_10;
    
    public JoystickButton openClaw_6;
    public JoystickButton closeClaw_5;
    
    // public JoystickButton flipCube_12;
    public JoystickButton backupStow_12;
    
    // public JoystickButton sketchyStowToBackwards_12;

    public OI() {
        // SmartDashboard.putData("Intake 3 volts", new SetMotorPower(Robot.intake, 0.5));
        // SmartDashboard.putData("Open Claw", new ExtendPiston(Robot.claw));
        // SmartDashboard.putData("Close Claw", new RetractPiston(Robot.claw));
        // SmartDashboard.putData("Reset Arm Encoder", new ResetArmEncoder());
        // SmartDashboard.putData("Reset Wrist Encoder", new ResetWristEncoder());
        // SmartDashboard.putData("Stow", new DefaultStow());
        SmartDashboard.putData("Reset Drive Encoders", new ResetDriveEncoders());
        // SmartDashboard.putData("Reset Gyro", new ResetGyro());
        // SmartDashboard.putData("Intake 3V ", new SetMotorPower(Robot.intake, .25));
        // SmartDashboard.putData("Intake 6V ", new SetMotorPower(Robot.intake, .5));
        // SmartDashboard.putData("Intake 9V ", new SetMotorPower(Robot.intake, 0.75));
        // SmartDashboard.putData("Intake 12V ", new SetMotorPower(Robot.intake, 1));
        // SmartDashboard.putData("Intake -3V ", new SetMotorPower(Robot.intake, -0.25));

        SmartDashboard.putData("Drive Characterization", new DriveCharacterizationTest(0.25));
        // SmartDashboard.putData("6 V open loop", new OpenLoopDrive(0.5));
        SmartDashboard.putData("Drive Motion Magic", new DriveDistanceMotionMagic(15000, 500, 500));
        // SmartDashboard.putData("Drive Trajectory", new DriveTrajectory(AutoConstants.testTraj, 3, true, 0.3, 0));
        // SmartDashboard.putData("Backwards Trajectory", new DriveTrajectory(AutoConstants.BackwardsTraj, 3, false, 0.3, 0));

        
        intake_1 = new JoystickButton(driveJoyArtic, 1);
        intake_1.whenPressed(new DefaultIntake());
        
        outtake_2 = new JoystickButton(driveJoyArtic, 2);
        outtake_2.whenPressed(new SetMotorPower(Robot.intake, -0.5));
        stopIntake_3 = new JoystickButton(driveJoyArtic, 3);
        stopIntake_3.whenPressed(new SetMotorPower(Robot.intake, 0));
        openCloseClaw_4 = new JoystickButton(driveJoyArtic, 4);
        openCloseClaw_4.whenPressed(new IntakeSequenceCurrent());
        // intakePosition_4 = new JoystickButton(driveJoyArtic, 4);
        // intakePosition_4.whenPressed(new DefaultIntake());

        intakeRollers_9 = new JoystickButton(driveJoyArtic, 9);
        intakeRollers_9.whenPressed(new SetMotorPower(Robot.intake, .8333333333333333));
    //	intakeRollers_9.whenPressed(new StackCubes(0));
        stowToForwards_7 = new JoystickButton(driveJoyArtic, 7);
        
        stowToForwards_7.whenPressed(new StowToForwardsScale());
    //	stowToForwards_7.whenPressed(new StackCubes(-15));
        
        adjustMiddle_8 = new JoystickButton(driveJoyArtic, 8);
        adjustMiddle_8.whenPressed(new AdjustForwardsScale(SuperstructureConstants.kArmMiddleScalePosition));
        defaultStow_10 = new JoystickButton(driveJoyArtic, 10);
        defaultStow_10.whenPressed(new DefaultStow());
        switchPosition_11 = new JoystickButton(driveJoyArtic, 11);
        switchPosition_11.whenPressed(new SwitchScorePositionTeleop());
    //	switchPosition_11.whenPressed(new StackCubes(35));

        openClaw_6 = new JoystickButton(driveJoyArtic, 5);
        openClaw_6.whenPressed(new ClawOpen());
        closeClaw_5 = new JoystickButton(driveJoyArtic, 6);
        closeClaw_5.whenPressed(new ClawClose());
        // flipCube_12 = new JoystickButton(driveJoyArtic, 12);
        // flipCube_12.whenPressed(new FlipCube());
        backupStow_12 = new JoystickButton(driveJoyArtic, 12);
        backupStow_12.whenPressed(new DefaultStow());

    }

    /**
     * @return input power from left drive joystick Y (-1.0 to +1.0)
     */
    public double getDriveJoyLeftY() {
	// return -gamepadJoy.getRawAxis(1);
	return -driveJoyLeft.getY();
    }

    /**
     * @return input power from right drive joystick Y (-1.0 to +1.0)
     */
    public double getDriveJoyRightY() {
	// return -gamepadJoy.getRawAxis(3);
	return -driveJoyRight.getY();
    }

    /**
     * @return input power from left drive joystick X (-1.0 to +1.0)
     */
    public double getDriveJoyLeftX() {
	// return gamepadJoy.getRawAxis(0);
	return driveJoyLeft.getX();
    }

    /**
     * @return input power from right drive joystick X (-1.0 to +1.0)
     */
    public double getDriveJoyRightX() {
	// return gamepadJoy.getRawAxis(2);
	return driveJoyRight.getX();
    }
    
    /**
     * @return input power from artic joy
     */
    public double getArticJoyY() {
    	return driveJoyArtic.getY();
    }

    /**
     * @return button selected
     */
    public boolean getArticButton(int buttonNumber) {
        return driveJoyArtic.getRawButton(buttonNumber);
    }

    /**
     * @return input throttle from right drive joystick (0 to +1.0)
     */
    public double getThrottleR() {
	return (driveJoyRight.getThrottle() + 1) / 2;
    }

    /**
     * @return input throttle from left drive josytick
     */
    public double getThrottleL() {
	return (driveJoyLeft.getThrottle() + 1) / 2;
    }

    public void startLog() {
        writeException = false;
        // Check to see if flash drive is mounted.
        File logFolder1 = new File(m_filePath1);
        File logFolder2 = new File(m_filePath2);
        Path filePrefix = Paths.get("");
        if (logFolder1.exists() && logFolder1.isDirectory()) {
            filePrefix = Paths.get(logFolder1.toString(),
                Robot.kDate + Robot.ds.getMatchType().toString() + Robot.ds.getMatchNumber() + "OI");
        } else if (logFolder2.exists() && logFolder2.isDirectory()) {
            filePrefix = Paths.get(logFolder1.toString(),
                Robot.kDate + Robot.ds.getMatchType().toString() + Robot.ds.getMatchNumber() + "OI");
        } else {
            writeException = true;
        }
    
        if (!writeException) {
            int counter = 0;
            while (counter <= 99) {
            m_file = new File(filePrefix.toString() + String.format("%02d", counter) + ".csv");
            if (m_file.exists()) {
                counter++;
            } else {
                break;
            }
            if (counter == 99) {
                System.out.println("file creation counter at 99!");
            }
            }
            try {
            m_writer = new FileWriter(m_file);
            m_writer.append("Time,Button 1,Button 2,Button 3,Button 4,Button 5,Button 6," +
            "Button 7,Button 8,Button 9,Button 10,Button 11,Button 12,Left X, Left Y, Right X, Right Y\n");
            m_writer.flush();
            m_logStartTime = Timer.getFPGATimestamp();
            } catch (IOException e) {
            e.printStackTrace();
            writeException = true;
            }
        }
        }
    
        public void stopLog() {
        try {
            if (null != m_writer)
            m_writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            writeException = true;
        }
        }
    
        public void logToCSV() {
        if (!writeException) {
            try {
            double timestamp = Timer.getFPGATimestamp() - m_logStartTime;
            m_writer.append(String.valueOf(timestamp) + "," + NerdyMath.boolToInt(getArticButton(1)) + ","
                + NerdyMath.boolToInt(getArticButton(2)) + "," + NerdyMath.boolToInt(getArticButton(3)) + ","
                + NerdyMath.boolToInt(getArticButton(4)) + "," + NerdyMath.boolToInt(getArticButton(5)) + ","
                + NerdyMath.boolToInt(getArticButton(6)) + "," + NerdyMath.boolToInt(getArticButton(7)) + ","
                + NerdyMath.boolToInt(getArticButton(8)) + "," + NerdyMath.boolToInt(getArticButton(9)) + ","
                + NerdyMath.boolToInt(getArticButton(10)) + "," + NerdyMath.boolToInt(getArticButton(11)) + ","
                + NerdyMath.boolToInt(getArticButton(12)) + "," + String.valueOf(getDriveJoyLeftX()) + ","
                + String.valueOf(getDriveJoyLeftY()) + "," + String.valueOf(getDriveJoyRightX()) + ","
                + String.valueOf(getDriveJoyRightX()) + ","
                + String.valueOf(SmartDashboard.getString("Current Intake Command", "None")) + "\n");
    //		m_writer.flush();
            } catch (IOException e) {
            e.printStackTrace();
            writeException = true;
            }
        }
        }

}
