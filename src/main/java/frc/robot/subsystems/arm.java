package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class arm extends SubsystemBase{
    public CANSparkMax armLeftMotor = new CANSparkMax(20, MotorType.kBrushless);
    public CANSparkMax armRightMotor = new CANSparkMax(21, MotorType.kBrushless);
    public RelativeEncoder armLeftEncoder = armLeftMotor.getEncoder();
    public RelativeEncoder armRightEncoder = armRightMotor.getEncoder();
    public SparkPIDController armLeftPidController = armLeftMotor.getPIDController();
    public SparkPIDController armRightPidController = armLeftMotor.getPIDController();
    
    private double kP = 0.04; 
    private double kI = 0;
    private double kD = 0.0001; 
    private double kIz = 0; 
    private double kFF = 0.000015; 
    private double kMaxOutput = 1; 
    private double kMinOutput = -1;
    private double maxRPM = 5700;
    
   // public static final SparkMaxAlternateEncoder.Type kAltEncType = SparkMaxAlternateEncoder.Type.kQuadrature;
  //  private static final int kCPR = 8192;
  //  public AbsoluteEncoder armAbsoluteEncoder;
    //public SparkAbsoluteEncoder armAbsoluteEncoder;   
    static DutyCycleEncoder encoder = new DutyCycleEncoder(0);

    public static void main(String[] args) {
    
    }
   
    // MotorControllerGroup armController = MotorControllerGroup(armLeftMotor,armRightMotor);


    


    public arm() {
        armLeftMotor.restoreFactoryDefaults();
        armRightMotor.restoreFactoryDefaults();
        armRightMotor.follow(armLeftMotor,true);
        armRightMotor.setSmartCurrentLimit(40);
        //armRightMotor.setInverted(true);
        armLeftMotor.setInverted(false);
        armLeftMotor.setSmartCurrentLimit(40);

        armRightPidController.setP(kP);
        armRightPidController.setI(kI);
        armRightPidController.setD(kD);
        armRightPidController.setIZone(kIz);
        armRightPidController.setFF(kFF);
        armRightPidController.setOutputRange(kMinOutput, kMaxOutput);

        armLeftPidController.setP(kP);
        armLeftPidController.setI(kI);
        armLeftPidController.setD(kD);
        armLeftPidController.setIZone(kIz);
        armLeftPidController.setFF(kFF);
        armLeftPidController.setOutputRange(kMinOutput, kMaxOutput);
        //armLeftPidController.setPositionPIDWrappingEnabled(true);
        //armLeftPidController.setPositionPIDWrappingMinInput(0);
        //armLeftPidController.setPositionPIDWrappingMaxInput(360);
        
        //m__throughBoreEncoder = armLeftMotor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle); 
        //armAbsoluteEncoder = armLeftMotor.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle);
        //armAbsoluteEncoder = armLeftMotor.getAbsoluteEncoder();
        


    }

    @Override
    public void periodic() {
    SmartDashboard.putNumber(" left  arm position is ", armLeftEncoder.getPosition());
    SmartDashboard.putNumber(" right  arm position is ", armRightEncoder.getPosition());
    //SmartDashboard.putNumber(" absolute encoder", 360*encoder.getAbsolutePosition());
    }


    
}




   
    
