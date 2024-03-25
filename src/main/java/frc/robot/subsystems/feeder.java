package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class feeder extends SubsystemBase {

    public CANSparkMax feederMotor = new CANSparkMax(40, MotorType.kBrushless);
    public RelativeEncoder intakeEncoder = feederMotor.getEncoder();
    public SparkPIDController feederPidController = feederMotor.getPIDController();
    private double kP = 6e-5; 
    private double kI = 0;
    private double kD = 0; 
    private double kIz = 0; 
    private double kFF = 0.000015; 
    private double kMaxOutput = 1; 
    private double kMinOutput = -1;
    private double maxRPM = 5700;

    
   


    public feeder() {
        // This method will be called once per scheduler run
        feederMotor.restoreFactoryDefaults();
        feederPidController.setP(kP);
        feederPidController.setI(kI);
        feederPidController.setD(kD);
        feederPidController.setIZone(kIz);
        feederPidController.setFF(kFF);
        feederPidController.setOutputRange(kMinOutput, kMaxOutput);

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