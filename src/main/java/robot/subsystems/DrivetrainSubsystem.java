package robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.subsystems.Intake.DrivetrainPorts;

public class DrivetrainSubsystem extends Subsystem {

    public TalonSRX leftMaster = new TalonSRX(DrivetrainPorts.LEFT_MASTER_PORT);
    public TalonSRX rightMaster = new TalonSRX(DrivetrainPorts.RIGHT_MASTER_PORT);
    public VictorSPX right1 = new VictorSPX(DrivetrainPorts.RIGHT1_PORT);
    public VictorSPX left1 = new VictorSPX(DrivetrainPorts.LEFT1_PORT);
    public VictorSPX right2 = new VictorSPX(DrivetrainPorts.RIGHT2_PORT);
    public VictorSPX left2 = new VictorSPX(DrivetrainPorts.LEFT2_PORT);

    public DrivetrainSubsystem(){
        leftMaster.setInverted(DrivetrainConstants.IS_LEFT_INVERTED);
        left1.setInverted(DrivetrainConstants.IS_LEFT_INVERTED);
        left2.setInverted(DrivetrainConstants.IS_LEFT_INVERTED);
        rightMaster.setInverted(DrivetrainConstants.IS_RIGHT_INVERTED);
        right1.setInverted(DrivetrainConstants.IS_RIGHT_INVERTED);
        right2.setInverted(DrivetrainConstants.IS_RIGHT_INVERTED);

        right1.follow(rightMaster);
        right2.follow(rightMaster);
        left1.follow(leftMaster);
        left2.follow(leftMaster);
    }

    public void setLeftSpeed(double speed){
        leftMaster.set(ControlMode.PercentOutput,speed);
    }

    public void setRightSpeed(double speed){
        rightMaster.set(ControlMode.PercentOutput,speed);
    }

    public double getLeftDistance(){
        return convertTicksToDistance(leftMaster.getSelectedSensorPosition());
    }

    public double getRightDistance(){
        return convertTicksToDistance(rightMaster.getSelectedSensorPosition());
    }

    public int convertDistanceToTicks(double distance) {
        return (int) (distance * DrivetrainConstants.TICKS_PER_METER);
    }

    public double convertTicksToDistance(int tick) {
        return tick / DrivetrainConstants.TICKS_PER_METER;
    }

    @Override
    protected void initDefaultCommand() {

    }
}
