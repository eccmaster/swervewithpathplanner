package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.sensor2Subsystem;

public class readSensor2Command extends Command {
    private final sensor2Subsystem sensor2Subsystem;
   
    

    public readSensor2Command(sensor2Subsystem sensor2Subsystem) {
        this.sensor2Subsystem = sensor2Subsystem;
        addRequirements(sensor2Subsystem);
}
@Override
    public void execute() {
        boolean sensorValue = sensor2Subsystem.getSensorValue();
        
        if (sensorValue) {
            System.out.println("Sensor2 is active (High)");
        } else {
            System.out.println("Sensor2 is inactive (Low)");
        }
        SmartDashboard.putBoolean("Sensor 2", sensorValue);
        
    }

    @Override
    public boolean isFinished() {
        return false;  // Continue executing indefinitely (override as needed)
    }

}
