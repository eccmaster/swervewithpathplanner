package frc.robot.commands;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.feeder;

public class feederCommand extends Command{
   
    private final feeder feederSubsystem;
    private final double power;
    
    public feederCommand(feeder feederSubsystem, double power){
        this.feederSubsystem = feederSubsystem;
        this.power = power;
        addRequirements(feederSubsystem);
    }

    @Override
    public void initialize(){
    

    }

    @Override
    public void execute(){
       
       
    feederSubsystem.feederMotor.set(power);
    
    }

    @Override
    public void end(boolean interrupted){
    
        feederSubsystem.feederMotor.set(0);

    }

    @Override
    public boolean isFinished(){
        return false;
    }

    


}




