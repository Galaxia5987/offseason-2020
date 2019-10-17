package robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

public class Drivetrain extends Subsystem {

    public TalonSRX leftMaster = new TalonSRX(RobotMap.DRIVETRAIN_LEFT_MASTER_PORT);
    public TalonSRX rightMaster = new TalonSRX(RobotMap.DRIVETRAIN_RIGHT_MASTER_PORT);
    public VictorSPX right1 = new VictorSPX(RobotMap.DRIVETRAIN_RIGHT_SLAVE1_PORT);
    public VictorSPX left1 = new VictorSPX(RobotMap.DRIVETRAIN_LEFT_SLAVE1_PORT);
    public VictorSPX right2 = new VictorSPX(RobotMap.DRIVETRAIN_RIGHT_SLAVE2_PORT);
    public VictorSPX left2 = new VictorSPX(RobotMap.DRIVETRAIN_LEFT_SLAVE2_PORT);

    public Drivetrain() {
        leftMaster.setInverted(DrivetrainConstants.LEFT_MASTER_REVERSED);
        left1.setInverted(DrivetrainConstants.LEFT_SLAVE1_REVERSED);
        left2.setInverted(DrivetrainConstants.LEFT_SLAVE2_REVERSED);
        rightMaster.setInverted(DrivetrainConstants.RIGHT_MASTER_REVERSED);
        right1.setInverted(DrivetrainConstants.RIGHT_SLAVE1_REVERSED);
        right2.setInverted(DrivetrainConstants.RIGHT_SLAVE2_REVERSED);

        right1.follow(rightMaster);
        right2.follow(rightMaster);
        left1.follow(leftMaster);
        left2.follow(leftMaster);
    }

    /**
     * Set the percent speed of the left side of the drivetrain.
     *
     * @param speed a number from -1 to 1
     */
    public void setLeftSpeed(double speed) {
        leftMaster.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Set the percent speed of the right side of the drivetrain.
     *
     * @param speed a number from -1 to 1
     */
    public void setRightSpeed(double speed) {
        rightMaster.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Get left distance.
     *
     * @return left distance in meters.
     */
    public double getLeftDistance() {
        return convertTicksToDistance(leftMaster.getSelectedSensorPosition());
    }

    /**
     * Get right distance.
     *
     * @return right distance in meters
     */
    public double getRightDistance() {
        return convertTicksToDistance(rightMaster.getSelectedSensorPosition());
    }

    /**
     * Convert distance to ticks.
     *
     * @param distance
     * @return amount of ticks
     */
    public int convertDistanceToTicks(double distance) {
        return (int) (distance * DrivetrainConstants.TICKS_PER_METER);
    }

    /**
     * Convert ticks to distance.
     *
     * @param tick
     * @return distance in meters
     */
    public double convertTicksToDistance(int tick) {
        return tick / DrivetrainConstants.TICKS_PER_METER;
    }

    @Override
    protected void initDefaultCommand() {

    }
}
