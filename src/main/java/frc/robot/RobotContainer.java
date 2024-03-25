// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.swing.text.StyleContext.SmallAttributeSet;

import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.auto.AutoBuilder;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.generated.TunerConstants;
import frc.robot.commands.intakeCommand;
import frc.robot.commands.readSensor1Command;
import frc.robot.commands.readSensor2Command;
import frc.robot.commands.shooterCommand;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.sensor1Subsystem;
import frc.robot.subsystems.sensor2Subsystem;
import frc.robot.subsystems.shooter;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.feeder;
import frc.robot.commands.armCommand;
import frc.robot.commands.feederCommand;

public class RobotContainer {
  //******************************************************************************* */
  private final arm armSubsystem = new arm();
  private final intake intakesubsytem = new intake();
  private final shooter shootersubsystem = new shooter();     
  private final feeder feedersubsystem = new feeder();                                                                 
  XboxController armController = new XboxController(1);
  XboxController arcadeController = new XboxController(2);
  private final sensor1Subsystem sensor1Subsystem;
  private final sensor2Subsystem sensor2Subsystem;
  //********************************************************************************* */
  private double MaxSpeed = TunerConstants.kSpeedAt12VoltsMps; // kSpeedAt12VoltsMps desired top speed
  private double MaxAngularRate = 1.5 * Math.PI; // 3/4 of a rotation per second max angular velocity

  /* Setting up bindings for necessary control of the swerve drive platform */
  private final CommandXboxController joystick = new CommandXboxController(0); // My joystick
  public final CommandSwerveDrivetrain drivetrain = TunerConstants.DriveTrain; // My drivetrain

  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
      .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // I want field-centric
                                                               // driving in open loop chang from OpenLoopVoltage
  private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
  private final SwerveRequest.RobotCentric forwardStraight = new SwerveRequest.RobotCentric().withDriveRequestType(DriveRequestType.OpenLoopVoltage);
  private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

  /* Path follower */
  
  //private Command runAuto = drivetrain.getAutoPath("Tests");

  private final Telemetry logger = new Telemetry(MaxSpeed);

  private void configureBindings() {
    drivetrain.setDefaultCommand( // Drivetrain will execute this command periodically
        drivetrain.applyRequest(() -> drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
        ).ignoringDisable(true));

    joystick.a().whileTrue(drivetrain.applyRequest(() -> brake));
    joystick.b().whileTrue(drivetrain
        .applyRequest(() -> point.withModuleDirection(new Rotation2d(-joystick.getLeftY(), -joystick.getLeftX()))));

    // reset the field-centric heading on left bumper press
    joystick.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldRelative()));

    drivetrain.registerTelemetry(logger::telemeterize);

    joystick.pov(0).whileTrue(drivetrain.applyRequest(() -> forwardStraight.withVelocityX(0.5).withVelocityY(0)));
    joystick.pov(180).whileTrue(drivetrain.applyRequest(() -> forwardStraight.withVelocityX(-0.5).withVelocityY(0)));


    /* Bindings for drivetrain characterization */
    /* These bindings require multiple buttons pushed to swap between quastatic and dynamic */
    /* Back/Start select dynamic/quasistatic, Y/X select forward/reverse direction */
    joystick.back().and(joystick.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
    joystick.back().and(joystick.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
    joystick.start().and(joystick.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
    joystick.start().and(joystick.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

        //***************************************************************************************/
    new JoystickButton(armController,3).whileTrue(new intakeCommand(intakesubsytem, 0.3));
    new JoystickButton(armController, 2).whileTrue(new intakeCommand(intakesubsytem, -0.3));
    new JoystickButton(armController,1).whileTrue(new shooterCommand(shootersubsystem, 10000));
    new JoystickButton(armController, 4).whileTrue( new armCommand(armSubsystem, -104));
    new JoystickButton(armController,5).whileTrue(new feederCommand(feedersubsystem, -1));
    new JoystickButton(armController,6).whileTrue(new feederCommand(feedersubsystem, 1));
    new JoystickButton(arcadeController, 1).whileTrue(new armCommand(armSubsystem, -10));
    new JoystickButton(arcadeController, 2).whileTrue(new armCommand(armSubsystem, -15));
    new JoystickButton(arcadeController, 3).whileTrue(new armCommand(armSubsystem, -20));
    new JoystickButton(arcadeController, 4).whileTrue(new armCommand(armSubsystem, -25));
    new JoystickButton(arcadeController, 5).whileTrue(new armCommand(armSubsystem, -30));
    new JoystickButton(arcadeController, 6).whileTrue(new armCommand(armSubsystem, -35));
    new JoystickButton(arcadeController, 8).whileTrue(new armCommand(armSubsystem, -115));
    new JoystickButton(arcadeController, 10).whileTrue(new ParallelCommandGroup( 
                new armCommand(armSubsystem, -5),
                new intakeCommand(intakesubsytem, 0.3)));
  }
    //*****************************************************************************************/

    private final SendableChooser<Command> autoChooser;

    
  
      
    
  

  public RobotContainer() {
    configureBindings();
    NamedCommands.registerCommand("intake", new intakeCommand(intakesubsytem,0.3).withTimeout(5.0));
    NamedCommands.registerCommand("shoot", new shooterCommand(shootersubsystem,10000).withTimeout(10.0));
    NamedCommands.registerCommand("feeder", new feederCommand(feedersubsystem,1).withTimeout(1.0));
    NamedCommands.registerCommand("armup", new armCommand(armSubsystem,-20).withTimeout(2.0));
    sensor1Subsystem = new sensor1Subsystem(0);
    sensor2Subsystem = new sensor2Subsystem(1);
    readSensor1Command readSensor1Command = new readSensor1Command(sensor1Subsystem);
    readSensor2Command readSensor2Command = new readSensor2Command(sensor2Subsystem);
    
   sensor1Subsystem.setDefaultCommand(readSensor1Command);
   sensor2Subsystem.setDefaultCommand(readSensor2Command);
   
    // Build an auto chooser. This will use Commands.none() as the default option.
      autoChooser = AutoBuilder.buildAutoChooser();
  
      // Another option that allows you to specify the default auto by its name
      // autoChooser = AutoBuilder.buildAutoChooser("My Default Auto");
  
      SmartDashboard.putData("Auto Chooser", autoChooser);
      //SmartDashboard.putBoolean("Note Sensor 1",readSensor1Command);
      //SmartDashboard.putBoolean("Note Sensor 2", readSensor2Command);
    }
  
    public Command getAutonomousCommand() {
      return autoChooser.getSelected();
    }
    //configureBindings();

    
    




  }

  //*public Command getAutonomousCommand() {
    /* First put the drivetrain into auto run mode, then run the auto */
    //return new PathPlannerAuto("Tests");
   // return runAuto; 
 // }
