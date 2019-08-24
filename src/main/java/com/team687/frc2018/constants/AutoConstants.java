package com.team687.frc2018.constants;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Config;
import jaci.pathfinder.Waypoint;

import java.util.ArrayList;
import java.util.Arrays;

import com.nerdherd.lib.drivetrain.trajectory.falconlib.Pose2D;
import com.nerdherd.lib.drivetrain.trajectory.falconlib.TrajectoryGen;
import com.nerdherd.lib.drivetrain.trajectory.falconlib.TrajectoryPoint;



public class AutoConstants {
    public static final double dt = 0.02;
    public static final double kAcceleration = 13;
    public static final double kCruiseVelocity = 13;
    public static final double kjerk = 100;

    private static Config testConfig = new Config(Trajectory.FitMethod.HERMITE_CUBIC, Config.SAMPLES_HIGH, dt, kCruiseVelocity/6, kAcceleration/6, kjerk);
    private static Waypoint[]testPoints = new Waypoint[] {
        new Waypoint(0, 0, 0),
        new Waypoint(5, 5, 0)
    };
    public static Trajectory testTraj = Pathfinder.generate(testPoints, testConfig);
    
    private static Waypoint[]BackwardsPoints = new Waypoint[] {
        new Waypoint(0, 0, 0),
        new Waypoint(-5, -5, 0)
    };
    public static Trajectory BackwardsTraj = Pathfinder.generate(BackwardsPoints, testConfig);
    
    private static TrajectoryGen gen = new TrajectoryGen();

    private static double kRightRobotOriginX = 8.144;
    private static double kRightRobotOriginY = 10.132;
    private static double kRightCargoPathOnePointTwoX = 16.45;
    private static double kRightCargoPathOnePointTwoY = 2.27;
    private static double kCentripetalAcceleration = 253;
    

    public static ArrayList<TrajectoryPoint> RightRocketPath1 = gen.generateTrajectory(
        Arrays.asList(new Pose2D(kRightRobotOriginX, kRightRobotOriginY, 0).pose,
        new Pose2D(kRightCargoPathOnePointTwoX, kRightCargoPathOnePointTwoY, 90).pose), 
        kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

}