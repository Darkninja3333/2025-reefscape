// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Degree;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AlgaeCoralStand;

/** 2 neo one controls elevation and one closes pinchers * */
public class CoralClaw extends SubsystemBase {
  // pour les neo utiliser revlib et spark max ex: private CANSparkMax m_exemple = new
  // CANSparkMax(kidexemple);
  private SparkMax m_ElevationNeo = new SparkMax(0, MotorType.kBrushless);
  private SparkMax m_PinchNeo = new SparkMax(1, MotorType.kBrushless);
  
  public void IntakeSetAngle(double angle) {
    m_ElevationNeo.set(angle);
  }

  public void IntakeSetSpeed(double speed) {
    m_PinchNeo.set(speed);
  }

  @Override
  public void periodic() {
    
  }

public void intake() {
  // make a command that could intake the coral. :)
  if (Degree == 0) {
    m_ElevationNeo.getangle(90);
    m_PinchNeo.set(50);
  }
}
}
