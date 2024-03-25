package frc.robot.commands;

import frc.robot.subsystems.intake;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;



public class  intakeCommand extends Command {
    private final intake intakeSubsytem;
    private final double speed;
    
    public intakeCommand(intake intakeSubsystem, double speed){
        this.intakeSubsytem = intakeSubsystem;
        this.speed = speed;
        addRequirements(intakeSubsystem);
    }
    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        intakeSubsytem.intakeMotor1.set(speed);
        //SmartDashboard.putNumber("intake encoder = ", intakeSubsytem.intakeEncoder.get());

    }

    @Override
    public void end(boolean interrupted){
        intakeSubsytem.intakeMotor1.set(0);

    }

    @Override
    public boolean isFinished(){
        return false;
    }

    
}
