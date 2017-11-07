package com.iuh.minhthanghuunghia.lotusflowernt;

import android.databinding.DataBindingUtil;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iuh.minhthanghuunghia.lotusflowernt.databinding.ActivityCameraFragmentBinding;

public class CameraFragment extends Fragment {
    private ActivityCameraFragmentBinding binding;
    private Camera mCamera;
    private CameraView mCameraView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_camera_fragment, container, false);
        try {
            mCamera = Camera.open();
            try {
                mCamera = Camera.open();
            } catch (Exception e) {
                Log.d("ERROR", "Failed to get camera: " + e.getMessage());
            }
        } catch (Exception e) {
            Log.d("ERROR", "Failed to get camera: " + e.getMessage());
        }
        if (mCamera != null) {
            mCameraView = new CameraView(getContext(), mCamera);
            binding.cameraView.addView(mCameraView);
        }
        return binding.getRoot();
    }
}
