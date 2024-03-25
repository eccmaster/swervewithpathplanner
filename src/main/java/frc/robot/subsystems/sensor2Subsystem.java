package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class sensor2Subsystem extends SubsystemBase{
    private final DigitalInput digitalInput;
    
    public sensor2Subsystem(int channel) {
        digitalInput = new DigitalInput(channel);
    }
    public boolean getSensorValue() {
        return digitalInput.get();
    }
}