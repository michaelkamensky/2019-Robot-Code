/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class Robot extends TimedRobot {
  
  private HatchGrabber hatchGrabber;
  private DriveControlManager dcm;
  private Drivetrain dtrain;
  private Arm arm;
  @Override
  public void robotInit() {
    dcm = new DriveControlManager(); // DCM contains all driver configurations and input
    dtrain = new Drivetrain(11, 12, 9, 10); // initialize drivetrain with given TalonSRX indices
    arm = new Arm(1); // initialize Arm with TalonSRX index
    hatchGrabber = new HatchGrabber(0, 1); // initialize Hatch Grabber

    CameraServer.getInstance().startAutomaticCapture(); // give dashboard camera feed
  }

  public void teleopPeriodic() {

    dtrain.drive(dcm.getLeftVelocity(), dcm.getRightVelocity(), dcm.shouldEnterOrExit());
    arm.actuate(dcm.getArmVelocity(), dcm.shouldFreezeArm());

    if(dcm.shouldGrab()) {
      hatchGrabber.grab();
    } else if(dcm.shouldRelease()) {
      hatchGrabber.release();
    }

    dcm.updateSquat();
  }

  public void autonomousPeriodic() {

  }
  
}
//parth was here