package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class shooter extends SubsystemBase{
    public CANSparkMax shootMotor1 = new CANSparkMax(50, MotorType.kBrushless);
    public RelativeEncoder shooterMotor1Encoder = shootMotor1.getEncoder();
    public CANSparkMax shootMotor2 = new CANSparkMax(51, MotorType.kBrushless);
    public RelativeEncoder shooterMotor2Encoder = shootMotor2.getEncoder();
    public SparkPIDController wheel1PidController = shootMotor1.getPIDController();
    public SparkPIDController wheel2PidController = shootMotor2.getPIDController();
   
    private double kP = 6e-5; 
    private double kI = 0;
    private double kD = 0; 
    private double kIz = 0; 
    private double kFF = 0.000015; 
    private double kMaxOutput = 1; 
    private double kMinOutput = -1;
    private double maxRPM = 5700;




    public shooter() {
        // This method will be called once per scheduler run
        shootMotor1.restoreFactoryDefaults();
        shootMotor2.restoreFactoryDefaults();
        
        shootMotor1.setInverted(false);
        shootMotor2.follow(shootMotor1, false);
        

        wheel1PidController.setP(kP);
        wheel1PidController.setI(kI);
        wheel1PidController.setD(kD);
        wheel1PidController.setIZone(kIz);
        wheel1PidController.setFF(kFF);
        wheel1PidController.setOutputRange(kMinOutput, kMaxOutput);

        wheel2PidController.setP(kP);
        wheel2PidController.setI(kI);
        wheel2PidController.setD(kD);
        wheel2PidController.setIZone(kIz);
        wheel2PidController.setFF(kFF);
        wheel2PidController.setOutputRange(kMinOutput, kMaxOutput);

       
    }

    @Override
    public void periodic() {
    
       SmartDashboard.putNumber("Wheel Speed =", shooterMotor1Encoder.getVelocity());

    }


    @Override
    public void simulationPeriodic() {
        // This methos will be called once per scheduler run during simulation
    }






}





