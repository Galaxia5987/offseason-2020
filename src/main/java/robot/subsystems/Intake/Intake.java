package robot.subsystems.Intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

import static robot.subsystems.Intake.commands.MoveArms.Direction;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Intake extends Subsystem {

    private Victor leftMotor = new Victor(RobotMap.INTAKE_MOTOR_LEFT);
    private Victor rightMotor = new Victor(RobotMap.INTAKE_MOTOR_RIGHT);
    private DoubleSolenoid solenoid = new DoubleSolenoid(IntakeConstants.SOLENOID_FORWARD,IntakeConstants.SOLENOID_REVERSE);


    /**
     *
     */
    public Intake() {
        leftMotor.setInverted(IntakeConstants.LEFT_REVERSED);
        rightMotor.setInverted(IntakeConstants.LEFT_REVERSED);
    }

    /**
     * sets the direction in which the arms move
     * @param direction either UP or DOWN
     */
    public void setArms(Direction direction){
        if (direction == Direction.UP){
            solenoid.set(DoubleSolenoid.Value.kForward);
        } else {
            solenoid.set(DoubleSolenoid.Value.kReverse);
        }
    }

    /**
     * sets the speed of the wheels of the intake
     * @param speed a number from -1 to 1
     */
    public void setSpeed(double speed){
        leftMotor.setSpeed(speed);
        rightMotor.setSpeed(speed);
    }
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}