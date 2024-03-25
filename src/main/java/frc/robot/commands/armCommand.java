package frc.robot.commands;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.intake;
import frc.robot.commands.intakeCommand;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.SparkAbsoluteEncoder;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
//import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class armCommand extends Command{
  private final arm armSubsystem;
  private final double armPosition;
  private final double armPositionUp = 0;

  

  public armCommand(arm armSubsystem, double armPosition){
    this.armSubsystem = armSubsystem;
    this.armPosition = armPosition;
    addRequirements(armSubsystem);
  }

  /**
   * The initial subroutine of a command.  Called once when the command is initially scheduled.
   */
  @Override
  public void initialize()  
  {
    
    armSubsystem.armLeftPidController.setReference(armPositionUp, ControlType.kPosition);
  }

  /**
   * The main body of a command.  Called repeatedly while the command is scheduled. (That is, it is called repeatedly
   * until {@link #isFinished()}) returns true.)
   */
  @Override
  public void execute()
  {

    
   armSubsystem.armLeftPidController.setReference(armPosition, ControlType.kPosition);
   
   
   //SmartDashboard.putNumber("arm position = ",arm.armLeftEncoder.getPosition());
  }

  /**
   * <p>
   * Returns whether this command has finished. Once a command finishes -- indicated by this method returning true --
   * the scheduler will call its {@link #end(boolean)} method.
   * </p><p>
   * Returning false will result in the command never ending automatically. It may still be cancelled manually or
   * interrupted by another command. Hard coding this command to always return true will result in the command executing
   * once and finishing immediately. It is recommended to use *
   * {@link edu.wpi.first.wpilibj2.command.InstantCommand InstantCommand} for such an operation.
   * </p>
   *
   * @return whether this command has finished.
   */
  //@Override
  //public boolean isFinished() { 
  //}

  /**
   * The action to take when the command ends. Called when either the command finishes normally -- that is it is called
   * when {@link #isFinished()} returns true -- or when  it is interrupted/canceled. This is where you may want to wrap
   * up loose ends, like shutting off a motor that was being used in the command.
   *
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted){
  armSubsystem.armLeftPidController.setReference(armPositionUp, ControlType.kPosition);
  
   
  }

}