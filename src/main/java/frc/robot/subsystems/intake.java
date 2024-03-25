package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class intake extends SubsystemBase{
    public CANSparkMax intakeMotor1 = new CANSparkMax(30, MotorType.kBrushless);
    public RelativeEncoder intakeEncoder1 = intakeMotor1.getEncoder();
    public CANSparkMax intakeMotor2 = new CANSparkMax(31, MotorType.kBrushless);
    public RelativeEncoder intakeEncoder2 = intakeMotor2.getEncoder();
    public SparkPIDController intakePidController1 = intakeMotor1.getPIDController();
    public SparkPIDController intakePidController2 = intakeMotor2.getPIDController();
   
    private double kP = 6e-5; 
    private double kI = 0;
    private double kD = 0; 
    private double kIz = 0; 
    private double kFF = 0.000015; 
    private double kMaxOutput = 1; 
    private double kMinOutput = -1;
    private double maxRPM = 5700;


    public intake() {
        // This method will be called once per scheduler run
        intakeMotor1.restoreFactoryDefaults();
        intakeMotor2.restoreFactoryDefaults();
        
        intakeMotor1.setInverted(false);
        intakeMotor2.follow(intakeMotor1, false);

        intakePidController1.setP(kP);
        intakePidController1.setI(kI);
        intakePidController1.setD(kD);
        intakePidController1.setIZone(kIz);
        intakePidController1.setFF(kFF);
        intakePidController1.setOutputRange(kMinOutput, kMaxOutput);

        intakePidController2.setP(kP);
        intakePidController2.setI(kI);
        intakePidController2.setD(kD);
        intakePidController2.setIZone(kIz);
        intakePidController2.setFF(kFF);
        intakePidController2.setOutputRange(kMinOutput, kMaxOutput);
        

    }
        
    @Override
    public void periodic() {
    
       // SmartDashboard.putNumber("Intake encoder value", intakeEncoder);

    }

    @Override
    public void simulationPeriodic() {
        // This methos will be called once per scheduler run during simulation
    }


    
}