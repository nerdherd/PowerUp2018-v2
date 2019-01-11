package com.team687.frc2018.constants;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Config;
import jaci.pathfinder.Waypoint;

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

}