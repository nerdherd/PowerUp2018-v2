package com.team687.frc2018.commands.auto;

import com.team687.frc2018.commands.drive.DriveAtHeading;
import com.team687.frc2018.commands.drive.DriveStraightDistance;
import com.team687.frc2018.commands.drive.ResetDriveEncoders;
import com.team687.frc2018.commands.drive.TurnTime;
import com.team687.frc2018.commands.drive.TurnToAngle;
import com.team687.frc2018.commands.drive.WaitTime;
import com.team687.frc2018.commands.intake.ClawClose;
import com.team687.frc2018.commands.intake.ClawOpen;
import com.team687.frc2018.commands.intake.OuttakeRollers;
import com.team687.frc2018.commands.superstructure.BackwardsScaleToStow;
import com.team687.frc2018.commands.superstructure.DefaultIntake;
import com.team687.frc2018.commands.superstructure.DefaultStow;
import com.team687.frc2018.commands.superstructure.IntakeSequenceCurrent;
import com.team687.frc2018.commands.superstructure.StowToBackwardsScale;
import com.team687.frc2018.constants.AutoConstants;
import com.team687.frc2018.utilities.NerdyMath;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightToRightScale2CubeAuto extends CommandGroup {

    public RightToRightScale2CubeAuto() {
	// doot doot doot
	addParallel(new DefaultStow());
	// addSequential(new DriveAtHeading(-0.4, 180,
	// NerdyMath.inchesToTicks(0.05 *AutoConstants.kRedStartingWallToSwitchInches),
	// 0.004));
	addSequential(new DriveAtHeading(-0.9, 180,
		NerdyMath.inchesToTicks(1 * AutoConstants.kRedStartingWallToSwitchInches), 0.004));

	// curve to scale and score
	addParallel(new StowToBackwardsScale());

	addSequential(new DriveAtHeading(-0.6, 150,
		NerdyMath.inchesToTicks(
			0.8 * AutoConstants.kRedStartingWallToSwitchInches + 0.5 * AutoConstants.kRedLeftSwitchToFrontScale),
		0.002));
	addSequential(new DriveAtHeading(-0.3, 150,
		NerdyMath.inchesToTicks(
			0.8 * AutoConstants.kRedStartingWallToSwitchInches + 1.3 * AutoConstants.kRedLeftSwitchToFrontScale),
		0.004));

    addParallel(new OuttakeRollers(0.6));
	addSequential(new WaitTime(1));

	// stow and turn
	addParallel(new BackwardsScaleToStow());
	 addSequential(new WaitTime(0.5));
	addSequential(new TurnToAngle(30, 3, 2));
	addSequential(new ResetDriveEncoders());
//
//	// get second cube
    // addSequential(new ClawOpen());
    addParallel(new DefaultIntake());
	addSequential(
		new DriveStraightDistance(0.9 * NerdyMath.inchesToTicks(AutoConstants.kRobotToSecondCubeScale), 35, 2, 0.5));
    // addSequential(new TurnToAngle(20, 1, 2));    
        // addSequential(new ClawClose());
        addSequential(new WaitTime(0.2));
    addSequential(new ResetDriveEncoders());
    // addSequential(new IntakeSequenceCurrent());
    // addSequential(new TurnTime(0.3, 0.5));
	addSequential(new DriveStraightDistance(0.9 * -NerdyMath.inchesToTicks(AutoConstants.kRobotToSecondCubeScale),
		-165, 2, 0.5));

	addParallel(new DefaultStow());
	addParallel(new TurnToAngle(-35, 2, 2));
	// addSequential(new ResetDriveEncoders());
	// addSequential(new DriveStraightDistance(NerdyMath.inchesToTicks(-10), 150, 2,
	// 0.5));

    // score second cube and stow
    
	addParallel(new StowToBackwardsScale());
	addSequential(new WaitTime(2));
	// addSequential(new DriveTime(-0.5, 0.3));
	addParallel(new OuttakeRollers(0.75));
	addSequential(new WaitTime(0.8));
	// addSequential(new DriveTime(0.5, 0.3));
	addParallel(new BackwardsScaleToStow());
    }

}