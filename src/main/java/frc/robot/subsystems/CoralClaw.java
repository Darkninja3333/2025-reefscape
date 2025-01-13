// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
/** 2 neo one controls elevation and one closes pinchers * */
public class CoralClaw extends SubsystemBase {
  // pour les neo utiliser revlib et spark max ex: private CANSparkMax m_exemple = new
  // CANSparkMax(kidexemple);
  SparkMax m_ElevationNeo = new SparkMax(0, SparkMax.MotorType.kBrushless);
  public CoralClaw() {
// initialize the NEOs of/and the Claw of the robot. :D
    
}

  @Override
  public void periodic() {

  }

  public void intake() {
// make a command that could intake the coral. :)
  }
}
