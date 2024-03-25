package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class sensor1Subsystem extends SubsystemBase{
    private final DigitalInput digitalInput;
    
    public sensor1Subsystem(int channel) {
        digitalInput = new DigitalInput(channel);
    }
    public boolean getSensorValue() {
        return digitalInput.get();
    }
}