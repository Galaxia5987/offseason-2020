package robot.subsystems.gripper;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

public class GripperSubsystem extends Subsystem {

    private final double MIN_HEIGHT = 1;
    private final double MIN_CUBE_DISTANCE = 2;
    private Victor rightMotor = new Victor(RobotMap.RIGHT_MOTOR_PORT);
    private Victor leftMotor = new Victor(RobotMap.LEFT_MOTOR_PORT);
    private AnalogInput proximity = new AnalogInput(RobotMap.PROXIMITY_PORT);

    public GripperSubsystem() {
        rightMotor.setInverted(true);
        leftMotor.setInverted(false);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());

    }

    /**
     * @param currentHeight the current height of the elevator
     * @return if the elevator is in danger zone.
     */
    public boolean inDangerOn(double currentHeight) {
        if (rightMotor.getSpeed() < 0 || leftMotor.getSpeed() < 0)
            return currentHeight > MIN_HEIGHT;
        return false;
    }

    /**
     * @return current distance from the cube
     */
    private double getCubeDistance() {
        return proximity.getVoltage();
    }

    /**
     * @return if the cube is inside the gripper
     */
    public boolean isCubeInside() {
        return MIN_CUBE_DISTANCE < getCubeDistance();
    }

    /**
     * @param speed set speed for the right side of the gripper
     */
    private void setRightSpeed(double speed) {
        rightMotor.setSpeed(speed);
    }

    /**
     * @param speed set speed for the left side of the gripper
     */
    private void setLeftSpeed(double speed) {
        leftMotor.setSpeed(speed);
    }

    public void setVelocities(double rightVelocity, double leftVelocity) {
        setRightSpeed(rightVelocity);
        setLeftSpeed(leftVelocity);
    }
}