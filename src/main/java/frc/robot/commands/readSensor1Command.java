package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.sensor1Subsystem;

public class readSensor1Command extends Command {
    private final sensor1Subsystem sensor1Subsystem;
    

    public readSensor1Command(sensor1Subsystem sensor1Subsystem) {
        this.sensor1Subsystem = sensor1Subsystem;
        addRequirements(sensor1Subsystem);
}
@Override
    public void execute() {
        boolean sensorValue = sensor1Subsystem.getSensorValue();
        
        if (sensorValue) {
            System.out.println("Sensor 1 is active (High)");
    
        } else {
            System.out.println("Sensor 1 is inactive (Low)");
        }
        SmartDashboard.putBoolean("Sensor 1", sensorValue);
    }

    @Override
    public boolean isFinished() {
        return false;  // Continue executing indefinitely (override as needed)
    }

}
