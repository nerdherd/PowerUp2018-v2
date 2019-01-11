package com.team687.frc2018.commands.superstructure;

import com.team687.frc2018.Robot;
import com.team687.frc2018.constants.SuperstructureConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DefaultStow extends Command {
	
	private boolean m_isBackwards;

    public DefaultStow() {
        requires(Robot.arm);
        requires(Robot.wrist);
        requires(Robot.intake);
    }

    @Override
    protected void initialize() {
	    SmartDashboard.putString("Current Command", "DefaultStow");
    }

    @Override
    protected void execute() {
        if (Robot.arm.getState() == SuperstructureConstants.kBackwardsScaleState) {
            //Robot.intake.setRollerPower(0);
            Robot.wrist.setPosition(SuperstructureConstants.kWristStowArmOffsetPos);
            // in comp-bot branch, - 200 is + 200
            if (Robot.wrist.getPosition() > SuperstructureConstants.kWristStowArmOffsetPos - 200) {
                Robot.arm.setPosition(SuperstructureConstants.kArmOffsetPos);
            }
        } else {
            //Robot.intake.setRollerPower(-0.1254); // hold cube in place as we go up
            Robot.arm.setPosition(SuperstructureConstants.kArmOffsetPos);
            Robot.wrist.setAngleAbsolute(90);
            
            // Robot.wrist.setPosition(SuperstructureConstants.kWristStowPos);
            // Robot.intake.closeClaw();
        }
        if (Math.abs(Robot.arm.getPosition() - SuperstructureConstants.kArmOffsetPos) < 
            SuperstructureConstants.kAcceptableErrorForStates) {
            
            Robot.arm.setState(SuperstructureConstants.kStowState);
        }
    }

    @Override
    protected boolean isFinished() {
	return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
	end();
    }

}
