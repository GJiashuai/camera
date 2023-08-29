package io.flutter.plugins.camera.features.focusLen;

import android.hardware.camera2.CaptureRequest;

import androidx.annotation.NonNull;

import io.flutter.plugins.camera.CameraProperties;
import io.flutter.plugins.camera.features.CameraFeature;


public class FocusModeLockedWithLensPositionFeature extends CameraFeature<Float> {
    private static final Float DEFAULT_LENS_POSITION = 0.5f;
    private final boolean hasSupport;
    @NonNull
    private Float currentSetting = DEFAULT_LENS_POSITION;

    public FocusModeLockedWithLensPositionFeature(@NonNull CameraProperties cameraProperties) {
        super(cameraProperties);
        // 您可以在此处添加检查设备是否支持设置对焦模式和镜头位置的逻辑
        hasSupport = true;
    }

    @NonNull
    @Override
    public String getDebugName() {
        return "FocusModeLockedWithLensPositionFeature";
    }

    @NonNull
    @Override
    public Float getValue() {
        return currentSetting;
    }

    @Override
    public void setValue(@NonNull Float value) {
        this.currentSetting = value;
    }

    @Override
    public boolean checkIsSupported() {
        return hasSupport;
    }

    @Override
    public void updateBuilder(@NonNull CaptureRequest.Builder requestBuilder) {
        System.out.println("requestBuilder");
        System.out.println(getValue());

        if (!checkIsSupported()) {
            return;
        }
        // 设置对焦模式为锁定（禁用自动对焦）
        requestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_OFF);
        // 设置镜头位置
        requestBuilder.set(CaptureRequest.LENS_FOCUS_DISTANCE, getValue());
    }
}