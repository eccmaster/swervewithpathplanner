package frc.robot.commands;

import frc.robot.subsystems.intake;
import frc.robot.subsystems.shooter;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class shooterCommand extends Command{
    private final shooter shooterSubsystem;
    private final double wheelSpeed;
    private double currentSpeed;


    public shooterCommand(shooter shooterSubsystem, double wheelSpeed){
        this.shooterSubsystem = shooterSubsystem;
        this.wheelSpeed = wheelSpeed;
        addRequirements(shooterSubsystem);
    }
    @Override
    public void initialize(){
    

    }

    @Override
    public void execute(){
       // shooterSubsystem.shootMotor1.set(wheelSpeed);
       // shooterSubsystem.shootMotor2.set(-wheelSpeed);
        //SmartDashboard.putNumber("intake encoder = ", intakeSubsytem.intakeEncoder.get());
                
        shooterSubsystem.wheel1PidController.setReference(wheelSpeed, CANSparkMax.ControlType.kVelocity);
        shooterSubsystem.wheel2PidController.setReference(-wheelSpeed, CANSparkMax.ControlType.kVelocity);
    
        
       
        
    }

    @Override
    public void end(boolean interrupted){
        shooterSubsystem.shootMotor1.set(0);
        shooterSubsystem.shootMotor2.set(0);
    

    }

    @Override
    public boolean isFinished(){
        return false;
    }

    


}
