// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants.AlgaeCoralStand;
import frc.robot.Constants;

/** 2 neo one controls elevation and one closes pinchers * */
public class CoralClaw extends SubsystemBase {
  public enum ClawState {
    OPEN,
    CLOSE
  }

  public enum ClawPosition {
    HANDOFF,
    INTAKE
  }

  // pour les neo utiliser revlib et spark max ex: private CANSparkMax m_exemple =
  // new
  // CANSparkMax(kidexemple);
  private SparkMax m_ElevationNeo = new SparkMax(0, MotorType.kBrushless);
  private SparkMax m_PinchNeo = new SparkMax(1, MotorType.kBrushless);
  private double kp = 0.1;
  private double ki = 0.0;
  private double kd = 0.0;
  private PIDController m_PID = new PIDController(kp, ki, kd);
  private double m_setpoint = Constants.CoralIntakeVariables.kIntakeAngle;
  private double m_setpointangle = Constants.CoralIntakeVariables.Closedposition;

  public Command clawCommand(ClawState state, ClawPosition position) {
    m_PID.setPID(
        SmartDashboard.getNumber("kp", kp),
        SmartDashboard.getNumber("ki", ki),
        SmartDashboard.getNumber("kd", kd));
    ClawAngle(state);
    ClawPosition(position);
    return this.run(
        () -> {
          m_ElevationNeo.set(
              m_PID.calculate(m_ElevationNeo.getAbsoluteEncoder().getPosition(), m_setpoint));
          m_PinchNeo.set(
              m_PID.calculate(m_PinchNeo.getAbsoluteEncoder().getPosition(), m_setpointangle));
        });
  }

  public void ClawAngle(ClawState state) {
    switch (state) {
      case OPEN:
        m_setpointangle = Constants.CoralIntakeVariables.Openposition;
        break;
      case CLOSE:
        m_setpointangle = Constants.CoralIntakeVariables.Closedposition;
        break;
      default:
        break;
    }
  }

  public void ClawPosition(ClawPosition position) {
    switch (position) {
      case HANDOFF:
        m_setpoint = Constants.CoralIntakeVariables.kHandoffAngle;

        break;
      case INTAKE:
        m_setpoint = Constants.CoralIntakeVariables.kIntakeAngle;
        break;

      default:
        break;
    }
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("kp", kp);
    SmartDashboard.putNumber("ki", ki);
    SmartDashboard.putNumber("kd", kd);
    SmartDashboard.putNumber(
        "ElevationPosition", m_ElevationNeo.getAbsoluteEncoder().getPosition());
    SmartDashboard.putNumber("PinchPosition", m_PinchNeo.getAbsoluteEncoder().getPosition());
  }
}
