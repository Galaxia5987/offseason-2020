package robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

public class ElevatorSubsystem extends Subsystem {

    private TalonSRX liftMaster = new TalonSRX(RobotMap.ELEVATOR_MOTOR);

    public ElevatorSubsystem() {

        liftMaster.configMotionSCurveStrength(4);
        liftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        liftMaster.setSensorPhase(ElevatorConstants.ENCODER_REVERSED);
        liftMaster.config_kP(ElevatorConstants.TALON_PID_SLOT, 0.2, ElevatorConstants.TALON_TIMEOUT_MS);
        liftMaster.config_kI(ElevatorConstants.TALON_PID_SLOT, 0, ElevatorConstants.TALON_TIMEOUT_MS);
        liftMaster.config_kD(ElevatorConstants.TALON_PID_SLOT, 0, ElevatorConstants.TALON_TIMEOUT_MS);
        liftMaster.configSelectedFeedbackCoefficient(ElevatorConstants.TICKS_PER_METER);

    }

    /*
    public double getSpeed(){
        return liftMaster.getSelectedSensorVelocity();
    }

     */
    public void setPosition(double position) {
        liftMaster.set(ControlMode.MotionMagic, position, DemandType.ArbitraryFeedForward, ElevatorConstants.FEED_FORWARD);

    }

    public void setHeight() {
    }


    public double convertTicksToHeight() {
        return liftMaster.getSelectedSensorPosition();
    }

    /*
    public void setSpeed(double position){
        liftMaster.set(ControlMode.MotionMagic, position);
    }

     */

    @Override
    protected void initDefaultCommand() {

    }
}
